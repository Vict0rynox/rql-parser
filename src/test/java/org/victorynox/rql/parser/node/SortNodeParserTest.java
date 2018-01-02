package org.victorynox.rql.parser.node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStream;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.node.SelectNode;
import org.victorynox.rql.node.SortNode;
import org.victorynox.rql.node.SortType;
import org.victorynox.rql.parser.value.FieldParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortNodeParserTest extends AbstractNodeParserTest<SortNode>{

	@Override
	protected void parseResultAssert(SortNode expectedResult, SortNode result) {
		for (Map.Entry<String, SortType> entry :expectedResult.getFieldsSort().entrySet()) {
			assertEquals(entry.getValue(), result.getFieldsSort().get(entry.getKey()));
		}
	}

	@Override
	@BeforeEach
	protected void startUp() {
		parser = new SortNodeParser(new FieldParser());
	}

	/**
	 * Return invalid tokenStream data for cache exception test
	 *
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData() {
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "select", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 7, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, ")", 6, 7));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 8, 14));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 14, 15));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 8, 14));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, "(", 14, 15));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 8, 14));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 14, 15));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 15, 16));
		tokenList.add(new Token(TokenType.T_STRING, "field2", 16, 22));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 22, 23));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 23, 24));
		tokenList.add(new Token(TokenType.T_STRING, "field3", 24, 30));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 30, 31));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 31, 32));

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
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "field", 8, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
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
		SortNode expectedNode;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "field", 8, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));

		expectedNode = new SortNode();
		expectedNode.addFiledSort("field", SortType.SORT_ACS);

		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "sort", 0, 6));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 6, 7));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "field1", 8, 14));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 14, 15));
		tokenList.add(new Token(TokenType.T_MINUS, "-", 15, 16));
		tokenList.add(new Token(TokenType.T_STRING, "field2", 16, 22));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 22, 23));
		tokenList.add(new Token(TokenType.T_PLUS, "+", 23, 24));
		tokenList.add(new Token(TokenType.T_STRING, "field3", 24, 30));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 30, 31));

		expectedNode = new SortNode();
		expectedNode.addFiledSort("field1", SortType.SORT_ACS);
		expectedNode.addFiledSort("field2",SortType.SORT_DESC);
		expectedNode.addFiledSort("field3",SortType.SORT_ACS);

		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), expectedNode));

		return streamBuilder.build();
	}
}