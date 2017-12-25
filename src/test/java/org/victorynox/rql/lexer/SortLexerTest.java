package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortLexerTest extends AbstractLexerTest {

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("+", 0, "+"),
				Arguments.of("-", 0, "-"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 47, "-"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(+name)", 47, "+"),
				Arguments.of("sort(+name)&and(eq(name,value),ne(age,string:value2))", 5, "+"),
				Arguments.of("sort(-name)&and(eq(name,value),ne(age,string:value2))", 5, "-")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("1&", 0),
				Arguments.of("=+", 0),
				Arguments.of("a+", 0),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 5)
		);
	}

	@BeforeEach
	void startUp() {
		lexer = new SortLexer();
	}

}