package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.exception.LexerNotFoundTokenException;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * @author victorynox
 * @version 0.1
 */
public class GlobLexer implements Lexer {

	@Override
	public Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException, SyntaxErrorException {
		throw new Exception();
	}

}
