package org.victorynox.rql.parser.node.query.comparison;

import org.victorynox.rql.parser.TokenStreamParser;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.operator.AbstractComparisonNode;
import org.victorynox.rql.parser.node.query.AbstractComparisonNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractRqlNodeParser<T extends AbstractComparisonNode, V> extends AbstractComparisonNodeParser<T, V> {

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public AbstractRqlNodeParser(TokenStreamParser<String> filedNameParser, TokenStreamParser<V> valueParser) {
		super(filedNameParser, valueParser);
	}

	@Override
	public boolean supports(TokenStreamIterator tokenStream) {
		return tokenStream.getCurrent().test(
				new TokenType[]{TokenType.T_OPERATOR},
				new String[]{getOperatorName()}
		);
	}

	@Override
	public T parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {

		tokenStream.expect(new TokenType[]{TokenType.T_OPERATOR}, new String[]{getOperatorName()});
		tokenStream.expect(new TokenType[]{TokenType.T_OPEN_PARENTHESIS});

		String filed = filedNameParser.parse(tokenStream);
		tokenStream.expect(new TokenType[]{TokenType.T_COMMA});
		V value = valueParser.parse(tokenStream);

		tokenStream.expect(new TokenType[]{TokenType.T_CLOSE_PARENTHESIS});

		return createNode(filed, value);
	}

}
