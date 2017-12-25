package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.Token;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PunctuationLexerTest extends AbstractLexerTest{

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("&", 0, "&"),
				Arguments.of("|", 0, "|"),
				Arguments.of(",", 0, ","),
				Arguments.of("(", 0, "("),
				Arguments.of(")", 0, ")"),
				Arguments.of(":", 0, ":"),
				Arguments.of("eq(name,value)&ne(age,string:value2)", 2, "("),
				Arguments.of("eq(name,value)&ne(age,string:value2)", 7, ","),
				Arguments.of("eq(name,value)&ne(age,string:value2)", 13, ")"),
				Arguments.of("eq(name,value)&ne(age,string:value2)", 14, "&"),
				Arguments.of("eq(name,value)|ne(age,string:value2)", 14, "|"),
				Arguments.of("eq(name,value)&ne(age,string:value2)", 28, ":")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("1&", 0),
				Arguments.of("_", 0),
				Arguments.of("a", 0),
				Arguments.of("eq(name,value)&ne(age,string:value2)", 5)
		);
	}

	@BeforeEach
	void startUp() {
		lexer = new PunctuationLexer();
	}
}