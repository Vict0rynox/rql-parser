package org.victorynox.rql.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for storage fields which selected by query
 * @author victorynox
 * @version 0.1
 */
public class SelectNode extends AbstractQueryNode {

	/**
	 * Filed name for select in query
	 */
	protected List<String> fields;

	/**
	 * Default constructor. Init fields by empty array list.
	 */
	public SelectNode()
	{
		this.fields = new ArrayList<>();
	}

	/**
	 *
	 * @param fields array of filed name
	 */
	public SelectNode(List<String> fields)
	{
		this.fields = fields;
	}

	/**
	 * Default fields array list getter.
	 * @return fields
	 */
	public List<String> getFields() {
		return fields;
	}

	/**
	 * Add filed name to selected array list.
	 * @param filed - selected filed name
	 * @return this
	 */
	public SelectNode addFiled(String filed)
	{
		this.fields.add(filed);
		return this;
	}

	@Override
	public String getNodeName() {
		return "select";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		SelectNode that = (SelectNode) o;
		return Objects.equals(fields, that.fields);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), fields);
	}
}
