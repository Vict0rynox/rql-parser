package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.GeNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public class GeNodeParser<V> extends AbstractRqlNodeParser<GeNode<V>, V> {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public GeNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected GeNode<V> createNode(String filed, V value) {
		return new GeNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "ge";
	}
}
