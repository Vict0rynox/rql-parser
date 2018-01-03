package org.victorynox.rql.node.operator;

import org.victorynox.rql.parser.value.ScalarValue;

import java.util.Objects;

/**
 * Abstraction for scalar node
 * @author vicotrynox
 * @version 0.1
 * @param <T> node value type
 */
public abstract class AbstractScalarNode<T> extends AbstractComparisonNode<T> {

	/**
	 * Node value
	 */
	protected T value;

	/**
	 *
	 * @param field - name which been searched
	 * @param value - appropriate searched value
	 * */
	public AbstractScalarNode(String field, T value) {
		this.filed = field;
		this.value = value;
	}

	/**
	 * Default value getter
	 * @return node value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Default value setter
	 * @param value - node searched value
	 */
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		AbstractScalarNode<?> that = (AbstractScalarNode<?>) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), value);
	}
}
