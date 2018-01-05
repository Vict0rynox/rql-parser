package org.victorynox.rql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TokenizerTest {

	private Tokenizer tokenizer;

	/**
	 * Return valid data for Success tokenize test
	 *
	 * @return Stream
	 */
	private static Stream<Arguments> getTokenizeSuccess() {
		Stream.Builder<Arguments> argumentsBuilder = Stream.builder();
		List<Token> tokenList;

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"a",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_STRING,"b",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"lt",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"c",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_STRING,"d",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"ne",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"e",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_STRING,"f",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_PIPE,"|",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"gt",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"g",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_STRING,"h",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		argumentsBuilder.add(Arguments.of(
				"(eq(a,b)&lt(c,d))&(ne(e,f)|gt(g,h))",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "eq", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND, "&", 0));
		tokenList.add(new Token(TokenType.T_STRING, "eq", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND, "&", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR, "limit", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0));
		tokenList.add(new Token(TokenType.T_STRING, "limit", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 0));
		tokenList.add(new Token(TokenType.T_TYPE, "date", 0));
		tokenList.add(new Token(TokenType.T_COLON, ":", 0));
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_NULL, "null()", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_INTEGER, "1", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_INTEGER, "+1", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_INTEGER, "-1", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_INTEGER, "0", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_FLOAT, "1.5", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_FLOAT, "-.4e12", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_DATE, "2015-04-16T17:40:32Z", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_GLOB, "*abc?", 0));
		argumentsBuilder.add(Arguments.of(
				"eq(&eq&limit(limit,)date:empty(),null(),1,+1,-1,0,1.5,-.4e12,2015-04-16T17:40:32Z,*abc?",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "in",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(",0));
		tokenList.add(new Token(TokenType.T_STRING, "a",0));
		tokenList.add(new Token(TokenType.T_COMMA, ",",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(",0));
		tokenList.add(new Token(TokenType.T_DATE, "2015-04-16T17:40:32Z",0));
		tokenList.add(new Token(TokenType.T_COMMA, ",",0));
		tokenList.add(new Token(TokenType.T_DATE, "2012-02-29T17:40:32Z",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")",0));
		argumentsBuilder.add(Arguments.of(
				"in(a,(2015-04-16T17:40:32Z,2012-02-29T17:40:32Z))",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"name", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"value", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"eq(name,value)",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"a",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"1",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"ne",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"b",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"2",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"lt",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"c",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"3",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"gt",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"d",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"4",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"le",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"e",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"5",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"ge",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"f",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"6",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"like",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"g",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_GLOB,"*abc?",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		argumentsBuilder.add(Arguments.of(
				"eq(a,1)&ne(b,2)&lt(c,3)&gt(d,4)&le(e,5)&ge(f,6)&like(g,*abc?)",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"in", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"1", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"b", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"out", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"c", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"2", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"d", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"in(a,(1,b))&out(c,(2,d))",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"b", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"lt", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"c", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"d", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"eq(a,b)&lt(c,d)",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"and", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"b", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"lt", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"c", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"d", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"or", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"in", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"1", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"f", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"gt", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"g", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"2", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"not", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"ne", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"h", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"3", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"and(eq(a,b),lt(c,d),or(in(a,(1,f)),gt(g,2)))&not(ne(h,3))",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"select",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_STRING,"a",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_STRING,"b",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_STRING,"c",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"sort",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_PLUS,"+",0));
		tokenList.add(new Token(TokenType.T_STRING,"a",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_MINUS,"-",0));
		tokenList.add(new Token(TokenType.T_STRING,"b",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"limit",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"1",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&",0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"limit",0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"1",0));
		tokenList.add(new Token(TokenType.T_COMMA,",",0));
		tokenList.add(new Token(TokenType.T_INTEGER,"2",0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")",0));
		argumentsBuilder.add(Arguments.of(
				"select(a,b,c)&sort(+a,-b)&limit(1)&limit(1,2)",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"3", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"in", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"b", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_TRUE,"true()", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_FALSE,"false()", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_NULL,"null()", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_EMPTY,"empty()", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_AMPERSAND,"&", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"out", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"c", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_INTEGER,"-1", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_TYPE,"string", 0));
		tokenList.add(new Token(TokenType.T_COLON,":", 0));
		tokenList.add(new Token(TokenType.T_FLOAT,"+.5e10", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"eq(a,string:3)&" +
						"in(b,(string:true(),string:false(),string:null(),string:empty()))&" +
						"out(c,(string:-1,string:+.5e10))",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "like", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 0));
		tokenList.add(new Token(TokenType.T_STRING, "a", 0));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 0));
		tokenList.add(new Token(TokenType.T_TYPE, "glob", 0));
		tokenList.add(new Token(TokenType.T_COLON, ":", 0));
		tokenList.add(new Token(TokenType.T_INTEGER, "3", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 0));
		argumentsBuilder.add(Arguments.of(
				"like(a,glob:3)",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR,"in", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_NULL,"null()", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_TRUE,"true()", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_FALSE,"false()", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_EMPTY,"empty()", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"in(a,(null(),true(),false(),empty()))",
				prepareTokenStream(tokenList)
		));

		tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"eq", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"a", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"b", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_PIPE,"|", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"lt", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"c", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"d", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_PIPE,"|", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"and", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"gt", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"e", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"f", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"ne", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"g", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"h", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_PIPE,"|", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"gt", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"i", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"j", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_PIPE,"|", 0));
		tokenList.add(new Token(TokenType.T_OPERATOR,"in", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"k", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS,"(", 0));
		tokenList.add(new Token(TokenType.T_STRING,"l", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"m", 0));
		tokenList.add(new Token(TokenType.T_COMMA,",", 0));
		tokenList.add(new Token(TokenType.T_STRING,"n", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS,")", 0));
		argumentsBuilder.add(Arguments.of(
				"(eq(a,b)|lt(c,d)|and(gt(e,f),(ne(g,h)|gt(i,j)|in(k,(l,m,n)))))",
				prepareTokenStream(tokenList)
		));

		return argumentsBuilder.build();
	}

	private static TokenStream prepareTokenStream(List<Token> tokenList) {
		int current = 0;
		List<Token> repackTokenList = new ArrayList<>();
		for (Token token : tokenList) {
			token = new Token(token.getType(), token.getValue(), current);
			current += token.getValue().length();
			repackTokenList.add(token);
		}
		repackTokenList.add(new Token(TokenType.T_END, "", current));
		return new TokenStream(repackTokenList);
	}

	/**
	 * Encode string.
	 * Need for optimize test.
	 *
	 * @param string non encoding string
	 * @return EncodedString
	 */
	private static String encodedString(String string) {
		//noinspection Duplicates
		try {
			string = URLEncoder.encode(string, "UTF-8");
			string = string.replace("+", "%20");
			string = string.replace("*", "%2A");
			string = string.replace("-", "%2D");
			string = string.replace("_", "%5F");
			string = string.replace(".", "%2E");
			string = string.replace("~", "%7E");
		} catch (UnsupportedEncodingException ignored) {
			//TODO: is bad code style.
		}
		return string;
	}

	@BeforeEach
	private void startUp() {
		tokenizer = new Tokenizer();
	}

	@ParameterizedTest
	@MethodSource("getTokenizeSuccess")
	void tokenize_Success(String code, TokenStream expectedTokenStream) {
		try {
			TokenStream tokens = tokenizer.tokenize(code);
			assertEquals(expectedTokenStream, tokens);
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}
}