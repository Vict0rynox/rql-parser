package org.victorynox.rql.parser.node.query.logical;

import org.victorynox.rql.SubParserInterface;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.AbstractComparisonNode;
import org.victorynox.rql.node.operator.AbstractLogicalNode;
import org.victorynox.rql.node.operator.logical.AndNode;
import org.victorynox.rql.parser.node.query.AbstractLogicalOperatorNodeParser;

import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class AndNodeParser extends AbstractLogicalOperatorNodeParser {

	/**
	 * Default config
	 *
	 * @param conditionParser condition parser
	 */
	public AndNodeParser(SubParserInterface<AbstractQueryNode> conditionParser) {
		super(conditionParser);
	}

	@Override
	protected AbstractQueryNode createNode(List<AbstractQueryNode> queryList) throws SyntaxErrorException {
		if(queryList.size() < 2) {
			throw new SyntaxErrorException();
		}
		return new AndNode(queryList);
	}

	@Override
	protected String getOperatorName() {
		return "and";
	}
}
