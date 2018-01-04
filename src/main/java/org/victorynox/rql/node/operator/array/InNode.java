package org.victorynox.rql.node.operator.array;

import org.victorynox.rql.node.operator.AbstractArrayNode;

import java.util.List;

/**
 * Select object where the specified property value is in the provided array
 * @author victorynox
 * @version 0.1
 * @param <T> value types
 */
public class InNode<T> extends AbstractArrayNode<T> {

	/**
	 * Init node with filed and values
	 *
	 * @param filed  name of searched filed
	 * @param values - <code>List</code> values that will be contain the filed
	 */
	public InNode(String filed, List<T> values) {
		super(filed, values);
	}

	/**
	 * Init node with filed and init values by empty <code>ArrayList</code>
	 *
	 * @param filed name of searched filed
	 */
	public InNode(String filed) {
		super(filed);
	}

	@Override
	public String getNodeName() {
		return "in";
	}
}