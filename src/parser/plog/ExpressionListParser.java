package parser.plog;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import parser.Parser;
import parser.tree.plog.ExprNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class ExpressionListParser extends Parser<List<ExprNode>> {

	public ExpressionListParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public List<ExprNode> parse(Token token) throws IOException {
		List<ExprNode> node = new LinkedList<ExprNode>();
		ExpressionParser parser = new ExpressionParser(this);
		while(token.type() == TokenType.COMMA) {
			token = tokenizer().next();
			ExprNode expr = parser.parse(token);
			if(expr != null) {
				node.add(expr);
			} else {
				error(ErrorCode.INVALID_WRITE);
			}
			token = tokenizer().current();
		}
		
		return node;
	}

}
