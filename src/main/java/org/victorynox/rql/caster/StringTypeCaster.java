package org.victorynox.rql.caster;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cast token value to String type
 * @author victorynox
 * @version 0.1
 */
public class StringTypeCaster implements TypeCaster<String> {
	@Override
	public String typeCast(Token token) {
		if(token.test(new TokenType[]{TokenType.T_NULL})) {
			return "null";
		} else if (token.test(new TokenType[]{TokenType.T_TRUE})) {
			return "true";
		} else if (token.test(new TokenType[]{TokenType.T_FALSE})) {
			return "false";
		} else if (token.test(new TokenType[]{TokenType.T_EMPTY})) {
			return "";
		} else if (token.test(new TokenType[]{TokenType.T_DATE})) {
			try {
				return URLDecoder.decode(token.getValue(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		} else {
			return token.getValue();
		}
	}

	@Override
	public Class getType() {
		return String.class;
	}
}