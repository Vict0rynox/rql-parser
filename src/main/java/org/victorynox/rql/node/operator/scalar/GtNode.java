package org.victorynox.rql.node.operator.scalar;

import org.victorynox.rql.node.operator.AbstractScalarNode;

/**
 * Gt - grate then (>).
 * Need to search object where value grate than given.
 * @author victorynox
 * @version 0.1
 * @param <T> type value
 */
public class GtNode<T> extends AbstractScalarNode<T> {
	/**
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 */
	public GtNode(String field, T value) {
		super(field, value);
	}

	@Override
	public String getNodeName() {
		return "gt";
	}
}
