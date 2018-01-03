package org.victorynox.rql.node.operator;

import org.victorynox.rql.node.AbstractQueryNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	protected List<? extends AbstractQueryNode> queries;

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
	public AbstractLogicalNode(List<? extends AbstractQueryNode> queries) {
		this.queries = queries;
	}

	/**
	 *  Default queries getter.
	 * @return <code>ArrayList</code> with query node.
	 */
	public List<? extends AbstractQueryNode> getQueries() {
		return queries;
	}

	/**
	 * Default queries setter
	 * @param queries array list with query node.
	 */
	public void setQueries(List<? extends AbstractQueryNode> queries) {
		this.queries = queries;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		AbstractLogicalNode that = (AbstractLogicalNode) o;
		return Objects.equals(queries, that.queries);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), queries);
	}
}
