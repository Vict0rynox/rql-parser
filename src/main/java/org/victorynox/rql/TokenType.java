package org.victorynox.rql;

import org.victorynox.rql.exception.UnknownTokenTypeException;

/**
 * @author victorynox
 * @version 0.1
 */
public enum TokenType {
	T_END(-1),

	T_INTEGER(1),
	T_FLOAT(2), //TODO: maybe need renamed to T_DOUBLE
	T_STRING(3),
	T_DATE(4),
	T_GLOB(5),

	T_CLOSE_PARENTHESIS(11),
	T_OPEN_PARENTHESIS(12),
	T_COMMA(13),
	T_AMPERSAND(14),
	T_VERTICAL_BAR(15),
	T_PLUS(16),
	T_MINUS(17),
	T_COLON(19),

	T_TYPE(31),

	T_OPERATOR(41),

	T_NULL(51),
	T_EMPTY(52),
	T_TRUE(53),
	T_FALSE(54),;

	/**
	 * Token type number
	 */
	private int type;

	TokenType(int type) {
		this.type = type;
	}

	/**
	 * @return token type
	 */
	public int getType() {
		return type;
	}
}
