package org.victorynox.rql;

import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * @author victorynox
 * @version 0.1
 */
public interface SubParserInterface<T> {
	/**
	 * Parse tokenize string
	 * @param tokenStream Tokenize string
	 * @throws SyntaxErrorException
	 * @return T type if parse return.
	 */
	T parse(TokenStream tokenStream) throws SyntaxErrorException;
}
