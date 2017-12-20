package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.array.OutNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;
import org.victorynox.rql.parser.value.ScalarValue;

import java.util.List;

/**
 * Parser for OutNode
 * @author victorynox
 * @version 0.1
 */
public class OutNodeParser<V> extends AbstractRqlNodeParser<OutNode<V>, V> {

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public OutNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected OutNode<V> createNode(String filed, V value) {
		OutNode<V> outNode = new OutNode<>(filed);
		outNode.addValue(value);
		return outNode;
	}

	protected OutNode<V> createNode(String filed, List<V> value) {
		return new OutNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "out";
	}
}
