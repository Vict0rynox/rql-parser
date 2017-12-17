package org.victorynox.rql.parser.node.query.logical;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.logical.AndNode;
import org.victorynox.rql.parser.node.NodeParser;
import org.victorynox.rql.parser.node.query.AbstractLogicalNodeParser;

import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class AndNodeParser<V extends AbstractQueryNode> extends AbstractLogicalNodeParser<AndNode, V> {

	/**
	 * Default config
	 *
	 * @param conditionParser condition parser
	 */
	public AndNodeParser(TokenStreamParser<V> conditionParser) {
		super(conditionParser);
	}

	@Override
	protected AndNode createNode(List<? extends AbstractQueryNode> queryList) throws SyntaxErrorException {
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
