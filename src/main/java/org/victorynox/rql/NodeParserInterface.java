package org.victorynox.rql;

/**
 * @author victorynox
 * @version 0.1
 */
public interface NodeParserInterface extends SubParserInterface<AbstractNode> {

	/**
	 * Check token stream to support.
	 * @return true if Parser support this tokens
	 */
	boolean supports(TokenStream tokenStream);
}
