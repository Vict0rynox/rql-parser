package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TypeLexerTest extends AbstractLexerTest {

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("string:", 0, "string"),
				Arguments.of("float:", 0, "float"),
				Arguments.of("date:", 0, "date"),
				Arguments.of("int:", 0, "int"),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 26, "string"),
				Arguments.of("and(eq(name,value),ne(age,int:value2))&sort(-name)", 26, "int"),
				Arguments.of("and(eq(name,value),ne(age,date:1998-10-10T02:02:20Z))&sort(-name)", 26, "date"),
				Arguments.of("and(eq(name,value),ne(age,float:19.34))&sort(-name)", 26, "float")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("(", 0),
				Arguments.of(",", 0),
				Arguments.of("123", 0),
				Arguments.of("123.34", 0),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 3),
				Arguments.of("and(eq(name,value),ne(age,value2))&sort(-name)", 26),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 41),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 22)

		);
	}

	@BeforeEach
	void startUp() {
		lexer = new TypeLexer();
	}
}