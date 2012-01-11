package parser.plog;

import java.io.IOException;
import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.ExprListNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class ExpressionListParser extends Parser {

	public ExpressionListParser(Parser parent) {
		super(parent);
	}

	/**
	 * Don't return null on failure (just an empty list, check for that)
	 */
	@Override
	public Node parse(Token token) throws IOException {
		ExprListNode node = null;
		ExpressionParser parser = new ExpressionParser(this);
		Node expr = parser.parse(token);
		if (expr != null) {
			node = new ExprListNode(startLine());
			node.add(expr);
			token = tokenizer().current();
			while (token.type() == TokenType.COMMA) {
				token = tokenizer().next();
				expr = parser.parse(token);
				if (expr != null) {
					node.add(expr);
				} else {
					error(ErrorCode.INVALID_EXPR);
				}
				token = tokenizer().current();
			}
		}

		return node;
	}

}
