package org.victorynox.rql.lexer;

import org.victorynox.rql.ParserException;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.LexerNotFoundTokenException;

/**
 * Lexer for tokenize constant value
 * empty, null, true, false
 * @author victorynox
 * @version 0.1
 */
public class ConstantLexer implements Lexer {

	@Override
	public Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException {
		String test = code.substring(cursor, 7);
		if(test.equals("false()")) {
			return new Token(TokenType.T_FALSE, test, cursor, cursor + 7);
		} else if (test.equals("empty()")) {
			return new Token(TokenType.T_EMPTY, test, cursor, cursor + 7);
		}

		test = code.substring(cursor, 6);
		if(test.equals("true()")) {
			return new Token(TokenType.T_TRUE, test, cursor, cursor + 6);
		} else if (test.equals("null()")) {
			return new Token(TokenType.T_NULL, test, cursor, cursor + 6);
		}
		//message: token not found.
		throw new LexerNotFoundTokenException();
	}
}
