package org.victorynox.rql;

import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.ArrayList;

/**
 * @author victorynox
 * @version 0.1
 */
public class Lexer {

	/**
	 * SubLexer
	 */
	protected SubLexerInterface subLexer;

	/**
	 * Init Lexer with subLexer
	 * @param subLexer injected sub lexer
	 */
	public Lexer(SubLexerInterface subLexer)
	{
		this.subLexer = subLexer;
	}

	/**
	 * Default getter
	 * @return subLexer
	 */
	public SubLexerInterface getSubLexer() {
		return subLexer;
	}

	/**
	 * Default setter
	 * @param subLexer another subLexer
	 */
	public void setSubLexer(SubLexerInterface subLexer) {
		this.subLexer = subLexer;
	}

	/**
	 * Tokenize string
	 * @param code string
	 * @return Tokenize stream
	 */
	public TokenStream tokenize(String code) throws SyntaxErrorException
	{
		int end = code.length();
		int cursor = 0;
		ArrayList<Token> tokens = new ArrayList<>();
		while (cursor < end) {
			try {
				Token token = subLexer.getTokenAt(code, cursor);
				cursor = token.getEnd();
				tokens.add(token);
			} catch (ParserException e) {
				//TODO:add message
				//invalid char `code[cursor]` at position `cursor`
				throw new SyntaxErrorException();
			}
		}
		tokens.add(new Token(TokenType.T_END, "", cursor, cursor));
		return new TokenStream(tokens);
	}
}
