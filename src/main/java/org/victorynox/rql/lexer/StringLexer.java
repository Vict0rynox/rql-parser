package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author victorynox
 * @version 0.1
 */
public class StringLexer implements Lexer{

	@Override
	public Optional<Token> getTokenAt(String code, int cursor) throws SyntaxErrorException {

		Pattern pattern = Pattern.compile("([a-z0-9]|\\%[a-z0-9]{2})", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(code.substring(cursor));
		if(!matcher.matches() || matcher.group().matches("[-+]?[0-9]+")) {
			return Optional.empty();
		}

		return Optional.of(new Token(
				TokenType.T_STRING,
				matcher.group(),
				cursor,
				cursor + matcher.group().length()
		));
	}
}
