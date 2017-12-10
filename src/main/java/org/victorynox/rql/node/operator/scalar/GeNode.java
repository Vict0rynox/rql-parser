package org.victorynox.rql.node.operator.scalar;

import org.victorynox.rql.node.operator.AbstractScalarNode;

/**
 * Ge - grate than or equals (>=).
 * Need to search object where value grates or equal that given.
 * @author victorynox
 * @version 0.1
 * @param <T> type value
 */
public class GeNode<T> extends AbstractScalarNode<T> {

	/**
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 */
	public GeNode(String field, T value) {
		super(field, value);
	}

	@Override
	public String getNodeName() {
		return "ge";
	}
}
