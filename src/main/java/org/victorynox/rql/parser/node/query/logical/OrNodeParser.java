package org.victorynox.rql.parser.node.query.logical;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.logical.OrNode;
import org.victorynox.rql.parser.node.query.AbstractLogicalNodeParser;

import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class OrNodeParser extends AbstractLogicalNodeParser<OrNode> {

	/**
	 * Default config
	 *
	 * @param conditionParser condition parser
	 */
	public OrNodeParser(TokenStreamParser<OrNode> conditionParser) {
		super(conditionParser);
	}

	@Override
	protected OrNode createNode(List<? extends AbstractQueryNode> queryList) throws SyntaxErrorException {
		if(queryList.size() < 2) {
			throw new SyntaxErrorException();
		}
		return new OrNode(queryList);
	}

	@Override
	protected String getOperatorName() {
		return "or";
	}
}
