package org.victorynox.rql.node.operator.array;

import org.victorynox.rql.node.operator.AbstractArrayNode;

import java.util.List;

/**
 * Select object where the specified property value is not int the provided array.
 * @author vicotrynox
 * @version 0.1
 * @param <T> value type
 */
public class OutNode<T> extends AbstractArrayNode<T> {

	/**
	 * Init node with filed and values
	 *
	 * @param filed name of searched filed
	 * @param values <code>List</code> values that will not contain the filed.
	 */
	public OutNode(String filed, List<T> values) {
		super(filed, values);
	}

	/**
	 * Init node with filed and init values by empty <code>ArrayList</code>
	 *
	 * @param filed name of searched filed
	 */
	public OutNode(String filed) {
		super(filed);
	}

	@Override
	public String getNodeName() {
		return "out";
	}
}
