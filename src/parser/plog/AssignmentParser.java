package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.AssignNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.VarNode;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class AssignmentParser extends Parser<AssignNode> {

	public AssignmentParser(Parser parent) {
		super(parent);
	}

	@Override
	public AssignNode parse() throws IOException {
		AssignNode node = new AssignNode(tokenizer().source().line());
		Token token = tokenizer().next();
		node.var(new VarNode(token.line(), token.text()));

		token = tokenizer().next();
		if (token.type() == TokenType.COLON
				&& (token = tokenizer().next()).type() == TokenType.EQUAL) {
			ExpressionParser exprParser = new ExpressionParser(this);
			ExprNode expr = exprParser.parse();
			node.expr(expr);
		} else {
			error(token, ErrorCode.INVALID_ASSIGN);
		}
		return node;
	}

}
