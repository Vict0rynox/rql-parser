package org.victorynox.rql.parser.value;

/**
 * @author victorynox
 * @version 0.1
 */
public class ScalarValue<T> {

	protected final T value;

	public ScalarValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * Return value type class
	 * @return Value type <codeClass></code>
	 */
	public Class getTypeClass()
	{
		return value.getClass();
	}
}
