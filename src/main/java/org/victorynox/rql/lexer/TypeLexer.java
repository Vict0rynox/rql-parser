package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.LexerNotFoundTokenException;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenize node value type.
 * @author victorynox
 * @version 0.1
 */
public class TypeLexer implements Lexer {

	@Override
	public Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException, SyntaxErrorException {
		Pattern pattern = Pattern.compile("[a-z]\\w*(?=:)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(code.substring(cursor));
		if(!matcher.matches()) {
			throw new LexerNotFoundTokenException();
		}
		return new Token(
				TokenType.T_TYPE,
				matcher.group(),
				cursor,
				cursor + matcher.group().length()
		);
	}
}
