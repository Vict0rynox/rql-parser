package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.scalar.EqNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;
import org.victorynox.rql.parser.value.ScalarValue;

/**
 * @author victorynox
 * @version 0.1
 */
public class EqNodeParser<V extends ScalarValue> extends AbstractRqlNodeParser<EqNode<V>, V> {
	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public EqNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected EqNode<V> createNode(String filed, V value) {
		return new EqNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "eq";
	}
}
