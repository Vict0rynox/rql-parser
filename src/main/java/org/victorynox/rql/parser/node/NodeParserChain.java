package org.victorynox.rql.parser.node;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.parser.node.NodeParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class NodeParserChain<T extends AbstractNode> implements NodeParser<T> {

	/**
	 * Node parsers list
	 */
	protected List<NodeParser<? extends T>> nodeParsers;

	/**
	 * init nodeParser by empty <code>ArrayList </code>
	 */
	public NodeParserChain()
	{
		nodeParsers = new ArrayList<>();
	}

	/**
	 * Init nodeParser chane by list;
	 * @param nodeParsers list of nodeParser
	 */
	public NodeParserChain(List<NodeParser<? extends T>> nodeParsers)
	{
		this.nodeParsers = nodeParsers;
	}

	/**
	 * add new nodeParser to chain
	 * @param nodeParser additional node parser
	 * @return this
	 */
	public <V extends NodeParser<? extends T>> NodeParserChain addNodeParser(V nodeParser) {
		nodeParsers.add(nodeParser);
		return this;
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		for (NodeParser nodeParser: nodeParsers) {
			if(nodeParser.supports(tokenStream)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		for (NodeParser<? extends T> nodeParser: nodeParsers) {
			if(nodeParser.supports(tokenStream)) {
				return nodeParser.parse(tokenStream);
			}
		}
		throw new SyntaxErrorException("Can't parse this tokenStream");
	}

}
