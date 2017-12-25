package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.Token;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Base class for <code>Lexer</code> test
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractLexerTest {

	/**
	 * Lexer for test
	 */
	protected Lexer lexer;

	/**
	 * Init lexer.
	 */
	@BeforeEach
	abstract void startUp();

	static protected Stream<Arguments> getTokenAtValidCodeData()
	{
		return Stream.of();
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of();
	}

	@ParameterizedTest
	@MethodSource("getTokenAtValidCodeData")
	void getTokenAt_ValidCode(String code, int cursor, String expectedValue) {
		try {
			Optional<Token> token = lexer.getTokenAt(code, cursor);
			assertTrue(token.isPresent());
			assertEquals(expectedValue, token.get().getValue());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}

	@ParameterizedTest
	@MethodSource("getTokenAtInvalidCodeTokenNotFoundData")
	void getTokenAt_InvalidCode_TokenNotFound(String code, int cursor) {
		try {
			Optional<Token> token  = lexer.getTokenAt(code, cursor);
			assertFalse(token.isPresent());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}
}
