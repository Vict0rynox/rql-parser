package org.victorynox.rql;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobTest {

	private Glob glob;

	/**
	 * @return Stream with data of
	 */
	static private Stream<Arguments> getEncodedData() {
		return Stream.of(
				Arguments.of("\\", "\\\\"),
				Arguments.of("?", "\\?"),
				Arguments.of("*", "\\*"),
				Arguments.of("* ? \\", "\\* \\? \\\\"),
				Arguments.of("__*asd?? \\asd \\asd ", "__\\*asd\\?\\? \\\\asd \\\\asd "),
				Arguments.of("string", "string"),
				Arguments.of("?* *?", "\\?\\* \\*\\?"),
				Arguments.of("?* asd *?", "\\?\\* asd \\*\\?"),
				Arguments.of("+231.3", "+231.3"),
				Arguments.of("2016-06-30T23:33:55Z", "2016-06-30T23:33:55Z"),
				Arguments.of("\\\\ 123 123 ? * _) 1.//,23,\\*1 \\?", "\\\\\\\\ 123 123 \\? \\* _) 1.//,23,\\\\\\*1 \\\\\\?")
		);
	}

	/**
	 * @return Stream with data for glob -> rql
	 */
	static private Stream<Arguments> getGlobToRqlData() {
		return Stream.of(
				Arguments.of("\\*\\? abc \\?\\*", "%2A%3F%20abc%20%3F%2A"),
				Arguments.of("string", "string"),
				Arguments.of("*", "*"),
				Arguments.of("?", "?"),
				Arguments.of("*? abc ?*", "*?%20abc%20?*"),
				Arguments.of("+1,5", "%2B1%2C5"),
				Arguments.of("2016-06-30T23:33:55Z", "2016%2D06%2D30T23%3A33%3A55Z"),
				Arguments.of(
						"* asd \\\\ * ? asd As. .1: 2 123 Q2_ e >\"]{|'wd'ASd '|ASD \' ASd AS_D&!@E !@E DS~ASD ? !@ @* *ASd 0_ Asd>>+ 1# AS--D asd",
						"*%20asd%20%5C%20*%20?%20asd%20As%2E%20%2E1%3A%202%20123%20Q2%5F%20e%20%3E%22%5D%7B%7C%27wd%27ASd%20%27%7CASD%20%27%20ASd%20AS%5FD%26%21%40E%20%21%40E%20DS%7EASD%20?%20%21%40%20%40*%20*ASd%200%5F%20Asd%3E%3E%2B%201%23%20AS%2D%2DD%20asd")
		);
	}

	/**
	 * @return Stream with data for glob -> rql
	 */
	static private Stream<Arguments> getGlobToRegexData() {
		return Stream.of(
				Arguments.of("string", "string"),
				Arguments.of("*", ".*"),
				Arguments.of("?", "."),
				Arguments.of("*? abc ?*", ".*. abc ..*"),
				Arguments.of("+1,5", "\\+1,5"),
				Arguments.of("2016-06-30T23:33:55Z", "2016\\-06\\-30T23\\:33\\:55Z"),
				Arguments.of(
						"* asd \\\\ * ? asd As. .1: 2 123 Q2_ e >\"]{|'wd'ASd '|ASD \' ASd AS_D&!@E !@E DS~ASD ? !@ @* *ASd 0_ Asd>>+ 1# AS--D asd",
						".* asd \\\\ .* . asd As\\. \\.1\\: 2 123 Q2_ e \\>\"\\]\\{\\|'wd'ASd '\\|ASD \' ASd AS_D&\\!@E \\!@E DS~ASD . \\!@ @.* .*ASd 0_ Asd\\>\\>\\+ 1# AS\\-\\-D asd")
		);
	}

	/**
	 * @return Stream with data for glob -> rql
	 */
	static private Stream<Arguments> getGlobToLikeData() {
		return Stream.of(
				Arguments.of(
						"* asd \\\\ * ? asd As. .1: 2 123 Q2_ e >\"]{|'wd'ASd '|ASD \' ASd AS_D&!@E !@E DS~ASD ? !@ @* *ASd 0_ Asd>>+ 1# AS--D asd",
						"% asd \\\\ % _ asd As. .1: 2 123 Q2\\_ e >\"]{|'wd'ASd '|ASD \' ASd AS\\_D&!@E !@E DS~ASD _ !@ @% %ASd 0\\_ Asd>>+ 1# AS--D asd"
						),
				Arguments.of("*", "%"),
				Arguments.of("string", "string"),
				Arguments.of("?", "_"),
				Arguments.of("*? abc ?*", "%_ abc _%"),
				Arguments.of("+1,5", "+1,5"),
						Arguments.of("2016-06-30T23:33:55Z", "2016-06-30T23:33:55Z")
		);
	}

	@ParameterizedTest
	@MethodSource("getEncodedData")
	void encoded(String globString, String expected) {
		String encodedString = Glob.encoded(globString);
		assertEquals(expected, encodedString);
	}

	@ParameterizedTest
	@MethodSource("getGlobToRqlData")
	void toRql(String globString, String expected) {
		glob = new Glob(globString);
		assertEquals(expected, glob.toRql());
	}

	@ParameterizedTest
	@MethodSource("getGlobToRegexData")
	void toRegex(String globString, String expected) {
		glob = new Glob(globString);
		assertEquals(expected, glob.toRegex());
	}

	@ParameterizedTest
	@MethodSource("getGlobToLikeData")
	void toLike(String globString, String expected) {
		glob = new Glob(globString);
		assertEquals(expected, glob.toLike());
	}
}