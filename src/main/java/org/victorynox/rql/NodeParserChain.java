package org.victorynox.rql;

import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class NodeParserChain implements NodeParserInterface{

	/**
	 * Node parsers list
	 */
	protected List<NodeParserInterface> nodeParsers;

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
	public NodeParserChain(List<NodeParserInterface> nodeParsers)
	{
		this.nodeParsers = nodeParsers;
	}

	/**
	 * add new nodeParser to chain
	 * @param nodeParser additional node parser
	 */
	public void addNodeParser(NodeParserInterface nodeParser) {
		nodeParsers.add(nodeParser);
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		for (NodeParserInterface nodeParser: nodeParsers) {
			if(nodeParser.supports(tokenStream)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		for (NodeParserInterface nodeParser: nodeParsers) {
			if(nodeParser.supports(tokenStream)) {
				return nodeParser.parse(tokenStream);
			}
		}
		//TODO: add message
		throw new SyntaxErrorException();
	}

}
