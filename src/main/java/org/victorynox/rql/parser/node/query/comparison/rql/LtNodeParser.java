package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.LtNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public class LtNodeParser<V> extends AbstractRqlNodeParser<LtNode<V>, V> {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public LtNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected LtNode<V> createNode(String filed, V value) {
		return new LtNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "lt";
	}
}
