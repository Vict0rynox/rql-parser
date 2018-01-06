package org.victorynox.rql.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.exception.UnknownNodeException;
import org.victorynox.rql.node.LimitNode;
import org.victorynox.rql.node.SelectNode;
import org.victorynox.rql.node.SortNode;
import org.victorynox.rql.node.SortType;
import org.victorynox.rql.node.operator.array.InNode;
import org.victorynox.rql.node.operator.array.OutNode;
import org.victorynox.rql.node.operator.logical.AndNode;
import org.victorynox.rql.node.operator.logical.NotNode;
import org.victorynox.rql.node.operator.logical.OrNode;
import org.victorynox.rql.node.operator.scalar.*;
import org.victorynox.rql.parser.value.ScalarValue;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RqlParserTest {
	private RqlParser parser;

	/**
	 * @return
	 */
	private static Stream<Arguments> getParseSuccessData() throws UnknownNodeException, SyntaxErrorException {
		Stream.Builder<Arguments> argumentsBuilder = Stream.builder();
		QueryBuilder queryBuilder;

		queryBuilder = new QueryBuilder();
		argumentsBuilder.add(Arguments.of(
				tokenize("ne(x,string:*)&eq(a,string:3)&in(b,(string:true(),string:false(),string:null(),string:empty()))&out(c,(string:-1,string:+.5e10))"),
				queryBuilder
						.addNode(new NeNode<ScalarValue>("x", new ScalarValue<>("*")))
						.addNode(new EqNode<ScalarValue>("a", new ScalarValue<>("3")))
						.addNode((new InNode<ScalarValue>("b"))
								.addValue(new ScalarValue<>("true"))
								.addValue(new ScalarValue<>("false"))
								.addValue(new ScalarValue<>("null"))
								.addValue(new ScalarValue<>(""))
						)
						.addNode((new OutNode<ScalarValue>("c"))
								.addValue(new ScalarValue<>("-1"))
								.addValue(new ScalarValue<>("+.5e10"))
						)
						.build()
		));

		queryBuilder = new QueryBuilder();
		argumentsBuilder.add(Arguments.of(
				tokenize("eq(a,1)&ne(b,2)&lt(c,3)&gt(d,4)&le(e,5)&ge(f,6)&like(g,*abc?)"),
				queryBuilder
						.addNode(new EqNode<ScalarValue>("a", new ScalarValue<>(1)))
						.addNode(new NeNode<ScalarValue>("b", new ScalarValue<>(2)))
						.addNode(new LtNode<ScalarValue>("c", new ScalarValue<>(3)))
						.addNode(new GtNode<ScalarValue>("d", new ScalarValue<>(4)))
						.addNode(new LeNode<ScalarValue>("e", new ScalarValue<>(5)))
						.addNode(new GeNode<ScalarValue>("f", new ScalarValue<>(6)))
						.addNode(new LikeNode<>("g", new Glob("*abc?")))
						.build()
		));

		queryBuilder = new QueryBuilder();
		argumentsBuilder.add(Arguments.of(
				tokenize("in(a,(1,b))&out(c,(2,d))"),
				queryBuilder
						.addNode((new InNode<>("a", new ArrayList<>()))
								.addValue(new ScalarValue<>(1))
								.addValue(new ScalarValue<>("b"))
						)
						.addNode((new OutNode<>("c", new ArrayList<>()))
								.addValue(new ScalarValue<>(2))
								.addValue(new ScalarValue<>("d"))
						)
						.build()
		));

		queryBuilder = new QueryBuilder();
		argumentsBuilder.add(Arguments.of(
				tokenize("eq(a,b)&lt(c,d)"),
				queryBuilder
						.addNode(new EqNode<ScalarValue>("a", new ScalarValue<>("b")))
						.addNode(new LtNode<ScalarValue>("c", new ScalarValue<>("d")))
						.build()
		));

		queryBuilder = new QueryBuilder();
		argumentsBuilder.add(Arguments.of(
				tokenize("and(eq(a,b),lt(c,d),or(in(a,(1,f)),gt(g,2)))&not(ne(h,3))"),
				queryBuilder
						.addNode((new AndNode())
								.addQueryNode(new EqNode<ScalarValue>("a", new ScalarValue<>("b")))
								.addQueryNode(new LtNode<ScalarValue>("c", new ScalarValue<>("d")))
								.addQueryNode((new OrNode())
										.addQueryNode((new InNode<ScalarValue>("a"))
												.addValue(new ScalarValue<>(1))
												.addValue(new ScalarValue<>("f"))
										)
										.addQueryNode(new GtNode<ScalarValue>("g", new ScalarValue<>(2)))
								)
						)
						.addNode((new NotNode())
								.addQueryNode(new NeNode<ScalarValue>("h", new ScalarValue<>(3)))
						)
						.build()
		));

		queryBuilder = new QueryBuilder();
		argumentsBuilder.add(Arguments.of(
				tokenize("select(a,b,c)&sort(+a,-b)&limit(1,2)"),
				queryBuilder
						.addNode((new SelectNode())
								.addFiled("a")
								.addFiled("b")
								.addFiled("c")
						)
						.addNode((new SortNode())
								.addFiledSort("a", SortType.SORT_ACS)
								.addFiledSort("b", SortType.SORT_DESC)
						)
						.addNode(new LimitNode(1, 2))
						.build()
		));

		return argumentsBuilder.build();
	}

	private static TokenStream tokenize(String code) throws SyntaxErrorException {
		Tokenizer tokenizer = new Tokenizer();
			return tokenizer.tokenize(code);

	}

	@BeforeEach
	void startUp() {
		parser = new RqlParser();
	}

	@ParameterizedTest
	@MethodSource("getParseSuccessData")
	void parse_Success(TokenStream tokenStream, Query eqpectedQuery) throws SyntaxErrorException {
		Query query = parser.parse(tokenStream.iterator());
 		assertEquals(eqpectedQuery, query);
	}

}