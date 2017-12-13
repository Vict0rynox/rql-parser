package org.victorynox.rql.parser.node;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.LimitNode;

import static org.victorynox.rql.TokenType.*;

/**
 * Parser Limit node
 *
 * @author victorynox
 * @version 0.1
 */
public class LimitNodeParser implements NodeParserInterface {

	/**
	 * int ValueParser
	 */
	protected SubParserInterface<Integer> valueParser;

	/**
	 * Init with ValueParser
	 *
	 * @param valueParser parser limit node value
	 */
	public LimitNodeParser(SubParserInterface<Integer> valueParser) {
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
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		int limit = 0;
		int offset = 0;
		tokenStream.expect(new TokenType[]{T_OPERATOR}, new String[]{"limit"});
		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS});

		limit = valueParser.parse(tokenStream);
		if(tokenStream.getCurrent().test(new TokenType[]{T_COMMA})) {
			offset = valueParser.parse(tokenStream);
		}

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS});

		return new LimitNode(limit, offset);
	}
}
