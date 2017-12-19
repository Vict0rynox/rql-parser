package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.LexerNotFoundTokenException;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * Tokenize sort type
 * @author victorynox
 * @version 0.1
 */
public class SortLexer implements Lexer {
	@Override
	public Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException, SyntaxErrorException {
		String test = code.substring(cursor, 1);
		switch (test) {
			case "+":
				return new Token(TokenType.T_PLUS, test, cursor, cursor + 1);
			case "-":
				return new Token(TokenType.T_MINUS, test, cursor, cursor + 1);
			default:
				throw new LexerNotFoundTokenException();
		}
	}
}
