package org.victorynox.rql.node.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstraction for array node
 *
 * @param <T> node values type
 */
public abstract class AbstractArrayNode<T> extends AbstractComparisonNode {

	/**
	 * Array values list with type T
	 */
	protected List<T> values;

	/**
	 * Init node with filed and values
	 *
	 * @param filed  name of searched filed
	 * @param values appropriate searched values <code>List</code>
	 */
	public AbstractArrayNode(String filed, List<T> values) {
		this.filed = filed;
		this.values = values;
	}

	/**
	 * Init node with filed and init values by empty <code>ArrayList</code>
	 *
	 * @param filed name of searched filed
	 */
	public AbstractArrayNode(String filed) {
		this.filed = filed;
		this.values = new ArrayList<>();
	}

	/**
	 * Default values setter
	 * @return values list
	 */
	public List<T> getValues() {
		return values;
	}

	/**
	 * Default values setter.
	 * @param values searched values <code>List</code>
	 */
	public void setValues(List<T> values) {
		this.values = values;
	}

	/**
	 * Add value to List
	 * @param value searched value.
	 * @return this
	 */
	public AbstractArrayNode<T> addValue(T value)
	{
		this.values.add(value);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		AbstractArrayNode<?> that = (AbstractArrayNode<?>) o;
		return Objects.equals(values, that.values);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), values);
	}
}
