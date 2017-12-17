package org.victorynox.rql.parser.value;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * Get field name
 * @author victorynox
 * @version 0.1
 */
public class FieldParser implements TokenStreamParser<String> {
	@Override
	public String parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		return tokenStream.expect(new TokenType[]{TokenType.T_STRING}).getValue();
	}
}
