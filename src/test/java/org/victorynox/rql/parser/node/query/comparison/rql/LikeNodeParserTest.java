package org.victorynox.rql.parser.node.query.comparison.rql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.victorynox.rql.*;
import org.victorynox.rql.node.operator.scalar.LikeNode;
import org.victorynox.rql.parser.node.AbstractNodeParserTest;
import org.victorynox.rql.parser.value.FieldParser;
import org.victorynox.rql.parser.value.GlobParser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LikeNodeParserTest extends AbstractNodeParserTest<LikeNode<Glob>> {

	/**
	 * Return invalid tokenStream data for cache exception test
	 *
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData() {
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "+field1", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_GLOB, "10", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, "(", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_GLOB, "10", 14, 20));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 20, 21));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 21, 22));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_STRING, "10", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 21, 22));

		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}

	/**
	 * Return valid tokenStream data for parse
	 *
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getSupportSuccessData() {
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}

	/**
	 * Return valid tokenStream data for parse
	 *
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		Stream.Builder<Arguments> streamBuilder = Stream.builder();

		List<Token> tokenList;
		LikeNode<Glob> expectedNode;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_GLOB, "str*ing", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new LikeNode<>("field", new Glob("str*ing"));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_GLOB, "*5", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new LikeNode<>("field", new Glob("*5"));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_GLOB, "+30503??-??-??", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new LikeNode<>("field", new Glob("+30503??-??-??"));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_GLOB, "?glob?", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new LikeNode<>("field", new Glob("?glob?"));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field", 7, 13));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 13, 14));
		tokenList.add(new Token(TokenType.T_GLOB, "432.?21", 14, 20));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 20, 21));

		expectedNode = new LikeNode<>("field", new Glob("432.?21"));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		return streamBuilder.build();
	}

	@Override
	protected void parseResultAssert(LikeNode expectedResult, LikeNode result) {
		assertEquals(expectedResult.getFiled(), result.getFiled());
		assertEquals(expectedResult.getValue(), result.getValue());
	}

	@Override
	@BeforeEach
	protected void startUp() {
		parser = new LikeNodeParser<>(new FieldParser(), new GlobParser());
	}
}