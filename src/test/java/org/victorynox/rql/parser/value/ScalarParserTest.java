package org.victorynox.rql.parser.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStream;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.caster.*;
import org.victorynox.rql.parser.AbstractParserTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ScalarParserTest extends AbstractParserTest<ScalarValue> {

	@Override
	@BeforeEach
	protected void startUp() {
		Map<String, TypeCaster> typeCasterMap = new HashMap<>();
		typeCasterMap.put(Boolean.class.getSimpleName(), new BooleanTypeCaster());
		typeCasterMap.put(Double.class.getSimpleName(), new DoubleTypeCaster());
		typeCasterMap.put(Integer.class.getSimpleName(), new IntegerTypeCaster());
		typeCasterMap.put(String.class.getSimpleName(), new StringTypeCaster());
		parser = new ScalarParser(typeCasterMap);
	}

	/**
	 * Return valid tokenStream data for parse
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		Stream.Builder<Arguments> streamBuilder = Stream.builder();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "int", 0, 3));
		tokenList.add(new Token(TokenType.T_COLON, ":", 3, 4));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 4, 6));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(10)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "string", 0, 6));
		tokenList.add(new Token(TokenType.T_COLON, ":", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "str", 7, 10));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>("str")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "float", 0, 5));
		tokenList.add(new Token(TokenType.T_COLON, ":", 5, 6));
		tokenList.add(new Token(TokenType.T_FLOAT, "10.31", 6, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(10.31)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "double", 0, 5));
		tokenList.add(new Token(TokenType.T_COLON, ":", 5, 6));
		tokenList.add(new Token(TokenType.T_FLOAT, "11.11", 6, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(11.11)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "bool", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_TRUE, "true()", 5, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(true)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "bool", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_FALSE, "false()", 5, 12));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(false)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "boolean", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_TRUE, "true()", 5, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(true)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "boolean", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_FALSE, "false()", 5, 12));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(false)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 4, 6));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(10)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_STRING, "str", 7, 10));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>("str")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_FLOAT, "10.31", 6, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(10.31)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_FLOAT, "11.11", 6, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(11.11)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TRUE, "true()", 5, 11));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(true)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_FALSE, "false()", 5, 12));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(false)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_NULL, "null()", 5, 12));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>(null)));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "", 5, 12));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new ScalarValue<>("")));

		return streamBuilder.build();
	}

	/**
	 * Return invalid tokenStream data for cache exception test
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData()
	{
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_END, "", 0, 0));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "decimal", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_TRUE, "10.34", 5, 12));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TRUE, "false()", 5, 11));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_FALSE, "true()", 5, 12));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_NULL, "not null()", 5, 12));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "not empty", 5, 12));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}
}