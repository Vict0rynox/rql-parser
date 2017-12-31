package org.victorynox.rql.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenStream;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.parser.TokenStreamParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractParserTest<T>{

	protected TokenStreamParser<T> parser;

	/**
	 * Init parser
	 */
	@BeforeEach
	abstract protected void startUp();

	/**
	 * Return valid tokenStream data for parse
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		return Stream.empty();
	}

	/**
	 * Return invalid tokenStream data for cache exception test
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getParseSyntaxErrorExceptionData()
	{
		return Stream.empty();
	}

	@ParameterizedTest
	@MethodSource("getParseSuccessData")
	void parse_Success(TokenStreamIterator tokenStreamIterator, T expectedResult) {
		try {
			T result = parser.parse(tokenStreamIterator);
			assertEquals(expectedResult, result);
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}


	@ParameterizedTest
	@MethodSource("getParseSyntaxErrorExceptionData")
	void parse_SyntaxErrorException(TokenStreamIterator tokenStreamIterator)
	{
		assertThrows(SyntaxErrorException.class, () -> parser.parse(tokenStreamIterator));
	}
}