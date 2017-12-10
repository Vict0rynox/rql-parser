package org.victorynox.rql.node.operator.logical;

import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.AbstractLogicalNode;

import java.util.List;

/**
 * Collected query node with OR rules
 * @author victorynox
 * @version 0.1
 */
public class OrNode extends AbstractLogicalNode {

	/**
	 * Default <code>OrNode</code> constructor
	 */
	public OrNode() {
		super();
	}

	/**
	 *
	 * @param queries <code>List</code> of query node
	 */
	public OrNode(List<AbstractQueryNode> queries) {
		super(queries);
	}

	@Override
	public String getNodeName() {
		return "or";
	}
}
