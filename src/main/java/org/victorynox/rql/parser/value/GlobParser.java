package org.victorynox.rql.parser.value;

import org.victorynox.rql.Glob;
import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

/**
 * Parse token value to Glob
 * @author victorynox
 * @version 0.1
 */
public class GlobParser implements TokenStreamParser<Glob> {

	protected static TokenType[] allowedType = new TokenType[]{
			TokenType.T_INTEGER,
			TokenType.T_FLOAT,
			TokenType.T_STRING,
			TokenType.T_DATE,
	};

	@Override
	public Glob parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		if (tokenStream.getCurrent().test(new TokenType[]{TokenType.T_GLOB})) {
			Glob glob = new Glob(tokenStream.getCurrent().getValue());
			tokenStream.next();
			return glob;
		}
		if (tokenStream.getCurrent().test(new TokenType[]{TokenType.T_TYPE}, new String[]{"glob"})) {
			tokenStream.next();
			tokenStream.expect(new TokenType[]{TokenType.T_COLON});
		}
		return new Glob(Glob.encoded(tokenStream.expect(GlobParser.allowedType).getValue()));
	}
}
