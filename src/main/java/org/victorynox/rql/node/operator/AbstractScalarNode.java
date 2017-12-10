package org.victorynox.rql.node.operator;

/**
 * Abstraction for scalar node
 * @author vicotrynox
 * @version 0.1
 * @param <T> node value type
 */
public abstract class AbstractScalarNode<T> extends AbstractComparisonNode {

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
}
