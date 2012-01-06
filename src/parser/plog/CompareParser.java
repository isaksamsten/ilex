package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.plog.CompNode;
import parser.tree.plog.ExprNode;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class CompareParser extends Parser<CompNode> {

	private EnumSet<TokenType> compareOps = EnumSet.of(TokenType.GT,
			TokenType.LT, TokenType.EQUAL);

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
				node.compareOp(token.type().toString());

				token = tokenizer().next();
				expr = parser.parse(token);
				if (expr != null) {
					node.rhs(expr);
				} else {
					error(token, ErrorCode.EXPECTED_EXPR);
				}
			} else {
				error(token, ErrorCode.INVALID_CHARACTER);
			}
		} else {
			error(token, ErrorCode.EXPECTED_EXPR);
		}

		return node;

	}
}
