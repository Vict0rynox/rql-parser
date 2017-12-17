package org.victorynox.rql.parser.node.query.comparison.rql;

import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.node.operator.array.InNode;
import org.victorynox.rql.parser.node.query.comparison.AbstractRqlNodeParser;

import java.util.List;

/**
 * Parser for InNode
 * @author victorynox
 * @version 0.1
 */
public class InNodeParser<V> extends AbstractRqlNodeParser<InNode<V>, V>{

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public InNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	protected InNode<V> createNode(String filed, V value) {
		InNode<V> inNode = new InNode<>(filed);
		inNode.addValue(value);
		return inNode;
	}

	protected InNode<V> createNode(String filed, List<V> value) {
		return new InNode<>(filed, value);
	}

	@Override
	protected String getOperatorName() {
		return "in";
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return super.supports(tokenStream);
	}

	@Override
	public InNode<V> parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		return super.parse(tokenStream);
	}
}
