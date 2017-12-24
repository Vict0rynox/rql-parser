package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.Token;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConstantLexerTest {

	private ConstantLexer lexer;

	/**
	 * @return Valid data for getTokenAt_validCode
	 */
	static private Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("false()", "false()"),
				Arguments.of("empty()", "empty()"),
				Arguments.of("true()", "true()"),
				Arguments.of("null(),", "null()"),
				Arguments.of("empty(),false()", "empty()"),
				Arguments.of("true(),empty()", "true()")
		);
	}

	/**
	 * @return Valid data for getTokenAt_validCode
	 */
	static private Stream<String> getTokenAtInvalidCodeData() {
		return Stream.of(
				"eq(name,value)",
				"not()",
				",",
				"and(eq(name,value),eq(name,value))",
				"emtty(),fzlse()",
				"trqwe(),emsty()"
		);
	}

	@BeforeEach
	void startUp() {
		lexer = new ConstantLexer();
	}

	@ParameterizedTest
	@MethodSource("getTokenAtValidCodeData")
	void getTokenAt_validCode(String code, String expectedValue) {
		Optional<Token> token = lexer.getTokenAt(code, 0);
		assertTrue(token.isPresent());
		assertEquals(expectedValue,token.get().getValue());
	}

	@ParameterizedTest
	@MethodSource("getTokenAtInvalidCodeData")
	void getTokenAt_InvalidCode(String code) {
		Optional<Token> token = lexer.getTokenAt(code, 0);
		assertFalse(token.isPresent());
	}
}