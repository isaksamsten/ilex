package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.ExprNode;
import parser.tree.plog.WriteNode;
import token.ErrorCode;
import token.Token;

public class WriteParser extends Parser<WriteNode> {

	public WriteParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public WriteNode parse(Token token) throws IOException {
		WriteNode node = null;
		token = tokenizer().next();

		ExpressionParser parser = new ExpressionParser(this);
		ExprNode expr = parser.parse(token);
		if (expr != null) {
			node = new WriteNode(startLine());
			node.expr(expr);			
		} else {
			error(tokenizer().current(), ErrorCode.EXPECTED_EXPR);
		}

		return node;
	}

}
