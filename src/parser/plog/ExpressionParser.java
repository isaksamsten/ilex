package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.ExprNode;
import parser.tree.plog.NumNode;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class ExpressionParser extends Parser<ExprNode> {

	public ExpressionParser(Parser parent) {
		super(parent);
	}

	@Override
	public ExprNode parse() throws IOException {
		ExprNode node = new ExprNode(tokenizer().source().line());
		Token token = tokenizer().next();
		if (token.type() == TokenType.NUMBER) {
			node.lhs(new NumNode(token.line(), (Number) token.value()));
		} else {
			error(token, ErrorCode.INVALID_CHARACTER);
		}

		return node;
	}

}
