package org.victorynox.rql;

/**
 * @author victorynox
 * @version 0.1
 */
public interface TypeCasterInsterface<T> {

	/**
	 * Casted token value to same type
	 * @param token who has been casted
	 * @return token value with cast
	 */
	T typeCast(Token token);
}
