package org.victorynox.rql.parser.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.victorynox.rql.*;
import org.victorynox.rql.parser.AbstractParserTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GlobParserTest extends AbstractParserTest<Glob> {

	@Override
	@BeforeEach
	protected void startUp() {
		parser = new GlobParser();
	}

	/**
	 * Return valid tokenStream data for parse
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		Stream.Builder<Arguments> streamBuilder = Stream.builder();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_GLOB, "st*ing", 0, 6));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new Glob("st*ing")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_GLOB, "gl0?", 0, 4));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new Glob("gl0?")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "glob", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_STRING, "g_*tr?ng", 5, 13));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new Glob("g_\\*tr\\?ng")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "glob", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_INTEGER, "1*1", 5, 8));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new Glob("1\\*1")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "glob", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_FLOAT, "10.4?", 5, 10));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new Glob("10.4\\?")));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_TYPE, "glob", 0, 4));
		tokenList.add(new Token(TokenType.T_COLON, ":", 4, 5));
		tokenList.add(new Token(TokenType.T_DATE, "2013-05-20T17:33:2?", 5, 24));
		streamBuilder.add(Arguments.of((new TokenStream(tokenList)).iterator(), new Glob("2013-05-20T17:33:2\\?")));

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
		tokenList.add(new Token(TokenType.T_STRING, "string", 0, 6));
		streamBuilder.add((new TokenStream(tokenList)).iterator());

		return streamBuilder.build();
	}
}