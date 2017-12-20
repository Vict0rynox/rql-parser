package org.victorynox.rql.lexer;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenize Number string
 *
 * @author victorynox
 * @version 0.1
 */
public class NumberLexer implements Lexer {
	@Override
	public Optional<Token> getTokenAt(String code, int cursor) throws SyntaxErrorException {
		Pattern pattern = Pattern.compile("[-+]?[\\d]*\\.?[\\d]+(?:[eE][-+]?[\\d]+)?");
		Matcher matcher = pattern.matcher(code.substring(cursor));
		if (matcher.matches()) {
			return Optional.empty();
		}

		return Optional.of(new Token(
				isInteger(matcher.group()) ? TokenType.T_INTEGER : TokenType.T_FLOAT,
				matcher.group(),
				cursor,
				cursor + matcher.group().length()
		));
	}

	/**
	 * @param numeric string numerical value
	 * @return true if string is valid int
	 */
	private boolean isInteger(String numeric) {
		try {
			//noinspection ResultOfMethodCallIgnored
			Integer.parseInt(numeric);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
		return true;
	}

}
