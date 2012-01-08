package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.plog.ExprNode;
import parser.tree.plog.LookupVarNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.StringNode;
import parser.tree.plog.TermNode;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class ExpressionParser extends Parser<ExprNode> {

	private EnumSet<TokenType> operators = EnumSet.of(TokenType.PLUS,
			TokenType.MINUS, TokenType.STAR, TokenType.SLASH);

	public ExpressionParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public ExprNode parse(Token token) throws IOException {
		ExprNode node = null;
		if (token.type() == TokenType.NUMBER
				|| (token.type() == TokenType.IDENTIFIER && !TokenType
						.isReserved(token.text()))
				|| token.type() == TokenType.STRING) {
			node = new ExprNode(tokenizer().source().line());

			TermNode term = new TermNode(token.line());
			if (token.type() == TokenType.NUMBER) {
				term.term(new NumNode(token.line(), (Number) token.value()));
			} else if (token.type() == TokenType.STRING) {
				term.term(new StringNode(token.line(), (String) token.value()));
			} else {
				term.term(new LookupVarNode(token.line(), token.text()));
			}

			node.lhs(term);

			if (operators.contains((token = tokenizer().next()).type())) {
				node.operator(token.type().toString());
				token = tokenizer().next(); // consume operator.

				ExpressionParser subExpr = new ExpressionParser(this);
				ExprNode rhsNode = subExpr.parse(token);
				node.rhs(rhsNode);
			}
		} else {
			error(ErrorCode.INVALID_EXPR);
		}

		return node;
	}
}
