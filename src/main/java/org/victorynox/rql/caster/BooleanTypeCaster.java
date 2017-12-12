package org.victorynox.rql.caster;

import org.victorynox.rql.Token;
import org.victorynox.rql.TokenType;
import org.victorynox.rql.TypeCasterInsterface;

/**
 * Cast token value to Boolean type
 * @author victorynox
 * @version 0.1
 */
public class BooleanTypeCaster implements TypeCasterInsterface<Boolean>{
	@Override
	public Boolean typeCast(Token token) {
		if(token.test(new TokenType[]{TokenType.T_NULL})) {
			return false;
		} else if (token.test(new TokenType[]{TokenType.T_TRUE})) {
			return true;
		} else if (token.test(new TokenType[]{TokenType.T_FALSE})) {
			return false;
		} else if (token.test(new TokenType[]{TokenType.T_EMPTY})) {
			return false;
		} else if (token.test(new TokenType[]{TokenType.T_DATE})) {
			return !token.getValue().equals("0000-00-00T00:00:00Z");
		} else {
			return Boolean.valueOf(token.getValue());
		}
	}
}
