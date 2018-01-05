package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeLexerTest extends AbstractLexerTest {

	static protected Stream<Arguments> getTokenAtValidCodeData() {
		return Stream.of(
				Arguments.of("2010-10-10T10:10:12Z", 0, "2010-10-10T10:10:12Z"),
				Arguments.of("and(eq(name,2010-10-10T10:10:12Z),ne(age,string:value2))&sort(-name)", 12, "2010-10-10T10:10:12Z"),
				Arguments.of("and(eq(name,value),ne(age,date:2007-10-10T00:10:12Z))&sort(-name)", 31, "2007-10-10T00:10:12Z"),
				Arguments.of("and(eq(name,2010-10-10T10:10:12Z),ne(age,1910-10-10T10:10:12Z))&sort(-name)", 41, "1910-10-10T10:10:12Z"),
				Arguments.of("and(eq(name,2012-12-12T12:12:12Z),ne(age,string:2011-11-10T11:11:11Z),gt(time,2010-10-10T10:10:10Z))&sort(-name)", 12, "2012-12-12T12:12:12Z"),
				Arguments.of("and(eq(name,2012-12-12T12:12:12Z),ne(age,string:2011-11-10T11:11:11Z),gt(time,2010-10-10T10:10:10Z))&sort(-name)", 48, "2011-11-10T11:11:11Z"),
				Arguments.of("and(eq(name,2012-12-12T12:12:12Z),ne(age,string:2011-11-10T11:11:11Z),gt(time,2010-10-10T10:10:10Z))&sort(-name)", 78, "2010-10-10T10:10:10Z")
		);
	}

	static protected Stream<Arguments> getTokenAtInvalidCodeTokenNotFoundData() {
		return Stream.of(
				Arguments.of("", 0),
				Arguments.of("20-1020-10T10:09:12Z", 0),
				Arguments.of("20-20 10 10:09:12Z", 0),
				Arguments.of("10 10 10 10:09:12Z", 0),
				Arguments.of("10 10 10 10:09:12Z", 0),
				Arguments.of("20-1020-10 _ 10:09:12Z", 0),
				Arguments.of("2010-20-10_10:09:12Z", 0),
				Arguments.of("2010-20-10 10:09:12Z", 0),
				Arguments.of("20-20-10 10:09:12Z", 0),
				Arguments.of("(", 0),
				Arguments.of(",", 0),
				Arguments.of("123", 0),
				Arguments.of("123", 0),
				Arguments.of("and(eq(name,value),ne(age,string:value2))&sort(-name)", 3)

		);
	}

	private static Stream<String> getTokenAtInvalidCodeSyntaxExceptionData() {
		return Stream.of(
				"1000-10-10T10:91:99Z",
				"0000-00-00T00:00:00Z",
				"0000-10-10T10:10:10Z",
				"1000-00-10T10:10:10Z",
				"1000-00-10T10:99:10Z",
				"1000-14-10T10:12:10Z",
				"1000-11-44T10:12:10Z"
		);
	}

	@BeforeEach
	void startUp() {
		lexer = new DateTimeLexer();
	}

	@ParameterizedTest()
	@MethodSource("getTokenAtInvalidCodeSyntaxExceptionData")
	void getTokenAt_DateInvalid_SyntaxException(String code) {
		assertThrows(SyntaxErrorException.class, () -> lexer.getTokenAt(code, 0));
	}
}