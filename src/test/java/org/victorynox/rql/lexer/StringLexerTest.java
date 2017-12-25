package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringLexerTest extends AbstractLexerTest {

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("string", 0, "string"),
				Arguments.of("dig1234567890", 0, "dig1234567890"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 0, "and"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 4, "eq"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 19, "ne"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 26, "string"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 42, "sort"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 7, "name"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 12, "value"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 22, "age"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 33, "value2"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 48, "name")
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
		lexer = new StringLexer();
	}
}