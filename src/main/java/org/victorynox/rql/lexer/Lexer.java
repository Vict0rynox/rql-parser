package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Optional;

/**
 * @author victorynox
 * @version 0.1
 */
public interface Lexer {

	/**
	 * Get token at position in string.
	 * @param code - string with data
	 * @param cursor - position in string
	 * @return <code> Optional<Token> </code>
	 * @throws SyntaxErrorException - if has error by tokenize.
	 */
	Optional<Token> getTokenAt(String code, int cursor) throws SyntaxErrorException;
}
