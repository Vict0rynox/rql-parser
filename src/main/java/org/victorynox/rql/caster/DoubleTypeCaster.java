package org.victorynox.rql.caster;


import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cast token value to Double type
 * @author victorynox
 * @version 0.1
 */
public class DoubleTypeCaster implements TypeCaster<Double> {
	@Override
	public Double typeCast(Token token) {
		if(token.test(new TokenType[]{TokenType.T_NULL})) {
			return 0.;
		} else if (token.test(new TokenType[]{TokenType.T_TRUE})) {
			return 1.;
		} else if (token.test(new TokenType[]{TokenType.T_FALSE})) {
			return 0.;
		} else if (token.test(new TokenType[]{TokenType.T_EMPTY})) {
			return 0.;
		} else if (token.test(new TokenType[]{TokenType.T_DATE})) {
			try {
				final DateFormat fromDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				fromDateFormat.setLenient(false);
				Date date = fromDateFormat.parse(token.getValue());
				final SimpleDateFormat toDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String formatData = toDateFormat.format(date);
				return Double.valueOf(formatData);
			} catch (ParseException e) {
				return 0.;
			}
		} else {
			return Double.valueOf(token.getValue());
		}
	}

	@Override
	public Class getType() {
		return Double.class;
	}
}