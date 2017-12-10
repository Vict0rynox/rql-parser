package org.victorynox.rql.node.operator.scalar;

import org.victorynox.rql.node.operator.AbstractScalarNode;

/**
 * Ne - not equals (!=).
 * Need for search object were the field not equals given value.
 * @author victorynox
 * @version 0.1
 * @param <T> type value
 */
public class NeNode<T> extends AbstractScalarNode<T> {
	/**
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 */
	public NeNode(String field, T value) {
		super(field, value);
	}

	@Override
	public String getNodeName() {
		return "ne";
	}
}
