package org.victorynox.rql.node;

/**
 * Used to sort the result-set in ascending or descending order.
 * ACS - ascending
 * DESC - descending
 * @author vicotrynox
 * @version 0.1
 */
public enum SortType {
	SORT_ACS(1),
	SORT_DESC(-1);

	/**
	 * Save sort type value
	 */
	private int sortType;

	/**
	 * init enum with sort type
	 * @param sortType value of sort type.
	 */
	SortType(int sortType) {
		this.sortType = sortType;
	}

	/**
	 * Default sortType getter.
	 * @return sortType
	 */
	public int getSortType()
	{
		return sortType;
	}
}
