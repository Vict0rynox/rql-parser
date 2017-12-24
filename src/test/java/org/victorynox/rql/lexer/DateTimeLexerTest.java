package org.victorynox.rql.lexer;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.rules.ExpectedException;
import org.victorynox.rql.Token;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeLexerTest {
	private DateTimeLexer lexer;

	@Rule
	private ExpectedException thrown = ExpectedException.none();

	private static Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("2012-01-10T15:10:02Z),eq(name,value)", "2012-01-10T15:10:02"),
				Arguments.of("2010-10-10T10:10:12Z", "2010-10-10T10:10:12")
		);
	}

	private static Stream<String> getTokenAtInvalidCodeSystaxExceptionData() {
		return Stream.of(
				"1000-10-10T10:91:99Z",
				"0000-00-00T00:00:00Z",
				"0000-10-10T10:10:10Z",
				"1000-00-10T10:10:10Z",
				"1000-00-10T10:99:10Z",
				"1000-14-10T10:12:10Z",
				"1000-11-44T10:12:10Z"
		);
	}

	private static Stream<String> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				"eq(name,value)",
				"and(eq(a,q),eq(b,q))",
				"",
				"null()",
				"20-1020-10T10:09:12Z",
				"20-20 10 10:09:12Z",
				"10 10 10 10:09:12Z",
				"10 10 10 10:09:12Z",
				"20-1020-10 _ 10:09:12Z",
				"2010-20-10_10:09:12Z",
				"2010-20-10 10:09:12Z",
				"20-20-10 10:09:12Z"
		);
	}

	@BeforeEach
	void startUp() {
		lexer = new DateTimeLexer();
	}

	@ParameterizedTest
	@MethodSource("getTokenAtValidCodeData")
	void getTokenAt_ValidCode(String code, String expectedValue) {
		try {
			Optional<Token> token = lexer.getTokenAt(code, 0);
			assertTrue(token.isPresent());
			assertEquals(expectedValue, token.get().getValue());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}

	@ParameterizedTest()
	@MethodSource("getTokenAtInvalidCodeSystaxExceptionData")
	void getTokenAt_DateInvalid_SyntaxException(String code) throws SyntaxErrorException {
		assertThrows(SyntaxErrorException.class, () -> lexer.getTokenAt(code, 0));
	}

	@ParameterizedTest
	@MethodSource("getTokenAtInvalidCodeTokenNotFoundData")
	void getTokenAt_InvalidCode_TokenNotFound(String code) {
		try {
			Optional<Token> token = lexer.getTokenAt(code, 0);
			assertFalse(token.isPresent());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}
}