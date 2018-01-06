package org.victorynox.rql;

import org.victorynox.rql.exception.UnknownNodeException;
import org.victorynox.rql.node.*;
import org.victorynox.rql.node.operator.logical.AndNode;

/**
 * @author victorynox
 * @version 0.1
 */
public class QueryBuilder {

	/**
	 * Query object
	 */
	protected Query query;

	/**
	 * Init query
	 */
	public QueryBuilder()
	{
		query = new Query();
	}

	/**
	 *
	 * @return Query
	 */
	public Query build()
	{
		return query;
	}

	/**
	 * Add note in query
	 * @param node additional node.
	 */
	public QueryBuilder addNode(AbstractNode node) throws UnknownNodeException
	{
		if(node instanceof SelectNode) {
			return addSelect((SelectNode)node);
		} else if (node instanceof AbstractQueryNode) {
			return addQuery((AbstractQueryNode)node);
		} else if (node instanceof SortNode) {
			return addSort((SortNode)node);
		} else if (node instanceof LimitNode) {
			return addLimit((LimitNode)node);
		}
		throw new UnknownNodeException("Find unknown node with name:" + node.getNodeName());
	}

	/**
	 * Set <code>LimitNode</code> to query
	 * @param node limit
	 */
	private QueryBuilder addLimit(LimitNode node) {
		query.setLimitNode(node);
		return this;
	}

	/**
	 * Set <code>SortNode</code> to query
	 * @param node sort
	 */
	private QueryBuilder addSort(SortNode node) {
		query.setSortNode(node);
		return this;
	}

	/**
	 * Set <code>AbstractQueryNode</code> to query
	 * @param node query
	 */
	private QueryBuilder addQuery(AbstractQueryNode node) {
		AbstractQueryNode queryNode  = query.getQueryNode();
		if(queryNode != null) {
			if(queryNode instanceof AndNode) {
				node = ((AndNode)queryNode).addQueryNode(node);
			} else {
				node = (new AndNode())
						.addQueryNode(queryNode)
						.addQueryNode(node);
			}
		}
		query.setQueryNode(node);
		return this;
	}

	/**
	 * Set <code>SelectNode</code> to query
	 * @param node select
	 */
	private QueryBuilder addSelect(SelectNode node) {
		query.setSelectNode(node);
		return this;
	}
}
