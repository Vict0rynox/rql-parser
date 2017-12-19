package org.victorynox.rql.lexer;

import org.victorynox.rql.ParserException;
import org.victorynox.rql.Token;
import org.victorynox.rql.exception.LexerNotFoundTokenException;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * @author victorynox
 * @version 0.1
 */
public interface Lexer {

	/**
	 * Get token at position in string.
	 * @param code - string with data
	 * @param cursor - position in string
	 * @return Token
	 * @throws LexerNotFoundTokenException - if Token not found.
	 * @throws SyntaxErrorException - if has error by tokenize.
	 */
	Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException, SyntaxErrorException;
}
