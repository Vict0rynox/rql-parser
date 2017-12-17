package org.victorynox.rql.parser.node;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.node.SortNode;
import org.victorynox.rql.node.SortType;
import org.victorynox.rql.parser.TokenStreamParser;

import java.util.HashMap;

import static org.victorynox.rql.TokenType.*;

/**
 * Parser sort node
 * @author victorynox
 * @version 0.1
 */
public class SortNodeParser implements NodeParser {

	/**
	 * fieldNameParser
	 */
	protected TokenStreamParser<String> fieldNameParser;

	/**
	 * Init with string parser
	 * @param fieldNameParser string parser
	 */
	public SortNodeParser(TokenStreamParser<String> fieldNameParser)
	{
		this.fieldNameParser = fieldNameParser;
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(
				new TokenType[]{T_OPERATOR},
				new String[]{"sort"}
		);
	}

	@Override
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {

		HashMap<String, SortType> filedSortMap = new HashMap<>();

		tokenStream.expect(new TokenType[]{T_OPERATOR}, new String[]{"select"});
		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS});

		do {
			Token direction = tokenStream.expect(new TokenType[]{T_PLUS, T_MINUS});
			String filedName = fieldNameParser.parse(tokenStream);
			SortType sortType = direction.test(new TokenType[]{T_PLUS}) ? SortType.SORT_ACS : SortType.SORT_DESC;

			filedSortMap.put(filedName, sortType);

			if(!tokenStream.getCurrent().test(new TokenType[]{T_COMMA})) {
				break;
			}
			tokenStream.next();
		} while (true);

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS});
		return new SortNode(filedSortMap);
	}
}
