package org.victorynox.test.rql;

import org.victorynox.rql.*;
import org.victorynox.rql.exception.SyntaxErrorException;
import org.victorynox.rql.node.AbstractQueryNode;
import org.victorynox.rql.node.operator.logical.AndNode;
import org.victorynox.rql.parser.RqlParser;
import org.victorynox.rql.parser.node.NodeParser;
import org.victorynox.rql.parser.node.QueryNodeParser;
import org.victorynox.rql.parser.node.query.logical.AndNodeParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victorynox
 * @version 0.1
 */
public class TestMain {

	public static void main(String[] args) {

		RqlParser rqlParser = new RqlParser();

		List<Token> tokenList = new ArrayList<>();
		tokenList.add(new Token(TokenType.T_OPERATOR, "eq", 0, 2));
		tokenList.add(new Token(TokenType.T_OPEN_PARENTHESIS, "(", 2, 3));
		tokenList.add(new Token(TokenType.T_STRING, "name", 3, 7));
		tokenList.add(new Token(TokenType.T_COMMA, ",", 7, 8));
		tokenList.add(new Token(TokenType.T_STRING, "value", 8, 13));
		tokenList.add(new Token(TokenType.T_CLOSE_PARENTHESIS, ")", 13, 14));
		tokenList.add(new Token(TokenType.T_END, "", 14, 14));

		TokenStream tokens = new TokenStream(tokenList);
		try {
			Query query = rqlParser.parse(tokens.iterator());
			System.out.println("Parser success. Node: " + query.getQueryNode().toString());
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}
}
