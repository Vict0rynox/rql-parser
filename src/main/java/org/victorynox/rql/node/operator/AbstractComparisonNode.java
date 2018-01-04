package org.victorynox.rql.node.operator;

import org.victorynox.rql.node.AbstractQueryNode;

import java.util.Objects;

/**
 * ComparisonNode abstraction.
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractComparisonNode extends AbstractQueryNode {

	/**
	 * Filed name
	 */
	protected String filed;

	/**
	 * Default field getter
	 * @return filed name
	 */
	public String getFiled() {
		return filed;
	}

	/**
	 * Default field setter
	 * @param filed name
	 */
	public void setFiled(String filed) {
		this.filed = filed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		AbstractComparisonNode that = (AbstractComparisonNode) o;
		return Objects.equals(filed, that.filed);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), filed);
	}
}
