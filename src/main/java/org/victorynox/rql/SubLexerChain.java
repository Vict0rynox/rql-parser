package org.victorynox.rql;

import java.util.ArrayList;
import java.util.List;

/**
 * SubLexer chain with lexer list
 * @author victorynox
 * @version 0.1
 */
public class SubLexerChain implements SubLexerInterface{

	/**
	 * subLexerList
	 */
	protected List<SubLexerInterface> subLexerList;

	/**
	 * Init chain with empty list
	 */
	public SubLexerChain()
	{
		subLexerList = new ArrayList<>();
	}

	/**
	 * Add SubLexer to chan
	 * @param subLexer - additional sub lexer 
	 */
	public void addSubLexer(SubLexerInterface subLexer)
	{
		subLexerList.add(subLexer);
	}

	@Override
	public Token getTokenAt(String code, int cursor) throws ParserException {
		for (SubLexerInterface subLexer : subLexerList) {
			try {
				return subLexer.getTokenAt(code, cursor);
			} catch (ParserException ignored) {
				//TODO: rewrite this with optional or add method hasTokenAt() to <code>SubLexerInterface</code>
			}
		}
		throw new ParserException();
	}
}
