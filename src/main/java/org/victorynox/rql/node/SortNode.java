package org.victorynox.rql.node;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Staged map of filed and sort type.
 * @author vicotrynox
 * @version  0.1
 */
public class SortNode extends AbstractNode {

	/**
	 * Staged map of filed name and sort type.
	 */
	protected Map<String, SortType> fieldsSort;

	/**
	 * Init fieldsSort by empty <code>HashMap</code>.
	 */
	public SortNode()
	{
		fieldsSort = new HashMap<>();
	}

	/**
	 * Init SortNode by collection with filed and sort type.
	 * @param fieldsSort collection with filed name and sort type
	 */
	public SortNode(Map<String, SortType> fieldsSort)
	{
		this.fieldsSort = fieldsSort;
	}

	/**
	 * Default fieldsSort getter.
	 * @return fieldsSort
	 */
	public Map<String, SortType> getFieldsSort() {
		return fieldsSort;
	}

	/**
	 * Add filed with sort type in collection
	 * @param filed name of filed which been sorted
	 * @param sortType filed sort type
	 * @return this
	 */
	public SortNode addFiledSort(String filed, SortType sortType) {
		this.fieldsSort.put(filed, sortType);
		return this;
	}

	@Override
	public String getNodeName() {
		return "sort";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		SortNode sortNode = (SortNode) o;
		return Objects.equals(fieldsSort, sortNode.fieldsSort);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), fieldsSort);
	}
}
