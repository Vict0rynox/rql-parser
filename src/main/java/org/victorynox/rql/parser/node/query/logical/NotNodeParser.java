package org.victorynox.rql.parser.node.query.logical;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.logical.NotNode;
import org.victorynox.rql.parser.node.query.AbstractLogicalNodeParser;

import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class NotNodeParser<V extends AbstractQueryNode> extends AbstractLogicalNodeParser<NotNode, V> {

	/**
	 * Default config
	 *
	 * @param conditionParser condition parser
	 */
	public NotNodeParser(TokenStreamParser<V> conditionParser) {
		super(conditionParser);
	}

	@Override
	protected NotNode createNode(List<? extends AbstractQueryNode> queryList) throws SyntaxErrorException {
		if(queryList.size() < 2) {
			throw new SyntaxErrorException("NotNode can't has list with size les than tow");
		}
		return new NotNode(queryList);
	}

	@Override
	protected String getOperatorName() {
		return "not";
	}
}
