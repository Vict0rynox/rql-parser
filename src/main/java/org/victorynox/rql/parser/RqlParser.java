package org.victorynox.rql.parser;

import org.victorynox.rql.Query;
import org.victorynox.rql.QueryBuilder;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.caster.BooleanTypeCaster;
import org.victorynox.rql.caster.DoubleTypeCaster;
import org.victorynox.rql.caster.IntegerTypeCaster;
import org.victorynox.rql.caster.StringTypeCaster;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.exception.UnknownNodeException;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.parser.node.*;
import org.victorynox.rql.parser.node.query.GroupNodeParser;
import org.victorynox.rql.parser.node.query.comparison.rql.*;
import org.victorynox.rql.parser.node.query.logical.AndNodeParser;
import org.victorynox.rql.parser.node.query.logical.NotNodeParser;
import org.victorynox.rql.parser.node.query.logical.OrNodeParser;
import org.victorynox.rql.parser.value.*;

import java.util.HashMap;

/**
 * @author victorynox
 * @version 0.1
 */
public class RqlParser implements TokenStreamParser<Query>{

	/**
	 * Parser
	 */
	protected NodeParser<AbstractNode> nodeParser;

	/**
	 * Init RqlParser with node parser.
	 */
	public RqlParser() {
		NodeParserChain<AbstractNode> nodeParser = new NodeParserChain<>();

		ScalarParser scalarParser = (new ScalarParser(new HashMap<>()))
				.registerTypeCaster(new BooleanTypeCaster())
				.registerTypeCaster(new DoubleTypeCaster())
				.registerTypeCaster(new IntegerTypeCaster())
				.registerTypeCaster(new StringTypeCaster());
		ArrayParser<ScalarValue> arrayParser = new ArrayParser<>(scalarParser);
		GlobParser globParser = new GlobParser();
		FieldParser fieldParser = new FieldParser();
		IntegerParser integerParser = new IntegerParser();

		QueryNodeParser<AbstractQueryNode> queryNodeParser = new QueryNodeParser<>();

        queryNodeParser
                .addNodeParser(new GroupNodeParser(queryNodeParser))

                .addNodeParser(new AndNodeParser<>(queryNodeParser))
				.addNodeParser(new OrNodeParser<>(queryNodeParser))
				.addNodeParser(new NotNodeParser<>(queryNodeParser))

                .addNodeParser(new InNodeParser<>(fieldParser, arrayParser))
				.addNodeParser(new OutNodeParser<>(fieldParser, arrayParser))
				.addNodeParser(new EqNodeParser<>(fieldParser, scalarParser))
				.addNodeParser(new NeNodeParser<>(fieldParser, scalarParser))
				.addNodeParser(new LtNodeParser<>(fieldParser, scalarParser))
				.addNodeParser(new LeNodeParser<>(fieldParser, scalarParser))
				.addNodeParser(new GtNodeParser<>(fieldParser, scalarParser))
				.addNodeParser(new GeNodeParser<>(fieldParser, scalarParser))
				.addNodeParser(new LikeNodeParser<>(fieldParser, globParser));
		nodeParser
				.addNodeParser(queryNodeParser)
				.addNodeParser(new SelectNodeParser(fieldParser))
				.addNodeParser(new SortNodeParser(fieldParser))
				.addNodeParser(new LimitNodeParser(integerParser));

		this.nodeParser = nodeParser;
	}

	@Override
	public Query parse(TokenStreamIterator tokenStream) throws SyntaxErrorException {
		//refactor with get by dep
		QueryBuilder queryBuilder = new QueryBuilder();
		while (!tokenStream.isEnd()) {
			try {
				queryBuilder.addNode(nodeParser.parse(tokenStream));
				if(tokenStream.getCurrent().test(new TokenType[]{TokenType.T_AMPERSAND})) {
					tokenStream.next();
				}
			} catch (UnknownNodeException e) {
				throw new SyntaxErrorException("Find UnknownNode", e);
			}
		}
		return queryBuilder.build();
	}
}
