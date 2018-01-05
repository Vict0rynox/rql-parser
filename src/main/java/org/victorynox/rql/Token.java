package org.victorynox.rql;

import java.util.Arrays;
import java.util.Objects;

/**
 * Tokenize construction in string
 * @author victorynox
 * @version 0.1
 */
public class Token {

	/**
	 * Token value
	 */
	protected String value;

	/**
	 * Token type
	 */
	protected TokenType type;

	/**
	 * Start position in string
	 */
	protected int start;

	/**
	 * End position in string
	 */
	protected int end;

	/**
	 * Initialize token
	 * @param type token type
	 * @param value token value
	 * @param start position in string
	 * @param end position in string
	 */
	public Token(TokenType type, String value, int start, int end)
	{
		this.type = type;
		this.value = value;
		this.start = start;
		this.end = end;
	}

	/**
	 * Initialize token
	 * @param type token type
	 * @param value token value
	 * @param start position in string
	 */
	public Token(TokenType type, String value, int start)
	{
		this.type = type;
		this.value = value;
		this.start = start;
		this.end = start + value.length();
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", this.type.name(), this.value);
	}

	/**
	 *
	 * @return token value
	 */
	public String getValue() {
		return value;
	}

	/**
	 *
	 * @return token type
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 *
	 * @return start position in string
	 */
	public int getStart() {
		return start;
	}

	/**
	 *
	 * @return end position in string
	 */
	public int getEnd() {
		return end;
	}

	/**
	 *
	 * @return true if type has been in tokenTypes array
	 */
	public boolean test(TokenType[] tokenTypes)
	{
		Arrays.sort(tokenTypes);
		int index = Arrays.binarySearch(tokenTypes, type);
		return index >= 0 && index < tokenTypes.length;
	}

	/**
	 *
	 * @return true if type has been in tokenTypes array and value has been in values array.
	 */
	public boolean test(TokenType[] tokenTypes, String[] values)
	{
		Arrays.sort(tokenTypes);
		int index = Arrays.binarySearch(tokenTypes, type);
		if(index < 0 || index > tokenTypes.length) {
			return false;
		}
		Arrays.sort(values);
		index = Arrays.binarySearch(values, value);
		return index >= 0 && index < values.length;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Token token = (Token) o;
		return start == token.start &&
				end == token.end &&
				Objects.equals(value, token.value) &&
				type == token.type;
	}

	@Override
	public int hashCode() {

		return Objects.hash(value, type, start, end);
	}
}
