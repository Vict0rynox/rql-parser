package org.victorynox.rql.node.operator.logical;

import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.AbstractLogicalNode;

import java.util.List;

/**
 * Collected query node with NOT rules
 * @author victorynox
 * @version 0.1
 */
public class NotNode extends AbstractLogicalNode {

	/**
	 * Default <code>NotNode</code> constructor.
	 */
	public NotNode() {
		super();
	}

	/**
	 *
	 * @param queries <code>List</code> of query node
	 */
	public NotNode(List<AbstractQueryNode> queries) {
		super(queries);
	}

	@Override
	public String getNodeName() {
		return "not";
	}
}
