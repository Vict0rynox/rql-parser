package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;

import java.util.Optional;

/**
 * Tokenize sort type
 * @author victorynox
 * @version 0.1
 */
public class SortLexer implements Lexer {
	@Override
	public Optional<Token> getTokenAt(String code, int cursor) {
		String test = code.substring(cursor, cursor+1);
		switch (test) {
			case "+":
				return Optional.of(new Token(TokenType.T_PLUS, test, cursor, cursor + 1));
			case "-":
				return Optional.of(new Token(TokenType.T_MINUS, test, cursor, cursor + 1));
			default:
				return Optional.empty();
		}
	}
}
