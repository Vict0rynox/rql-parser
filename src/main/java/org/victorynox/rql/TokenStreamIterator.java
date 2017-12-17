package org.victorynox.rql;

import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for token stream
 * @author victorynox
 * @version 0.1
 */
public class TokenStreamIterator implements Iterator<Token>{

	/**
	 * token stream
	 */
	protected TokenStream tokenStream;

	/**
	 * Current position in string
	 */
	protected int current;

	TokenStreamIterator(TokenStream tokenStream)
	{
		this.tokenStream = tokenStream;
		current = 0;
	}

	@Override
	public boolean hasNext() {
		return current > tokenStream.tokens.size();
	}

	@Override
	public Token next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		current++;
		return tokenStream.tokens.get(current);
	}

	/**
	 * Get <code>Token</code> by skip number of token is stream.
	 * @param number number to skipped
	 * @return Token
	 * @throws NoSuchElementException if element not found
	 */
	public Token lookAheard(int number) throws NoSuchElementException
	{
		if(this.tokenStream.tokens.size() < current + number) {
			throw new NoSuchElementException();
		}
		return this.tokenStream.tokens.get(current + number);
	}

	/**
	 *
	 * @param tokenTypes array wiht expected tokenTypes
	 * @return Token if hee been expected
	 * @throws SyntaxErrorException if token not expected
	 */
	public Token expect(TokenType[] tokenTypes) throws SyntaxErrorException
	{
		Token token = getCurrent();
		if(!token.test(tokenTypes)) {
			throw new SyntaxErrorException();
		}
		next();
		return token;
	}

	/**
	 * @param values array with expected values
	 * @param tokenTypes array with expected tokenTypes
	 * @return Token if hee been expected
	 * @throws SyntaxErrorException if token not expected
	 */
	public Token expect(TokenType[] tokenTypes, String[] values) throws SyntaxErrorException
	{
		Token token = getCurrent();
		if(!token.test(tokenTypes, values)) {
			throw new SyntaxErrorException();
		}
		next();
		return token;
	}

	/**
	 *
	 * @return current token
	 */
	public Token getCurrent()
	{
		return tokenStream.tokens.get(current);
	}

	/**
	 * Check if current token is end
	 * @return true if current token is end.
	 */
	public boolean isEnd()
	{
		return tokenStream.tokens.get(current).getType() == TokenType.T_END;
	}

	@Override
	public TokenStreamIterator clone() throws CloneNotSupportedException {
		return (TokenStreamIterator)super.clone();
	}
}
