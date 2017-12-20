package org.victorynox.rql.parser.value;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.caster.TypeCaster;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.parser.TokenStreamParser;

import javax.lang.model.type.UnknownTypeException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author victorynox
 * @version 0.1
 */
public class ScalarParser implements TokenStreamParser<ScalarValue> {

	/**
	 * Type name aliases
	 */
	protected Map<String, String> typeNameAliases;

	/**
	 * List with TypeCaster
	 */
	protected Map<String,TypeCaster> typeCasterMap;


	/**
	 * Init default alias of type.
	 */
	public ScalarParser(Map<String,TypeCaster> typeCasterMap)
	{
		this.typeCasterMap = typeCasterMap;

		typeNameAliases = new HashMap<>();
		typeNameAliases.put("int", Integer.class.getSimpleName());
		typeNameAliases.put("string", String.class.getSimpleName());
		typeNameAliases.put("float", Double.class.getSimpleName());
		typeNameAliases.put("double", Double.class.getSimpleName());
		typeNameAliases.put("boolean", Boolean.class.getSimpleName());
		typeNameAliases.put("bool", Boolean.class.getSimpleName());
	}

	/**
	 * Add alias to type.
	 *
	 * @param alias alias type
	 * @param typeName Origin type name
	 */
	public void addAlias(String alias, String typeName)
	{
		typeNameAliases.put(alias, typeName);
	}

	/**
	 * Register type caster with unique type.
	 * In typeCasterMap can be save only one typeCaster with same type.
	 * @param typeCaster typeCaster
	 * @return this
	 */
	public ScalarParser registerTypeCaster(TypeCaster typeCaster)
	{
		String typeName = typeCaster.getType().getSimpleName();
		typeCasterMap.put(typeName, typeCaster);
		return this;
	}

	/**
	 *
	 * @param typeName TypeCaster type class name.
	 * @return TypeCaster with same type
	 */
	protected TypeCaster getTypeCaster(String typeName) throws SyntaxErrorException
	{
		//get alias if exist
		typeName = typeNameAliases.getOrDefault(typeName, typeName);
		TypeCaster typeCaster = typeCasterMap.get(typeName);
		if(typeCaster == null) {
			//TODO: change exception type
			throw new SyntaxErrorException("Type caster not found.");
		}
		return typeCaster;
	}

	/**
	 * Return initialized <code>ScalarValue</code> with <code>Token</code> value type
	 * @param typeToken token with value type
	 * @param valueToken token with value
	 * @return <code>ScalarValue</code> with value type
	 */
	protected ScalarValue createScalarValue(Token typeToken, Token valueToken) throws SyntaxErrorException
	{
		Object value = getTypeCaster(typeToken.getValue()).typeCast(valueToken);
		if(value instanceof Integer) {
			return new ScalarValue<>((Integer)value);
		} else if(value instanceof Float) {
			return new ScalarValue<>((Float)value);
		} else if(value instanceof Double) {
			return new ScalarValue<>((Double)value);
		} else if(value instanceof Boolean) {
			return new ScalarValue<>((Boolean)value);
		} else if(value instanceof String) {
			return new ScalarValue<>((String)value);
		}
		throw new UnknownTypeException(null,value);
	}

	/**
	 * Convert token to scalarValue
	 * @return <code>ScalarValue</code> with value type
	 */
	protected ScalarValue createScalarValue(Token token) throws SyntaxErrorException {
		if (token.test(new TokenType[]{TokenType.T_FALSE})) {
			return new ScalarValue<>(false);
		} else if (token.test(new TokenType[]{TokenType.T_TRUE})) {
			return new ScalarValue<>(true);
		} else if (token.test(new TokenType[]{TokenType.T_NULL})) {
			return new ScalarValue<>(null);
		} else if (token.test(new TokenType[]{TokenType.T_EMPTY})) {
			return new ScalarValue<>("");
		} else if (token.test(new TokenType[]{TokenType.T_DATE})) {
			try {
				Date date = DateFormat.getInstance().parse(token.getValue());
				return new ScalarValue<>(date);
			} catch (ParseException e) {
				throw new SyntaxErrorException("Date is not valid.");
			}
		} else if (token.test(new TokenType[]{TokenType.T_STRING})) {
			return new ScalarValue<>(token.getValue());
		} else  if (token.test(new TokenType[]{TokenType.T_INTEGER})) {
			return new ScalarValue<>(Integer.parseInt(token.getValue()));
		} else if (token.test(new TokenType[]{TokenType.T_FLOAT})) {
			return new ScalarValue<>(Double.parseDouble(token.getValue()));
		}
		throw new SyntaxErrorException("Unknown value type.");
	}

	@Override
	public ScalarValue parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		ScalarValue scalarValue;
		if(tokenStream.getCurrent().test(new TokenType[]{TokenType.T_TYPE})) {
			Token typeToken = tokenStream.next();
			tokenStream.expect(new TokenType[]{TokenType.T_COLON});
			scalarValue = createScalarValue(typeToken, tokenStream.getCurrent());
		} else {
			scalarValue = createScalarValue(tokenStream.getCurrent());
		}
		tokenStream.next();
		return scalarValue;
	}

}
