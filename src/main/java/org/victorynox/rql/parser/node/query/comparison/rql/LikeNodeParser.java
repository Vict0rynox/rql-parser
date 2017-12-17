package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.LikeNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public class LikeNodeParser<V> extends AbstractRqlNodeParser<LikeNode<V>, V> {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public LikeNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected LikeNode<V> createNode(String filed, V value) {
		return new LikeNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "like";
	}
}
