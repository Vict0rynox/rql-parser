package org.victorynox.rql.node.operator.scalar;

import org.victorynox.rql.node.operator.AbstractScalarNode;

/**
 * Eq - equals node (=).
 * Need to search for a object with a field with the corresponding value.
 * @author victorynox
 * @version 0.1
 * @param <T> type value
 */
public class EqNode<T> extends AbstractScalarNode<T>{

	/**
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 */
	public EqNode(String field, T value) {
		super(field, value);
	}

	@Override
	public String getNodeName() {
		return "eq";
	}
}
