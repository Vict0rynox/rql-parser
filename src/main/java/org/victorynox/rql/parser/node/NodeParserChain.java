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
public class NodeParserChain implements NodeParser<AbstractNode> {

	/**
	 * Node parsers list
	 */
	protected List<NodeParser<AbstractNode>> nodeParsers;

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
	public NodeParserChain(List<NodeParser<AbstractNode>> nodeParsers)
	{
		this.nodeParsers = nodeParsers;
	}

	/**
	 * add new nodeParser to chain
	 * @param nodeParser additional node parser
	 */
	public void addNodeParser(NodeParser<AbstractNode> nodeParser) {
		nodeParsers.add(nodeParser);
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
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		for (NodeParser<? extends AbstractNode> nodeParser: nodeParsers) {
			if(nodeParser.supports(tokenStream)) {
				return nodeParser.parse(tokenStream);
			}
		}
		//TODO: add message
		throw new SyntaxErrorException();
	}

}
