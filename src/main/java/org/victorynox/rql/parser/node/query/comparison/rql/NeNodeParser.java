package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.NeNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;
import org.victorynox.rql.parser.value.ScalarValue;

/**
 * @author victorynox
 * @version 0.1
 */
public class NeNodeParser<V extends ScalarValue> extends AbstractRqlNodeParser<NeNode<V>, V> {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public NeNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected NeNode<V> createNode(String filed, V value) {
		return new NeNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "ne";
	}
}
