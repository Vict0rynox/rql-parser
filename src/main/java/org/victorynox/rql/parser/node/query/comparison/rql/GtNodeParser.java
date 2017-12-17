package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.GtNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public class GtNodeParser<V> extends AbstractRqlNodeParser<GtNode<V>, V> {

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public GtNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected GtNode<V> createNode(String filed, V value) {
		return new GtNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "gt";
	}
}
