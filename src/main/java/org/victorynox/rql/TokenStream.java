package org.victorynox.rql;

import java.util.List;
import java.util.Objects;

/**
 * @author victorynox
 * @version 0.1
 */
public class TokenStream implements Iterable<Token> {

	/**
	 * Token list
	 */
	protected final List<Token> tokens;

	/**
	 * Init tokens by list
	 * @param tokens list of token
	 */
	public TokenStream(List<Token> tokens)
	{
		this.tokens = tokens;
	}

	@Override
	public TokenStreamIterator iterator() {
		return new TokenStreamIterator(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TokenStream tokens1 = (TokenStream) o;
		if(tokens1.tokens.size() != tokens.size()) return false;
		for (int i = 0; i < tokens.size(); i++) {
			if(!Objects.equals(tokens.get(i), tokens1.tokens.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {

		return Objects.hash(tokens);
	}
}
