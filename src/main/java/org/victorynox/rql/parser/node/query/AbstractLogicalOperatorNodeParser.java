package org.victorynox.rql.parser.node.query;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.AbstractComparisonNode;

import java.util.ArrayList;
import java.util.List;

import static org.victorynox.rql.TokenType.*;

/**
 * Abstract logical node parser
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractLogicalOperatorNodeParser implements NodeParserInterface {

	/**
	 * Parser another <code>AbstractQueryNode</code>
	 */
	protected SubParserInterface<AbstractQueryNode> conditionParser;

	/**
	 * Default config
	 * @param conditionParser condition parser
	 */
	public AbstractLogicalOperatorNodeParser(SubParserInterface<AbstractQueryNode> conditionParser)
	{
		this.conditionParser = conditionParser;
	}

	/**
	 * Create node with data
	 * @param queryList list with query
	 * @return Node
	 */
	abstract protected AbstractComparisonNode createNode(List<AbstractQueryNode> queryList);

	/**
	 *
	 * @return operator name
	 */
	abstract protected String getOperatorName();

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(
				new TokenType[]{T_OPERATOR},
				new String[]{getOperatorName()}
		);
	}

	@Override
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		List<AbstractQueryNode> queryList = new ArrayList<>();

		tokenStream.expect(new TokenType[]{T_OPERATOR}, new String[]{getOperatorName()});
		tokenStream.expect(new TokenType[]{T_OPEN_PARENTHESIS});

		do {
			queryList.add(conditionParser.parse(tokenStream));
			if(!tokenStream.getCurrent().test(new TokenType[]{T_COMMA})) {
				break;
			}
		} while (true);

		tokenStream.expect(new TokenType[]{T_CLOSE_PARENTHESIS});

		return createNode(queryList);
	}
}
