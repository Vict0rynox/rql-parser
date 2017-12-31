package org.victorynox.rql.parser.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStream;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.caster.*;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserTest {

	private ArrayParser<ScalarValue> parser;

	@BeforeEach
	void startUp() {
		Map<String, TypeCaster> typeCasterMap = new HashMap<>();
		typeCasterMap.put(Boolean.class.getSimpleName(), new BooleanTypeCaster());
		typeCasterMap.put(Double.class.getSimpleName(), new DoubleTypeCaster());
		typeCasterMap.put(Integer.class.getSimpleName(), new IntegerTypeCaster());
		typeCasterMap.put(String.class.getSimpleName(), new StringTypeCaster());
		parser = new ArrayParser<>(new ScalarParser(typeCasterMap));
	}

	/**
	 * Return valid tokenStream data for parse
	 * @return stream
	 */
	private static Stream<Arguments> getParseSuccessData() {
		Stream.Builder<Arguments> streamBuilder = Stream.builder();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 3, 4));
		tokenList.add(new Token(TokenType.T_STRING, "str", 4, 7));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 7, 8));
		tokenList.add(new Token(TokenType.T_FLOAT, "10.31", 8, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_FLOAT, "11.11", 14, 19));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 19, 20));
		tokenList.add(new Token(TokenType.T_TRUE, "true()", 20, 26));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 26, 27));
		tokenList.add(new Token(TokenType.T_FALSE, "false()", 27, 34));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 34, 35));
		tokenList.add(new Token(TokenType.T_NULL, "null()", 35, 41));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 41, 42));
		tokenList.add(new Token(TokenType.T_EMPTY, "", 42, 42));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 42, 43));


		List<ScalarValue> expectedResult = new ArrayList<>();
		expectedResult.add(new ScalarValue<>(10));
		expectedResult.add(new ScalarValue<>("str"));
		expectedResult.add(new ScalarValue<>(10.31));
		expectedResult.add(new ScalarValue<>(11.11));
		expectedResult.add(new ScalarValue<>(true));
		expectedResult.add(new ScalarValue<>(false));
		expectedResult.add(new ScalarValue<>(null));
		expectedResult.add(new ScalarValue<>(""));

		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedResult));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 6, 7));

		expectedResult = new ArrayList<>();
		expectedResult.add(new ScalarValue<>(10));

		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedResult));


		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 3, 4));
		tokenList.add(new Token(TokenType.T_STRING, "10", 4, 6));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 6, 7));

		expectedResult = new ArrayList<>();
		expectedResult.add(new ScalarValue<>(10));
		expectedResult.add(new ScalarValue<>("10"));

		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedResult));

		return streamBuilder.build();
	}

	/**
	 * Return invalid tokenStream data for cache exception test
	 * @return stream
	 */
	private static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData()
	{
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 3, 4));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 4, 6));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 6, 7));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 3, 4));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 4, 6));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 6, 7));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 3, 4));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 6, 7));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 3, 4));
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 4, 6));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 1, 3));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0, 1));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 6, 7));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();

	}

	@ParameterizedTest
	@MethodSource("getParseSuccessData")
	void parse_Success(TokenStreamIterator tokenStreamIterator, List<ScalarValue> expectedResult) {
		try {
			List<ScalarValue> result = parser.parse(tokenStreamIterator);
			for (int i = 0; i < expectedResult.size(); i++) {
				assertEquals(expectedResult.get(i),result.get(i));
			}
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}

	@ParameterizedTest
	@MethodSource("getParseSyntaxErrorExceptionData")
	void parse_SyntaxErrorException(TokenStreamIterator tokenStreamIterator)
	{
		assertThrows(SyntaxErrorException.class, () -> parser.parse(tokenStreamIterator));
	}
}