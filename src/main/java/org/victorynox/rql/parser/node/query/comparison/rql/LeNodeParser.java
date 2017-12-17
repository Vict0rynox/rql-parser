package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.LeNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public class LeNodeParser<V> extends AbstractRqlNodeParser<LeNode<V>, V> {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public LeNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected LeNode<V> createNode(String filed, V value) {
		return new LeNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "le";
	}
}
