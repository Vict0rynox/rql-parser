package org.victorynox.rql.node.operator.logical;

import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.AbstractLogicalNode;

import java.util.List;

/**
 * Collected query node with AND rules
 * @author victorynox
 * @version 0.1
 */
public class AndNode extends AbstractLogicalNode {

	/**
	 * Default AndNode constructor
	 */
	public AndNode() {
		super();
	}

	/**
	 * @param queries <code>List</code> of query node
	 */
	public AndNode(List<AbstractQueryNode> queries) {
		super(queries);
	}

	@Override
	public String getNodeName() {
		return "and";
	}
}
