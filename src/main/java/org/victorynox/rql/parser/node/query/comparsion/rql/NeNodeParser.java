package org.victorynox.rql.parser.node.query.comparsion.rql;

import org.victorynox.rql.SubParserInterface;
import org.victorynox.rql.node.operator.AbstractComparisonNode;
import org.victorynox.rql.node.operator.scalar.NeNode;
import org.victorynox.rql.parser.node.query.comparsion.AbstractRqlNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public class NeNodeParser extends AbstractRqlNodeParser {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public NeNodeParser(SubParserInterface<String> filedNameParser, SubParserInterface valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected <T> AbstractComparisonNode createNode(String filed, T value) {
		return new NeNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "ne";
	}
}
