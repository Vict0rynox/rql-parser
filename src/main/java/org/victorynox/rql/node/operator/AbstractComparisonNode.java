package org.victorynox.rql.node.operator;

import org.victorynox.rql.node.AbstractQueryNode;

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
}
