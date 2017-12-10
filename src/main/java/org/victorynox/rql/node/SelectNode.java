package org.victorynox.rql.node;

import java.util.ArrayList;
import java.util.List;

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
	 */
	public void addFiled(String filed)
	{
		this.fields.add(filed);
	}

	@Override
	public String getNodeName() {
		return "select";
	}
}
