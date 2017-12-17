package org.victorynox.rql;

import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.lexer.Lexer;

import java.util.ArrayList;

/**
 * @author victorynox
 * @version 0.1
 */
public class Tokenizer {

	/**
	 * SubLexer
	 */
	protected Lexer subLexer;

	/**
	 * Init Tokenizer with subLexer
	 * @param subLexer injected sub lexer
	 */
	public Tokenizer(Lexer subLexer)
	{
		this.subLexer = subLexer;
	}

	/**
	 * Default getter
	 * @return subLexer
	 */
	public Lexer getSubLexer() {
		return subLexer;
	}

	/**
	 * Default setter
	 * @param subLexer another subLexer
	 */
	public void setSubLexer(Lexer subLexer) {
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
