package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.ExprNode;
import parser.tree.plog.WriteNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

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
			node.add(expr);

			token = tokenizer().current();
			while(token.type() == TokenType.COMMA) {
				token = tokenizer().next();
				expr = parser.parse(token);
				if(expr != null) {
					node.add(expr);
				} else {
					error(ErrorCode.INVALID_WRITE);
				}
			}

		} else {
			error(ErrorCode.INVALID_WRITE);
		}

		return node;
	}

}
