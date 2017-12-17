package org.victorynox.rql;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

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
}
