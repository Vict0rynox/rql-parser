package org.victorynox.rql.node.operator.scalar;

import org.victorynox.rql.node.operator.AbstractScalarNode;
import org.victorynox.rql.parser.value.ScalarValue;

/**
 * Like - (*).
 * Need to search object where the field contains the given value.
 * @author victorynox
 * @version 0.1
 * @param <T> type value
 */
public class LikeNode<T> extends AbstractScalarNode<T> {
	/**
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 */
	public LikeNode(String field, T value) {
		super(field, value);
	}

	@Override
	public String getNodeName() {
		return "like";
	}
}
