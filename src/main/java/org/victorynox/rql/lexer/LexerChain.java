package org.victorynox.rql.lexer;

import org.victorynox.rql.ParserException;
import org.victorynox.rql.Token;
import org.victorynox.rql.lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

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
	public void addSubLexer(Lexer subLexer)
	{
		subLexerList.add(subLexer);
	}

	@Override
	public Token getTokenAt(String code, int cursor) throws ParserException {
		for (Lexer subLexer : subLexerList) {
			try {
				return subLexer.getTokenAt(code, cursor);
			} catch (ParserException ignored) {
				//TODO: rewrite this with optional or add method hasTokenAt() to <code>Lexer</code>
			}
		}
		throw new ParserException();
	}
}
