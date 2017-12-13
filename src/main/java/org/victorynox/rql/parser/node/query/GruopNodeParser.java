package org.victorynox.rql.parser.node.query;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.logical.AndNode;
import org.victorynox.rql.node.operator.logical.OrNode;

import java.util.ArrayList;
import java.util.List;

import static org.victorynox.rql.TokenType.*;

/**
 * Parser GroupNode
 *
 * @author victorynox
 * @version 0.1
 */
public class GruopNodeParser implements NodeParserInterface {

	/**
	 * Parser another <code>AbstractQueryNode</code>
	 */
	protected SubParserInterface<AbstractQueryNode> conditionParser;

	/**
	 * @param conditionParser condition query parser
	 */
	public GruopNodeParser(SubParserInterface<AbstractQueryNode> conditionParser) {
		this.conditionParser = conditionParser;
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(new TokenType[]{T_OPEN_PARENTHESIS});
	}

	@Override
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		List<AbstractQueryNode> queryList = new ArrayList<>();
		TokenType operator = null;


		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS});
		do {
			queryList.add(conditionParser.parse(tokenStream));

			if (tokenStream.getCurrent().test(new TokenType[]{T_AMPERSAND})) {
				tokenStream.next();
				if(operator == null) {
					operator = T_AMPERSAND;
				} else if(operator != T_AMPERSAND){
					//message cannot mix & and | within a group
					throw new SyntaxErrorException();
				}
			} else if (tokenStream.getCurrent().test(new TokenType[]{T_VERTICAL_BAR})) {
				tokenStream.next();
				if(operator == null) {
					operator = T_VERTICAL_BAR;
				} else if(operator != T_VERTICAL_BAR){
					//message cannot mix & and | within a group
					throw new SyntaxErrorException();
				}
			} else {
				break;
			}

		} while (true);

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS});
		if(operator == T_VERTICAL_BAR) {
			return new OrNode(queryList);
		} else if (operator == T_AMPERSAND) {
			return new AndNode(queryList);
		} else {
			return queryList.get(0);
		}
	}
}
