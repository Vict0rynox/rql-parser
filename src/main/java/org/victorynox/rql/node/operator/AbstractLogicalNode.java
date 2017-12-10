package org.victorynox.rql.node.operator;

import org.victorynox.rql.node.AbstractQueryNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction for logical node
 *
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractLogicalNode extends AbstractQueryNode {

	/**
	 * Array with query
	 */
	protected List<AbstractQueryNode> queries;

	/**
	 * Init <code>SelectNode</code> by empty array.
	 */
	public AbstractLogicalNode() {
		this.queries = new ArrayList<>();
	}

	/**
	 * Init <code>SelectNode</code> by queries.
	 * @param queries collection with query nodes
	 */
	public AbstractLogicalNode(List<AbstractQueryNode> queries) {
		this.queries = queries;
	}

	/**
	 *  Default queries getter.
	 * @return <code>ArrayList</code> with query node.
	 */
	public List<AbstractQueryNode> getQueries() {
		return queries;
	}

	/**
	 * Default queries setter
	 * @param queries array list with query node.
	 */
	public void setQueries(List<AbstractQueryNode> queries) {
		this.queries = queries;
	}
}
