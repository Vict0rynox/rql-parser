package org.victorynox.rql.node;

import java.util.Objects;

/**
 * Value class for storage Limit and Offset for query.
 * @author victorynox
 * @version 0.1
 */
public class LimitNode extends AbstractNode {

	/**
	 * staged a number of element limit in query result
	 */
	protected int limit;

	/**
	 * staged a number of skipped element by search
	 */
	protected int offset;

	/**
	 * Minimal constructor. Initialize limit attribute
	 * @param limit number of element limit in query result
	 */
	public LimitNode(int limit)
	{
		this.limit = limit;
	}

	/**
	 * Constructor initialize limit and offset attribute.
	 * @param limit number of element limit in query result
	 * @param offset number of skipped element by search
	 */
	public LimitNode(int limit, int offset)
	{
		this.limit = limit;
		this.offset = offset;
	}

	@Override
	public String getNodeName() {
		return "limit";
	}

	/**
	 * Default getter for limit
	 * @return limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Default setter for limit. Init limit param
	 * @param limit number of element limit in query result
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Default getter for offset
	 * @return offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Default setter for offset
	 * @param offset number of skipped element by search
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		LimitNode limitNode = (LimitNode) o;
		return limit == limitNode.limit &&
				offset == limitNode.offset;
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), limit, offset);
	}
}
