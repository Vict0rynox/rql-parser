package org.victorynox.rql.parser.value;

import java.util.Objects;

/**
 * TODO: refactor this calss and scalar module. ScalarValue must extend <code>Number</code>
 * Value Object - it necessary to store values of unknown value type
 * (Boolean, Integer, String, Double, Float, ...)
 *
 * @author victorynox
 * @version 0.1
 */
public class ScalarValue<T> {

	/**
	 * Non changed value
	 */
	public final T value;

	public ScalarValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (value == null) {
			return "null";
		}
		return value.toString();
	}

	/**
	 * Return value type class
	 *
	 * @return Value type <codeClass></code>
	 */
	public Class getTypeClass() {
		return value.getClass();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null && value == null) return true;
		if (o != null && o.getClass() == getClass()) {
			ScalarValue<?> that = (ScalarValue<?>) o;
			return Objects.equals(value, that.value);
		}
		return value != null && o != null && o.getClass() == getTypeClass() && Objects.equals(value, o);

	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
