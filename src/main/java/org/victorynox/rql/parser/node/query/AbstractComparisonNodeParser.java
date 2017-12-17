package org.victorynox.rql.parser.node.query;

import org.victorynox.rql.parser.node.NodeParser;
import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.AbstractComparisonNode;

/**
 * Abstraction comparision nodeParser
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractComparisonNodeParser<T extends AbstractComparisonNode, V> implements NodeParser<T> {

	/**
	 * Filed name parser
	 */
	protected TokenStreamParser<String> filedNameParser;

	/**
	 * Value parser
	 */
	protected TokenStreamParser<V> valueParser;

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public AbstractComparisonNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		this.filedNameParser = filedNameParser;
		this.valueParser = valueParser;
	}

	/**
	 * Create node with data
	 * @param filed node filed name
	 * @param value node value
	 * @return Node
	 */
	abstract protected T createNode(String filed, V value);

	/**
	 *
	 * @return operator name
	 */
	abstract protected String getOperatorName();
}
