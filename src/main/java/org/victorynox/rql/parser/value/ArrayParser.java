package org.victorynox.rql.parser.value;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse array values.
 *
 * @author victorynox
 * @version 0.1
 */
public class ArrayParser<T> implements TokenStreamParser<List<T>> {

	/**
	 * SubParser
	 */
	protected TokenStreamParser<T> itemParser;

	/**
	 * Init Array parser with itemParser
	 *
	 * @param itemParser parser for parse array item value.
	 */
	public ArrayParser(TokenStreamParser<T> itemParser) {
		this.itemParser = itemParser;
	}


	@Override
	public List<T> parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		tokenStream.expect(new TokenType[]{TokenType.T_OPEN_PARENTHESIS});
		ArrayList<T> values = new ArrayList<>();

		do {
			values.add(itemParser.parse(tokenStream));
			if (!tokenStream.getCurrent().test(new TokenType[]{TokenType.T_COMMA})) {
				break;
			}
			tokenStream.next();
		} while (true);
		tokenStream.expect(new TokenType[]{TokenType.T_CLOSE_PARENTHESIS});
		return values;
	}
}
