package org.victorynox.rql.parser.node.query;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.logical.AndNode;
import org.victorynox.rql.node.operator.logical.OrNode;
import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.parser.node.NodeParser;

import java.util.ArrayList;
import java.util.List;

import static org.victorynox.rql.TokenType.*;

/**
 * Parser GroupNode
 *
 * @author victorynox
 * @version 0.1
 */
public class GroupNodeParser<T extends AbstractQueryNode> implements NodeParser<T> {

	/**
	 * Parser another <code>AbstractQueryNode</code>
	 */
	protected TokenStreamParser<T> conditionParser;

	/**
	 * @param conditionParser condition query parser
	 */
	public GroupNodeParser(TokenStreamParser<T> conditionParser) {
		this.conditionParser = conditionParser;
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(new TokenType[]{T_OPEN_PARENTHESIS});
	}

	@Override
	public T parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		List<T> queryList = new ArrayList<>();
		TokenType operator = null;


		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS});
		do {
			queryList.add(conditionParser.parse(tokenStream));

			if (tokenStream.getCurrent().test(new TokenType[]{T_AMPERSAND})) {
				tokenStream.next();
				if (operator == null) {
					operator = T_AMPERSAND;
				} else if (operator != T_AMPERSAND) {
					throw new SyntaxErrorException("Cannot mix & and | with in a group");
				}
			} else if (tokenStream.getCurrent().test(new TokenType[]{T_PIPE})) {
				tokenStream.next();
				if (operator == null) {
					operator = T_PIPE;
				} else if (operator != T_PIPE) {
					throw new SyntaxErrorException("Cannot mix & and | with in a group");
				}
			} else {
				break;
			}

		} while (true);

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS});
		assert operator != null;
		switch (operator) {
			case T_PIPE:
				//noinspection unchecked
				return (T) new OrNode(queryList);
			case T_AMPERSAND:
				//noinspection unchecked
				return (T) new AndNode(queryList);
			default:
				return queryList.get(0);
		}
	}
}
