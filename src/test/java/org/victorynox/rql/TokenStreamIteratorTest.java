package org.victorynox.rql;

import org.junit.jupiter.api.Test;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

//TODO: refactor, add dataProvider

class TokenStreamIteratorTest {

	private TokenStreamIterator tokenStreamIterator;

	@Test
	void hasNext_True() {
		//TODO: rewrite with use Mockito (Mock object)
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0, 7));
		tokenList.add(new Token(TokenType.T_END, "", 0, 0));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertTrue(tokenStreamIterator.hasNext());
	}

	@Test
	void hasNext_False() {
		//TODO: rewrite with use Mockito (Mock object)
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(new ArrayList<>()));
		assertFalse(tokenStreamIterator.hasNext());
	}

	@Test
	void next_Success() {
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0, 7));
		tokenList.add(new Token(TokenType.T_END, "", 0, 0));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertEquals(tokenList.get(1), tokenStreamIterator.next());
	}

	@Test
	void next_NoSuchElementException() {
		List<Token> tokenList = new ArrayList<>();
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertThrows(NoSuchElementException.class, () -> tokenStreamIterator.next());
	}


	@Test
	void lookAhead_Success() {
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0, 8));
		tokenList.add(new Token(TokenType.T_STRING, "string", 8, 14));
		tokenList.add(new Token(TokenType.T_FLOAT, "13.4", 14, 18));
		tokenList.add(new Token(TokenType.T_END, "", 18, 18));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		for (int i = 0; i < tokenList.size(); i++) {
			assertEquals(tokenList.get(i), tokenStreamIterator.lookAhead(i));
		}
	}

	@Test
	void lookAhead_NoSuchElementException() {
		List<Token> tokenList = new ArrayList<>();
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertThrows(NoSuchElementException.class, () -> tokenStreamIterator.lookAhead(5));
	}

	@Test
	void expect_TokenType_Success() throws SyntaxErrorException {
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0, 8));
		tokenList.add(new Token(TokenType.T_STRING, "string", 8, 14));
		tokenList.add(new Token(TokenType.T_FLOAT, "13.4", 14, 18));
		tokenList.add(new Token(TokenType.T_END, "", 18, 18));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		for (Token token : tokenList) {
			Token checked = tokenStreamIterator.expect(new TokenType[]{token.getType()});
			assertEquals(token, checked);
		}
	}

	@Test
	void expect_TokenTypeValue_Success() throws SyntaxErrorException {
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0, 8));
		tokenList.add(new Token(TokenType.T_STRING, "string", 8, 14));
		tokenList.add(new Token(TokenType.T_FLOAT, "13.4", 14, 18));
		tokenList.add(new Token(TokenType.T_END, "", 18, 18));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		for (Token token : tokenList) {
			Token checked = tokenStreamIterator.expect(new TokenType[]{token.getType()}, new String[]{token.getValue()});
			assertEquals(token, checked);
		}
	}

	@Test
	void expect_TokenType_SyntaxErrorException() {
		List<Token> tokenList = new ArrayList<>();
		Token token = new Token(TokenType.T_EMPTY, "empty()", 0, 8);
		tokenList.add(token);
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertThrows(SyntaxErrorException.class,
				() -> tokenStreamIterator.expect(new TokenType[]{TokenType.T_TYPE}));
	}

	@Test
	void expect_TokenTypeValue_SyntaxErrorException() {
		List<Token> tokenList = new ArrayList<>();
		Token token = new Token(TokenType.T_STRING, "string", 0, 8);
		tokenList.add(token);
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertThrows(SyntaxErrorException.class, () ->
				tokenStreamIterator.expect(new TokenType[]{TokenType.T_STRING}, new String[]{"not_str"}));
	}

	@Test
	void getCurrent() {
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_EMPTY, "empty()", 0, 8));
		tokenList.add(new Token(TokenType.T_STRING, "string", 8, 14));
		tokenList.add(new Token(TokenType.T_FLOAT, "13.4", 14, 18));
		tokenList.add(new Token(TokenType.T_END, "", 18, 18));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		for (Token token : tokenList) {
			assertEquals(token, tokenStreamIterator.getCurrent());
			if (tokenStreamIterator.hasNext()) {
				tokenStreamIterator.next();
			}
		}
	}

	@Test
	void isEnd() {
		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_END, "", 0, 0));
		tokenStreamIterator = new TokenStreamIterator(new TokenStream(tokenList));
		assertTrue(tokenStreamIterator.isEnd());
	}
}