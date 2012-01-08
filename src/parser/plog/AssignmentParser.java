package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.AssignNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.VarNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class AssignmentParser extends Parser<AssignNode> {

	public AssignmentParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public AssignNode parse(Token token) throws IOException {
		AssignNode node = null;

		if (token.type() == TokenType.IDENTIFIER) {
			node = new AssignNode(tokenizer().source().line());
			node.var(new VarNode(token.line(), (String) token.value()));

			token = tokenizer().next();
			if (token.type() == TokenType.COLON_EQUAL) {
				token = tokenizer().next();

				ExpressionParser exprParser = new ExpressionParser(this);
				ExprNode expr = exprParser.parse(token);
				node.expr(expr);
			} else {
				error(ErrorCode.INVALID_ASSIGN);
			}
		} else {
			error(ErrorCode.INVALID_ASSIGN);
		}
		return node;
	}

}
