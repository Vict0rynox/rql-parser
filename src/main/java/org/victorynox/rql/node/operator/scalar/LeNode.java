package org.victorynox.rql.node.operator.scalar;

import org.victorynox.rql.node.operator.AbstractScalarNode;
import org.victorynox.rql.parser.value.ScalarValue;

/**
 * Le - less than or equals (<=).
 * Need to search object where value less or equal that given.
 * @author victorynox
 * @version 0.1
 * @param <T> type value
 */
public class LeNode<T extends ScalarValue> extends AbstractScalarNode<T> {
	/**
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 */
	public LeNode(String field, T value) {
		super(field, value);
	}

	@Override
	public String getNodeName() {
		return "le";
	}
}
