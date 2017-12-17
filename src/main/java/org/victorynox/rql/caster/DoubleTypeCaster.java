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
				Date date = DateFormat.getInstance().parse(token.getValue());
				SimpleDateFormat dateFormat = new SimpleDateFormat("YmdHis");
				return Double.valueOf(dateFormat.format(date));
			} catch (ParseException e) {
				return 0.;
			}
		} else {
			return Double.valueOf(token.getValue());
		}
	}
}