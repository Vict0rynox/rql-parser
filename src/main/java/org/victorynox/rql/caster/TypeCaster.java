package org.victorynox.rql.caster;

import org.victorynox.rql.Token;

/**
 * @author victorynox
 * @version 0.1
 */
public interface TypeCaster<T> {

	/**
	 * Casted token value to same type
	 * @param token who has been casted
	 * @return token value with cast
	 */
	T typeCast(Token token);

	/**
	 * Return <code>TypeCaster</code> T type.
	 * @return class type
	 */
	Class getType();
}
