package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class ConstantLexerTest extends AbstractLexerTest {


	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("false()", 0, "false()"),
				Arguments.of("empty()", 0, "empty()"),
				Arguments.of("true()", 0, "true()"),
				Arguments.of("null(),", 0, "null()"),
				Arguments.of("and(eq(name,false()),ne(age,string:value2))&sort(-name)", 12, "false()"),
				Arguments.of("and(eq(name,true()),ne(age,string:value2))&sort(-name)", 12, "true()"),
				Arguments.of("and(eq(name,value),ne(age,string:empty()))&sort(-name)", 33, "empty()"),
				Arguments.of("and(eq(name,false()),ne(age,string:null())&sort(-name)", 35, "null()"),
				Arguments.of("and(eq(name,false()),ne(age,string:null())&sort(-name)", 12, "false()")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("(", 0),
				Arguments.of(",", 0),
				Arguments.of("123", 0),
				Arguments.of("123", 0),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 3)

		);
	}

	@BeforeEach
	void startUp() {
		lexer = new ConstantLexer();
	}
}