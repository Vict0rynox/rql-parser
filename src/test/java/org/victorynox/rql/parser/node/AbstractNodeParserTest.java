package org.victorynox.rql.parser.node;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.TokenStreamIterator;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractNode;
import org.victorynox.rql.parser.AbstractParserTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractNodeParserTest<T extends AbstractNode> extends AbstractParserTest<T> {

	protected NodeParser<T> parser;

	/**
	 * Return valid tokenStream data for parse
	 *
	 * @return stream
	 */
	protected static Stream<TokenStreamIterator> getSupportSuccessData() {
		return Stream.empty();
	}

	/**
	 * Return valid tokenStream data for parse
	 *
	 * @return stream
	 */
	protected static Stream<Arguments> getParseSuccessData() {
		return Stream.empty();
	}

	@ParameterizedTest
	@MethodSource("getSupportSuccessData")
	void supports(TokenStreamIterator tokenStreamIterator) {
		assertTrue(parser.supports(tokenStreamIterator));
	}

	@ParameterizedTest
	@MethodSource("getParseSuccessData")
	void parse_Success(TokenStreamIterator tokenStreamIterator, T expectedResult) {
		try {
			T result = parser.parse(tokenStreamIterator);
			assertEquals(expectedResult.getNodeName(), result.getNodeName());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}
}