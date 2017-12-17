package org.victorynox.rql.parser.node;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.node.SelectNode;
import org.victorynox.rql.parser.TokenStreamParser;

import java.util.ArrayList;

import static org.victorynox.rql.TokenType.*;

/**
 * @author victorynox
 * @version 0.1
 */
public class SelectNodeParser implements NodeParser {

	/**
	 * field name parser with string type.
	 */
	protected TokenStreamParser<String> fieldNameParser;

	/**
	 * Init with string parser
	 * @param fieldNameParser string parser
	 */
	public SelectNodeParser(TokenStreamParser<String> fieldNameParser)
	{
		this.fieldNameParser = fieldNameParser;
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(
				new TokenType[]{T_OPERATOR},
				new String[]{"select"}
		);
	}

	@Override
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {

		ArrayList<String> filedNameList = new ArrayList<>();

		tokenStream.expect(new TokenType[]{T_OPERATOR}, new String[]{"select"});
		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS});

		do {
			filedNameList.add(fieldNameParser.parse(tokenStream));
			if(!tokenStream.getCurrent().test(new TokenType[]{T_COMMA})) {
				break;
			}
			tokenStream.next();
		} while (true);

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS});

		return new SelectNode(filedNameList);
	}
}
