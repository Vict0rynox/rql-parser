package org.victorynox.rql.parser.node;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractNode;

import java.util.List;

/**
 * Parser query chain.
 * @author victorynox
 * @version 0.1
 */
public class QueryNodeParser<T extends AbstractNode> extends NodeParserChain<T> {

	public QueryNodeParser() {
		super();
	}

	public QueryNodeParser(List<NodeParser> nodeParsers) {
		super(nodeParsers);
	}

	@Override
	public NodeParserChain<T> addNodeParser(NodeParser nodeParser) {
		return super.addNodeParser(nodeParser);
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return super.supports(tokenStream);
	}

	@Override
	public T parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		return super.parse(tokenStream);
	}
}
