package org.victorynox.rql.parser.node.query.comparsion;

import org.victorynox.rql.AbstractNode;
import org.victorynox.rql.SubParserInterface;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.parser.node.query.AbstractComparsionOperatorNodeParser;

/**
 * @author victorynox
 * @version 0.1
 */
public abstract class AbstractRqlNodeParser extends AbstractComparsionOperatorNodeParser {

	/**
	 * Default constructor
	 *
	 * @param filedNameParser - parser for field name
	 * @param valueParser     - parser for value
	 */
	public AbstractRqlNodeParser(SubParserInterface<String> filedNameParser, SubParserInterface valueParser) {
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
	public AbstractNode parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {

		tokenStream.expect(new TokenType[]{TokenType.T_OPERATOR}, new String[]{getOperatorName()});
		tokenStream.expect(new TokenType[]{TokenType.T_OPEN_PARENTHESIS});

		String filed = filedNameParser.parse(tokenStream);
		tokenStream.expect(new TokenType[]{TokenType.T_COMMA});
		Object value = valueParser.parse(tokenStream);

		tokenStream.expect(new TokenType[]{TokenType.T_CLOSE_PARENTHESIS});

		return createNode(filed, value);
	}
}
