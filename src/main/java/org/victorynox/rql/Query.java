package org.victorynox.rql;

import org.victorynox.rql.node.*;

/**
 * @author victorynox
 * @version 0.1
 */
public class Query extends AbstractNode
{

	/**
	 * Select node
	 */
	protected SelectNode selectNode;

	/**
	 * Query node
	 */
	protected AbstractQueryNode queryNode;

	/**
	 * Sort node
	 */
	protected SortNode sortNode;

	/**
	 * Limit node
	 */
	protected LimitNode limitNode;

	/**
	 * Default getter
	 * @return selectNode
	 */
	public SelectNode getSelectNode() {
		return selectNode;
	}

	/**
	 * Default selectNode setter
	 * @param selectNode node with selected fields name
	 */
	public void setSelectNode(SelectNode selectNode) {
		this.selectNode = selectNode;
	}

	/**
	 * Default getter
	 * @return queryNode
	 */
	public AbstractQueryNode getQueryNode() {
		return queryNode;
	}

	/**
	 * Default queryNode setter
	 * @param queryNode node with searched query
	 */
	public void setQueryNode(AbstractQueryNode queryNode) {
		this.queryNode = queryNode;
	}

	/**
	 * Default getter
	 * @return sortNode
	 */
	public SortNode getSortNode() {
		return sortNode;
	}

	/**
	 * Default sortNode setter
	 * @param sortNode - node with sort type
	 */
	public void setSortNode(SortNode sortNode) {
		this.sortNode = sortNode;
	}

	/**
	 * Default getter
	 * @return limitNode
	 */
	public LimitNode getLimitNode() {
		return limitNode;
	}

	/**
	 * Default limitNode setter
	 * @param limitNode node with result limit
	 */
	public void setLimitNode(LimitNode limitNode) {
		this.limitNode = limitNode;
	}

	@Override
	public String getNodeName() {
		return "query";
	}
}
