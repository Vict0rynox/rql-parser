package org.victorynox.rql.parser.node.query;

import org.victorynox.rql.AbstractNode;
import org.victorynox.rql.NodeParserInterface;
import org.victorynox.rql.SubParserInterface;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.operator.AbstractComparisonNode;

/**
 * Abstraction comparision nodeParser
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractComparsionOperatorNodeParser implements NodeParserInterface {

	/**
	 * Filed name parser
	 */
	protected SubParserInterface<String> filedNameParser;

	/**
	 * Value parser
	 */
	protected SubParserInterface valueParser;

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public AbstractComparsionOperatorNodeParser(SubParserInterface<String> filedNameParser, SubParserInterface valueParser) {
		this.filedNameParser = filedNameParser;
		this.valueParser = valueParser;
	}

	/**
	 * Create node with data
	 * @param filed node filed name
	 * @param value node value
	 * @param <T> value type
	 * @return Node
	 */
	abstract protected <T>AbstractComparisonNode createNode(String filed, T value);

	/**
	 *
	 * @return operator name
	 */
	abstract protected String getOperatorName();
}
