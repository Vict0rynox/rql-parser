package org.victorynox.rql.parser.value;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * Get int value from token.
 * @author victorynox
 * @version 0.1
 */
public class IntegerParser implements TokenStreamParser<Integer> {
	@Override
	public Integer parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		return Integer.valueOf(tokenStream.expect(new TokenType[]{TokenType.T_INTEGER}).getValue());
	}
}
