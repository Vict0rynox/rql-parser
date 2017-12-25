package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class RqlOperatorLexerTest extends AbstractLexerTest {
	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("and(", 0, "and"),
				Arguments.of("eq(", 0, "eq"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 0, "and"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 4, "eq"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 19, "ne"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 42, "sort")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("(", 0),
				Arguments.of(",", 0),
				Arguments.of("123", 0),
				Arguments.of("123.34", 0),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 7),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 12),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 22),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 33),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 48)

		);
	}

	@BeforeEach
	void startUp() {
		lexer = new RqlOperatorLexer();
	}
}