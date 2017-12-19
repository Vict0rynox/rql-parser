package org.victorynox.rql.lexer;

import org.victorynox.rql.ParserException;
import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.exception.LexerNotFoundTokenException;
import org.victorynox.rql.exception.SyntaxErrorException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenize DataTime value
 * @author victorynox
 * @version 0.1
 */
public class DateTimeLexer implements Lexer{

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public Token getTokenAt(String code, int cursor) throws LexerNotFoundTokenException, SyntaxErrorException {
		Pattern pattern = Pattern.compile("(?<formatDate>(?<y>\\d{4})-(?<m>\\d{2})-(?<d>\\d{2})T(?<h>\\d{2}):(?<i>\\d{2}):(?<s>\\d{2}))Z");
		Matcher matcher = pattern.matcher(code.substring(cursor));
		if(!matcher.matches()) {
			throw new LexerNotFoundTokenException();
		}

		if(!validateFormatDate(matcher.group("formatDate"))) {
			throw new SyntaxErrorException();
		}

		return new Token(
				TokenType.T_DATE,
				matcher.group("formatDate"),
				cursor,
				cursor + matcher.group("formatDate").length()
		);
	}

	/**
	 * Check formatDate is valid .
	 * @param formatDate data in string with format {@literal #DateTimeLexer.DATE_FORMAT}
	 * @return true if valid.
	 */
	protected boolean validateFormatDate(String formatDate)
	{
		//This method not 'best practice', but in 0.0.1 is 'normal' solution.... See 'HIDE THE PAIN'
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeLexer.DATE_FORMAT);
			dateFormat.setLenient(false);
			dateFormat.parse(formatDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
