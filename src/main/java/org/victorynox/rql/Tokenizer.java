package org.victorynox.rql;

import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.lexer.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author victorynox
 * @version 0.1
 */
public class Tokenizer {

	/**
	 * SubLexer
	 */
	protected Lexer lexer;

	/**
	 * Init Tokenizer with lexer
	 * @param lexer injected sub lexer
	 */
	public Tokenizer(Lexer lexer)
	{
		this.lexer = lexer;
	}

	/**
	 * Init tokenizer with default lexer chain
	 */
	public Tokenizer()
	{
		LexerChain lexerChain = new LexerChain();
		lexerChain
				.addSubLexer(new ConstantLexer())
				.addSubLexer(new PunctuationLexer())
				.addSubLexer(new RqlOperatorLexer())
				.addSubLexer(new TypeLexer())
				.addSubLexer(new GlobLexer())
				.addSubLexer(new StringLexer())
				.addSubLexer(new DateTimeLexer())
				.addSubLexer(new NumberLexer())
				.addSubLexer(new SortLexer());
		lexer = lexerChain;
	}

	/**
	 * Default getter
	 * @return lexer
	 */
	public Lexer getLexer() {
		return lexer;
	}

	/**
	 * Default setter
	 * @param lexer another lexer
	 */
	public void setLexer(Lexer lexer) {
		this.lexer = lexer;
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
				Optional<Token> token = lexer.getTokenAt(code, cursor);
			if(token.isPresent()) {
				cursor = token.get().getEnd();
				tokens.add(token.get());
			} else {
				throw new SyntaxErrorException("Invalid char " + code.substring(cursor) + " at position " + cursor);
			}
		}
		tokens.add(new Token(TokenType.T_END, "", cursor, cursor));
		return new TokenStream(tokens);
	}
}
