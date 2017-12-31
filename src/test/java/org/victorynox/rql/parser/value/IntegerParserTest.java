package org.victorynox.rql.parser.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStream;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.parser.AbstractParserTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerParserTest extends AbstractParserTest<Integer> {

	@Override
	@BeforeEach
	protected void startUp() {
		parser = new IntegerParser();
	}

	/**
	 * Return valid tokenStream data for parse
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		Stream.Builder<Arguments> streamBuilder = Stream.builder();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "10", 0, 2));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), 10));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "0", 0, 1));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), 0));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "-10", 0, 3));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), -10));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_INTEGER, "+10", 0, 3));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), 10));

		return streamBuilder.build();
	}

	/**
	 * Return invalid tokenStream data for cache exception test
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData()
	{
		Stream.Builder<TokenStreamIterator> streamBuilder = Stream.builder();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_FLOAT, "3.14", 0, 4));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_STRING, "A3B14", 0, 5));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}
}