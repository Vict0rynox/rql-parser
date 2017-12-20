package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SubLexer chain with lexer list
 * @author victorynox
 * @version 0.1
 */
public class LexerChain implements Lexer {

	/**
	 * subLexerList
	 */
	protected List<Lexer> subLexerList;

	/**
	 * Init chain with empty list
	 */
	public LexerChain()
	{
		subLexerList = new ArrayList<>();
	}

	/**
	 * Add SubLexer to chan
	 * @param subLexer - additional sub lexer 
	 */
	public LexerChain addSubLexer(Lexer subLexer)
	{
		subLexerList.add(subLexer);
		return this;
	}

	@Override
	public Optional<Token> getTokenAt(String code, int cursor) throws SyntaxErrorException {
		for (Lexer subLexer : subLexerList) {
			Optional<Token> token =  subLexer.getTokenAt(code, cursor);
			if(token.isPresent()) {
				return token;
			}
		}
		return Optional.empty();
	}
}
