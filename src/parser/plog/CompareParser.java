package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.plog.CompNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.Operator;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class CompareParser extends Parser<CompNode> {

	private EnumSet<TokenType> compareOps = EnumSet.of(TokenType.GT,
			TokenType.LT, TokenType.LTE, TokenType.GTE, TokenType.EQUAL);

	public CompareParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public CompNode parse(Token token) throws IOException {
		CompNode node = null;

		ExpressionParser parser = new ExpressionParser(this);
		ExprNode expr = parser.parse(token);

		token = tokenizer().current();
		if (expr != null) {
			node = new CompNode(startLine());
			node.lhs(expr);

			token = tokenizer().current();
			if (compareOps.contains(token.type())) {
				node.operator(Operator.fromTokenType(token.type()));

				token = tokenizer().next();
				expr = parser.parse(token);
				if (expr != null) {
					node.rhs(expr);
				} else {
					error(ErrorCode.INVALID_EXPR);
				}
			} else {
				error(ErrorCode.UNEXPECTED_OPERATOR);
			}
		} else {
			error(ErrorCode.INVALID_EXPR);
		}

		return node;

	}
}
