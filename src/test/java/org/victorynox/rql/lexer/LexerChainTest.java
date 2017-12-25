package org.victorynox.rql.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.Mockito;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LexerChainTest{

	private LexerChain lexerChain;

	@BeforeEach
	void startUp() {
		lexerChain = new LexerChain();
	}

	@Test
	void addSubLexer() {
		Lexer lexer = Mockito.mock(Lexer.class);
		try {
			Optional<Token> token = lexerChain.getTokenAt("", 0);
			assertFalse(token.isPresent());
			Mockito.when(lexer.getTokenAt("", 0)).thenReturn(Optional.of(new Token(TokenType.T_EMPTY,"", 0, 0)));
			lexerChain.addSubLexer(lexer);
			token = lexerChain.getTokenAt("", 0);
			assertTrue(token.isPresent());
			assertEquals("", token.get().getValue());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}

	@Test
	void getTokenAt()
	{
		Lexer lexer = Mockito.mock(Lexer.class);
		lexerChain.addSubLexer(lexer);
		try {
			Mockito.when(lexer.getTokenAt("", 0)).thenReturn(Optional.of(new Token(TokenType.T_EMPTY,"", 0, 0)));
			Optional<Token> token = lexerChain.getTokenAt("", 0);
			assertTrue(token.isPresent());
			assertEquals("", token.get().getValue());
		} catch (SyntaxErrorException e) {
			fail(e);
		}
	}
}