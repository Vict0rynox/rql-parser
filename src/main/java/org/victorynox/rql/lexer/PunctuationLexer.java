package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.LexerNotFoundTokenException;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * Tokenize singe system punctuation char.
 * @author victorynox
 * @version 0.1
 */
public class PunctuationLexer implements Lexer {
	@Override
	public Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException, SyntaxErrorException {
		String test = code.substring(cursor, 1);

		switch (test) {
			case "&":
				return new Token(TokenType.T_AMPERSAND, test, cursor, cursor + 1);
			case "|":
				return new Token(TokenType.T_PIPE, test, cursor, cursor + 1);
			case ",":
				return new Token(TokenType.T_COMMA, test, cursor, cursor + 1);
			case "(":
				return new Token(TokenType.T_OPEN_PARENTHESIS, test, cursor, cursor + 1);
			case ")":
				return new Token(TokenType.T_CLOSE_PARENTHESIS, test, cursor, cursor + 1);
			case ":":
				return new Token(TokenType.T_COLON, test, cursor, cursor + 1);
		}
		throw new LexerNotFoundTokenException();
	}
}
