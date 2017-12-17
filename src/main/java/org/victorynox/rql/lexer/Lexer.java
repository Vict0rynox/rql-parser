package org.victorynox.rql.lexer;

import org.victorynox.rql.ParserException;
import org.victorynox.rql.Token;

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
	 * @throws ParserException - if Token not getted.
	 */
	Token getTokenAt(String code, int cursor) throws ParserException;
}
