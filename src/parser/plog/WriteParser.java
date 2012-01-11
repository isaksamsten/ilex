package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.ExprListNode;
import parser.tree.plog.WriteNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class WriteParser extends Parser {

	public WriteParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		WriteNode node = null;
		token = tokenizer().next();

		ExpressionParser parser = new ExpressionParser(this);
		Node expr = parser.parse(token);
		if (expr != null) {
			node = new WriteNode(startLine());
			node.add(expr);

			token = tokenizer().current();
			if (token.type() == TokenType.COMMA) {
				token = tokenizer().next(); // consume comma
				ExpressionListParser listp = new ExpressionListParser(this);
				ExprListNode list = (ExprListNode) listp.parse(token);
				for (Node n : list.elements()) {
					node.add(n);
				}

			}
		} else {
			error(ErrorCode.INVALID_WRITE);
		}

		return node;
	}

}
