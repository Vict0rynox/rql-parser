package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class NumberLexerTest extends AbstractLexerTest {

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("1", 0, "1"),
				Arguments.of("+1", 0, "+1"),
				Arguments.of("-1", 0, "-1"),
				Arguments.of("-1.5", 0, "-1.5"),
				Arguments.of("+1.5", 0, "+1.5"),
				Arguments.of("-1e10", 0, "-1e10"),
				Arguments.of("-1e-10", 0, "-1e-10"),
				Arguments.of("+3e-9", 0, "+3e-9"),
				Arguments.of("+2e+9", 0, "+2e+9"),
				Arguments.of("0.3", 0, "0.3"),
				Arguments.of("+0.3", 0, "+0.3"),
				Arguments.of("-0.3", 0, "-0.3"),


				Arguments.of("and(eq(name,value),ne(age,10))&sort(-name)", 26, "10"),
				Arguments.of("and(eq(name,value),ne(age,15))&sort(-name)", 26, "15"),
				Arguments.of("and(eq(name,v1),ne(sum,-45))&sort(-name)", 23, "-45"),
				Arguments.of("and(eq(name,value),ne(age,+1.5))&sort(-name)", 26, "+1.5"),
				Arguments.of("and(eq(name,value),ne(age,-1.4))&sort(-name)", 26, "-1.4"),
				Arguments.of("and(eq(name,-110),ne(age,-1e10))&sort(-name)", 25, "-1e10"),
				Arguments.of("and(eq(name,23),ne(age,-1e10),gt(age,215))&sort(-name)", 23, "-1e10"),
				Arguments.of("and(eq(name,23),ne(age,-1e10),gt(age,215))&sort(-name)", 12, "23"),
				Arguments.of("and(eq(name,23),ne(age,-1e10),gt(age,215))&sort(-name)", 37, "215"),
				Arguments.of("and(eq(name,value),ne(age,10e1))&sort(-name)", 26, "10e1"),
				Arguments.of("and(eq(name,23value),ne(age,0.004))&sort(-name)", 28, "0.004"),
				Arguments.of("and(eq(name,value),ne(age,0))&sort(-name)", 26, "0"),
				Arguments.of("and(eq(name,25a5lue),ne(age,0.3))&sort(-name)", 28, "0.3")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("(", 0),
				Arguments.of(",", 0),
				//TODO: check this case
				//Arguments.of("12_3", 0),
				//Arguments.of("1iu23", 0),
				//Arguments.of("and(eq(name,25a5lue),ne(age,0.3))&sort(-name)", 12),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 3)

		);
	}

	@BeforeEach
	void startUp() {
		lexer = new NumberLexer();
	}

}