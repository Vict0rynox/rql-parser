package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GlobLexerTest extends AbstractLexerTest {

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("str?ng", 0, "str?ng"),
				Arguments.of("s*g", 0, "s*g"),
				Arguments.of("and(eq(name,v*lue),ne(age,string:value2))&sort(-name)", 12, "v*lue"),
				Arguments.of("and(eq(name,value),ne(age,string:?alu?))&sort(-name)", 33, "?alu?"),
				Arguments.of("and(eq(name,value),gt(age,*2))&sort(-name)", 26, "*2"),
				Arguments.of("and(eq(name,value),ne(age,0?0*))&sort(-name)", 26, "0?0*")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("(", 0),
				Arguments.of(",", 0),
				Arguments.of("123", 0),
				Arguments.of("123.34", 0),
				Arguments.of("and(eq(name,va_lue),ne(age,string:value2))&sort(-name)", 12),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 33)
		);
	}

	@BeforeEach
	void startUp() {
		lexer = new GlobLexer();
	}
}