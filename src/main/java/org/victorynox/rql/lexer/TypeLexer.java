package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenize node value type.
 * @author victorynox
 * @version 0.1
 */
public class TypeLexer implements Lexer {

	@Override
	public Optional<Token> getTokenAt(String code, int cursor) {
		Pattern pattern = Pattern.compile("^[a-z]\\w*(?=:)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(code.substring(cursor));
		if(!matcher.find()) {
			return Optional.empty();
		}
		return Optional.of(new Token(
				TokenType.T_TYPE,
				matcher.group(),
				cursor,
				cursor + matcher.group().length()
		));
	}
}
