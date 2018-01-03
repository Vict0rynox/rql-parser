package org.victorynox.rql.node;

/**
 * Basic abstract class for Rql Node;
 *
 * @author vicotrynox
 * @version 0.1
 */
public abstract class AbstractNode implements Node {
	@Override
	public int hashCode() {
		return getNodeName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj == this || obj != null && obj.getClass() == getClass() &&
				((AbstractNode) obj).getNodeName().equals(getNodeName())
		);
	}
}

