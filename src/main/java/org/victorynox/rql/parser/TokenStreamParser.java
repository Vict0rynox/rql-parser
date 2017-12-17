package org.victorynox.rql.parser;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * @author victorynox
 * @version 0.1
 */
public interface TokenStreamParser<T> {
	/**
	 * Parse tokenize string
	 * @param tokenStream Tokenize string
	 * @throws SyntaxErrorException when <code>TokenStream</code> is incorrect.
	 * @return T type if parse return.
	 */
	T parse(TokenStreamIterator tokenStream) throws SyntaxErrorException;
}
