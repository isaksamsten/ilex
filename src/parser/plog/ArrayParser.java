package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.ArrayNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class ArrayParser extends Parser {

	public ArrayParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		ArrayNode node = null;
		if (token.type() == TokenType.LEFT_BRACKET) {
			node = new ArrayNode(startLine());
			token = tokenizer().next();
			ExpressionListParser listp = new ExpressionListParser(this);
			Node items = listp.parse(token);
			token = tokenizer().current();
			if (items != null && token.type() == TokenType.RIGHT_BRACKET) {
				node.items(items);
			} else {
				error(ErrorCode.INVALID_EXPR);
			}
			tokenizer().next();
		}

		return node;
	}

}
