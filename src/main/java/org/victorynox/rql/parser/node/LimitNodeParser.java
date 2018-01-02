package org.victorynox.rql.parser.node;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.node.LimitNode;
import org.victorynox.rql.parser.TokenStreamParser;

import static org.victorynox.rql.TokenType.*;

/**
 * Parser Limit node
 *
 * @author victorynox
 * @version 0.1
 */
public class LimitNodeParser implements NodeParser<LimitNode> {

	/**
	 * int ValueParser
	 */
	protected TokenStreamParser<Integer> valueParser;

	/**
	 * Init with ValueParser
	 *
	 * @param valueParser parser limit node value
	 */
	public LimitNodeParser(TokenStreamParser<Integer> valueParser) {
		this.valueParser = valueParser;
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(
				new TokenType[]{T_OPERATOR},
				new String[]{"limit"}
		);
	}

	@Override
	public LimitNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		tokenStream.expect(new TokenType[]{T_OPERATOR}, new String[]{"limit"});
		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS}, new String[]{"("});

		int limit = valueParser.parse(tokenStream);
		int offset = 0;
		if(tokenStream.getCurrent().test(new TokenType[]{T_COMMA})) {
			tokenStream.next();
			offset = valueParser.parse(tokenStream);
		}

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS}, new String[]{")"});

		return new LimitNode(limit, offset);
	}
}
