package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.AbstractListNode;
import parser.tree.plog.AssignNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.VarNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class AssignmentParser extends Parser {

	public AssignmentParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		AssignNode node = null;

		if (ExpressionParser.START.contains(token.type())) {
			node = new AssignNode(tokenizer().source().line());

			ExpressionParser attr = new ExpressionParser(this);
			ExprNode var = (ExprNode) attr.parse(token);

			token = tokenizer().current();
			if (token.type() == TokenType.COLON_EQUAL) {
				if (var != null
						&& var.rhs() == null
						&& ((AbstractListNode) var.lhs()).elements().get(
								((AbstractListNode) var.lhs()).elements()
										.size() - 1) instanceof VarNode) {
					node.var(var);
				} else {
					error(ErrorCode.ASSIGN_TO_FUNCTION_CALL);
				}

				token = tokenizer().next();

				ExpressionParser exprParser = new ExpressionParser(this);
				Node expr = exprParser.parse(token);
				node.expr(expr);
			} else {
				return var;
			}

		} else {
			error(ErrorCode.INVALID_ASSIGN);
		}
		return node;
	}

}
