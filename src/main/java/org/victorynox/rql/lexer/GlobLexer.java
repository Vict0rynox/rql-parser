package org.victorynox.rql.lexer;

import org.victorynox.rql.Glob;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenized glob string
 * @author victorynox
 * @version 0.1
 */
public class GlobLexer implements Lexer {

	@Override
	public Optional<Token> getTokenAt(String code, int cursor) throws SyntaxErrorException {
		Pattern pattern = Pattern.compile("", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(code.substring(cursor));
		if (!matcher.matches() || (!matcher.group().contains("*") && !matcher.group().contains("?"))) {
			return Optional.empty();
		}

		return Optional.of(new Token(
				TokenType.T_GLOB,
				decodeGlob(matcher.group()),
				cursor,
				cursor + matcher.group().length()
		));
	}

	/**
	 * Decode glob string
	 * @param globString string with glob
	 */
	private String decodeGlob(String globString) throws SyntaxErrorException {
		//noinspection RegExpRedundantEscape
		Pattern pattern = Pattern.compile("[^\\*\\?]+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(globString);
		while (matcher.find()) {
			String find = matcher.group();
			String replaceTo;
			try {
				replaceTo = Glob.encoded(URLDecoder.decode(find, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new SyntaxErrorException("Unsupported encoding by decode string.");
			}
			globString = globString.replace(find, replaceTo);
		}
		return globString;
	}

}
