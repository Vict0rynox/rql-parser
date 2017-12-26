package org.victorynox.rql;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

	private Token token;

	static private Stream<Arguments> getTokenParamsData() {
		return Stream.of(
				Arguments.of(TokenType.T_END, "", 32),
				Arguments.of(TokenType.T_INTEGER, "43", 21),
				Arguments.of(TokenType.T_FLOAT, "1.34", 4),
				Arguments.of(TokenType.T_STRING, "Mana", 65),
				Arguments.of(TokenType.T_DATE, "1995-04-20T13:34:01", 14),
				Arguments.of(TokenType.T_GLOB, "?st*", 16),
				Arguments.of(TokenType.T_CLOSE_PARENTHESIS, ")", 5),
				Arguments.of(TokenType.T_OPEN_PARENTHESIS, "(", 15),
				Arguments.of(TokenType.T_COMMA, ",", 6),
				Arguments.of(TokenType.T_AMPERSAND, "&", 9),
				Arguments.of(TokenType.T_PIPE, "|", 18),
				Arguments.of(TokenType.T_PLUS, "+", 29),
				Arguments.of(TokenType.T_MINUS, "-", 54),
				Arguments.of(TokenType.T_COLON, ":", 21),
				Arguments.of(TokenType.T_TYPE, "string", 3),
				Arguments.of(TokenType.T_OPERATOR, "eq", 13),
				Arguments.of(TokenType.T_NULL, "null()", 22),
				Arguments.of(TokenType.T_EMPTY, "empty()", 31),
				Arguments.of(TokenType.T_TRUE, "true()", 45),
				Arguments.of(TokenType.T_FALSE, "false()", 8)
		);
	}

	static private Stream<Arguments> getTestTokenTypesSuccessData() {
		return Stream.of(
				Arguments.of(TokenType.T_END, new TokenType[]{TokenType.T_END}),
				Arguments.of(TokenType.T_INTEGER, new TokenType[]{TokenType.T_INTEGER, TokenType.T_FLOAT}),
				Arguments.of(TokenType.T_FLOAT, new TokenType[]{TokenType.T_FLOAT}),
				Arguments.of(TokenType.T_STRING, new TokenType[]{TokenType.T_STRING, TokenType.T_GLOB}),
				Arguments.of(TokenType.T_DATE, new TokenType[]{TokenType.T_DATE}),
				Arguments.of(TokenType.T_GLOB, new TokenType[]{TokenType.T_GLOB}),
				Arguments.of(TokenType.T_CLOSE_PARENTHESIS, new TokenType[]{TokenType.T_CLOSE_PARENTHESIS}),
				Arguments.of(TokenType.T_OPEN_PARENTHESIS, new TokenType[]{TokenType.T_OPEN_PARENTHESIS}),
				Arguments.of(TokenType.T_COMMA, new TokenType[]{TokenType.T_COMMA}),
				Arguments.of(TokenType.T_AMPERSAND, new TokenType[]{TokenType.T_AMPERSAND}),
				Arguments.of(TokenType.T_PIPE, new TokenType[]{TokenType.T_PIPE, TokenType.T_PLUS}),
				Arguments.of(TokenType.T_PLUS, new TokenType[]{TokenType.T_PLUS}),
				Arguments.of(TokenType.T_MINUS, new TokenType[]{TokenType.T_MINUS}),
				Arguments.of(TokenType.T_COLON, new TokenType[]{TokenType.T_COLON, TokenType.T_PLUS, TokenType.T_MINUS}),
				Arguments.of(TokenType.T_TYPE, new TokenType[]{TokenType.T_TYPE}),
				Arguments.of(TokenType.T_OPERATOR, new TokenType[]{TokenType.T_OPERATOR}),
				Arguments.of(TokenType.T_NULL, new TokenType[]{TokenType.T_NULL, TokenType.T_EMPTY, TokenType.T_FALSE}),
				Arguments.of(TokenType.T_EMPTY, new TokenType[]{TokenType.T_EMPTY}),
				Arguments.of(TokenType.T_TRUE, new TokenType[]{TokenType.T_TRUE, TokenType.T_NULL, TokenType.T_EMPTY}),
				Arguments.of(TokenType.T_FALSE, new TokenType[]{TokenType.T_FALSE})
		);
	}

	static private Stream<Arguments> getTestTokenTypesFailureData() {
		return Stream.of(
				Arguments.of(TokenType.T_END, new TokenType[]{TokenType.T_INTEGER}),
				Arguments.of(TokenType.T_INTEGER, new TokenType[]{TokenType.T_FLOAT}),
				Arguments.of(TokenType.T_FLOAT, new TokenType[]{TokenType.T_END}),
				Arguments.of(TokenType.T_STRING, new TokenType[]{TokenType.T_END}),
				Arguments.of(TokenType.T_DATE, new TokenType[]{TokenType.T_OPERATOR}),
				Arguments.of(TokenType.T_GLOB, new TokenType[]{TokenType.T_AMPERSAND}),
				Arguments.of(TokenType.T_CLOSE_PARENTHESIS, new TokenType[]{TokenType.T_OPEN_PARENTHESIS}),
				Arguments.of(TokenType.T_OPEN_PARENTHESIS, new TokenType[]{TokenType.T_INTEGER, TokenType.T_COLON}),
				Arguments.of(TokenType.T_COMMA, new TokenType[]{TokenType.T_DATE}),
				Arguments.of(TokenType.T_AMPERSAND, new TokenType[]{TokenType.T_TYPE}),
				Arguments.of(TokenType.T_PIPE, new TokenType[]{TokenType.T_EMPTY}),
				Arguments.of(TokenType.T_PLUS, new TokenType[]{TokenType.T_PIPE}),
				Arguments.of(TokenType.T_MINUS, new TokenType[]{TokenType.T_EMPTY}),
				Arguments.of(TokenType.T_COLON, new TokenType[]{TokenType.T_INTEGER}),
				Arguments.of(TokenType.T_TYPE, new TokenType[]{TokenType.T_FALSE, TokenType.T_DATE}),
				Arguments.of(TokenType.T_OPERATOR, new TokenType[]{TokenType.T_DATE}),
				Arguments.of(TokenType.T_NULL, new TokenType[]{TokenType.T_TRUE}),
				Arguments.of(TokenType.T_EMPTY, new TokenType[]{TokenType.T_COLON, TokenType.T_AMPERSAND}),
				Arguments.of(TokenType.T_TRUE, new TokenType[]{TokenType.T_FALSE, TokenType.T_TYPE}),
				Arguments.of(TokenType.T_FALSE, new TokenType[]{TokenType.T_TYPE})
		);
	}

	static private Stream<Arguments> getTestTokenTypesValuesSuccessData() {
		return Stream.of(
				Arguments.of(TokenType.T_INTEGER, "10", new TokenType[]{TokenType.T_INTEGER, TokenType.T_FLOAT}, new String[]{"10"}),
				Arguments.of(TokenType.T_FLOAT, "45.31", new TokenType[]{TokenType.T_FLOAT}, new String[]{"45.31"}),
				Arguments.of(TokenType.T_STRING, "Code", new TokenType[]{TokenType.T_STRING}, new String[]{"Code"}),
				Arguments.of(TokenType.T_GLOB, "*4053?", new TokenType[]{TokenType.T_GLOB}, new String[]{"*4053?"}),
				Arguments.of(TokenType.T_OPERATOR, "eq", new TokenType[]{TokenType.T_OPERATOR}, new String[]{"eq", "ne", "gt", "or"})
		);
	}

	static private Stream<Arguments> getTestTokenTypesValuesFailureData() {
		return Stream.of(
				Arguments.of(TokenType.T_INTEGER, "10", new TokenType[]{TokenType.T_INTEGER, TokenType.T_FLOAT}, new String[]{"10.23"}),
				Arguments.of(TokenType.T_FLOAT, "45.31", new TokenType[]{TokenType.T_INTEGER}, new String[]{"41"}),
				Arguments.of(TokenType.T_STRING, "Code", new TokenType[]{TokenType.T_GLOB}, new String[]{"?ode"}),
				Arguments.of(TokenType.T_GLOB, "*4053?", new TokenType[]{TokenType.T_STRING}, new String[]{"1240530"}),
				Arguments.of(TokenType.T_OPERATOR, "and", new TokenType[]{TokenType.T_OPERATOR}, new String[]{"eq", "ne", "gt", "or"})
		);
	}

	@ParameterizedTest
	@MethodSource("getTokenParamsData")
	void toString_Success(TokenType tokenType, String value, int start) {
		token = new Token(tokenType, value, start, start + value.length());
		String expected = String.format("%s(%s)", tokenType.name(), value);
		assertEquals(expected, token.toString());
	}

	@ParameterizedTest
	@MethodSource("getTokenParamsData")
	void getValue(TokenType tokenType, String value, int start) {
		token = new Token(tokenType, value, start, start + value.length());
		assertEquals(value, token.getValue());
	}

	@ParameterizedTest
	@MethodSource("getTokenParamsData")
	void getType(TokenType tokenType, String value, int start) {
		token = new Token(tokenType, value, start, start + value.length());
		assertEquals(tokenType, token.getType());
	}

	@ParameterizedTest
	@MethodSource("getTokenParamsData")
	void getStart(TokenType tokenType, String value, int start) {
		token = new Token(tokenType, value, start, start + value.length());
		assertEquals(start, token.getStart());

	}

	@ParameterizedTest
	@MethodSource("getTokenParamsData")
	void getEnd(TokenType tokenType, String value, int start) {
		token = new Token(tokenType, value, start, start + value.length());
		assertEquals(start + value.length(), token.getEnd());
	}

	@ParameterizedTest
	@MethodSource("getTestTokenTypesSuccessData")
	void test_TokenTypes_Success(TokenType tokenType, TokenType[] tokenTypes) {
		token = new Token(tokenType, "", 0, 0);
		assertTrue(token.test(tokenTypes));
	}

	@ParameterizedTest
	@MethodSource("getTestTokenTypesFailureData")
	void test_TokenTypes_Failure(TokenType tokenType, TokenType[] tokenTypes) {
		token = new Token(tokenType, "", 0, 0);
		assertFalse(token.test(tokenTypes));
	}

	@ParameterizedTest
	@MethodSource("getTestTokenTypesValuesSuccessData")
	void test_TokenTypesValues_Success(TokenType tokenType, String value, TokenType[] tokenTypes, String[] values) {
		token = new Token(tokenType, value, 0, value.length());
		assertTrue(token.test(tokenTypes, values));
	}

	@ParameterizedTest
	@MethodSource("getTestTokenTypesValuesFailureData")
	void test_TokenTypesValues_Failure(TokenType tokenType, String value, TokenType[] tokenTypes, String[] values) {
		token = new Token(tokenType, value, 0, value.length());
		assertFalse(token.test(tokenTypes, values));
	}
}