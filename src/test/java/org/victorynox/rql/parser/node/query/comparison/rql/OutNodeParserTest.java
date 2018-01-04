package org.victorynox.rql.parser.node.query.comparison.rql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStream;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.caster.*;
import org.victorynox.rql.node.operator.array.OutNode;
import org.victorynox.rql.parser.node.AbstractNodeParserTest;
import org.victorynox.rql.parser.value.ArrayParser;
import org.victorynox.rql.parser.value.FieldParser;
import org.victorynox.rql.parser.value.ScalarParser;
import org.victorynox.rql.parser.value.ScalarValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutNodeParserTest extends AbstractNodeParserTest<OutNode<ScalarValue>> {

	/**
	 * Return invalid tokenStream data for cache exception test
	 *
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData() {
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "+field1", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, "(", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 20, 21));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 20, 21));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 20, 21));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 21, 22));

		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}

	/**
	 * Return valid tokenStream data for parse
	 *
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getSupportSuccessData() {
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}

	/**
	 * Return valid tokenStream data for parse
	 *
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		Stream.Builder<Arguments> streamBuilder = Stream.builder();

		List<Token> tokenList;

		OutNode<ScalarValue> expectedNode;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "string1", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_STRING, "string2", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_STRING, "string3", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_STRING, "string4", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new OutNode<>("field", new ArrayList<>());
		expectedNode.addValue(new ScalarValue<>("string1"));
		expectedNode.addValue(new ScalarValue<>("string2"));
		expectedNode.addValue(new ScalarValue<>("string3"));
		expectedNode.addValue(new ScalarValue<>("string4"));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_INTEGER, "15", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_INTEGER, "25", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_INTEGER, "-5", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_INTEGER, "54", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new OutNode<>("field", new ArrayList<>());
		expectedNode.addValue(new ScalarValue<>(15));
		expectedNode.addValue(new ScalarValue<>(25));
		expectedNode.addValue(new ScalarValue<>(-5));
		expectedNode.addValue(new ScalarValue<>(54));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_FLOAT, "15.42", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_FLOAT, "0.42", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new OutNode<>("field", new ArrayList<>());
		expectedNode.addValue(new ScalarValue<>(15.42));
		expectedNode.addValue(new ScalarValue<>(0.42));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_TRUE, "true()", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new OutNode<>("field", new ArrayList<>());
		expectedNode.addValue(new ScalarValue<>(true));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));


		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "out", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_FALSE, "false()", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_TRUE, "true()", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new OutNode<>("field", new ArrayList<>());
		expectedNode.addValue(new ScalarValue<>(false));
		expectedNode.addValue(new ScalarValue<>(true));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		return streamBuilder.build();
	}

	@Override
	protected void parseResultAssert(OutNode expectedResult, OutNode result) {
		assertEquals(expectedResult.getFiled(), result.getFiled());
		assertEquals(expectedResult.getValues(), result.getValues());
	}

	@Override
	@BeforeEach
	protected void startUp() {
		Map<String, TypeCaster> typeCasterMap = new HashMap<>();
		typeCasterMap.put(Boolean.class.getSimpleName(), new BooleanTypeCaster());
		typeCasterMap.put(Double.class.getSimpleName(), new DoubleTypeCaster());
		typeCasterMap.put(Integer.class.getSimpleName(), new IntegerTypeCaster());
		typeCasterMap.put(String.class.getSimpleName(), new StringTypeCaster());
		ScalarParser scalarParser = new ScalarParser(typeCasterMap);
		parser = new OutNodeParser<>(new FieldParser(), new ArrayParser<>(scalarParser));
	}
}