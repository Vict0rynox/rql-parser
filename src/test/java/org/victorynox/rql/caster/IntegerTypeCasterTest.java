package org.victorynox.rql.caster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.Token;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.victorynox.rql.TokenType.*;
import static org.victorynox.rql.TokenType.T_FLOAT;
import static org.victorynox.rql.TokenType.T_INTEGER;

class IntegerTypeCasterTest {

	private IntegerTypeCaster typeCaster;

	@BeforeEach
	void startUp()
	{
		typeCaster = new IntegerTypeCaster();
	}

	/**
	 * TODO: rewrite with moc
	 * Return <code>Stream</code> <code>Argument</code> with expected data, and value
	 * @return Stream with dataProviding
	 */
	static private Stream<Arguments> getTypeCastData()
	{
		return Stream.of(
				Arguments.of(new Token(T_DATE, "2017-10-10T10:12:01", 0, 6), 2017_10_10_10),
				Arguments.of(new Token(T_DATE, "0000-00-00T00:00:00", 0, 0), 0),
				Arguments.of(new Token(T_NULL, "null()", 0,6), 0),
				Arguments.of(new Token(T_TRUE, "true()", 0, 6), 1),
				Arguments.of(new Token(T_FALSE, "false()", 0, 7), 0),
				Arguments.of(new Token(T_EMPTY, "empty()", 0, 7), 0),
				Arguments.of(new Token(T_STRING, "0", 0, 1), 0),
				Arguments.of(new Token(T_INTEGER, "1", 0, 1), 1),
				Arguments.of(new Token(T_FLOAT, "0.0", 0, 3), 0),
				Arguments.of(new Token(T_INTEGER, "1", 0, 1), 1),
				Arguments.of(new Token(T_FLOAT, "1.2", 0, 3), 1)
		);
	}

	@ParameterizedTest
	@MethodSource("getTypeCastData")
	void typeCast(Token token, Integer expectedResult) {
		Integer result = typeCaster.typeCast(token);
		assertEquals(expectedResult, result);
	}

	@Test
	void getType() {
		assertEquals(typeCaster.getType(), Integer.class);
	}
}