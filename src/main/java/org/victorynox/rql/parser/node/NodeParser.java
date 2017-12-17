package org.victorynox.rql.parser.node;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.parser.TokenStreamParser;

/**
 * @author victorynox
 * @version 0.1
 */
public interface NodeParser<T extends AbstractNode> extends TokenStreamParser<T> {

	/**
	 * Check token stream to support.
	 * @return true if Parser support this tokens
	 */
	boolean supports(TokenStreamIterator tokenStream);
}
