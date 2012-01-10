package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.plog.ExprNode;
import parser.tree.plog.Operator;
import parser.tree.plog.TermNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class ExpressionParser extends Parser<ExprNode> {

	public static final EnumSet<TokenType> OPERATORS = EnumSet.of(
			TokenType.PLUS, TokenType.MINUS, TokenType.STAR, TokenType.SLASH,
			TokenType.PERCENT);

	public static final EnumSet<TokenType> START = EnumSet.of(TokenType.NUMBER,
			TokenType.IDENTIFIER, TokenType.STRING, TokenType.LEFT_BRACKET);

	public ExpressionParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public ExprNode parse(Token token) throws IOException {
		ExprNode node = null;
		if (START.contains(token.type()) && !TokenType.isReserved(token.text())) {
			node = new ExprNode(tokenizer().source().line());

			TermNode lhs = null;
			if (CallParser.START.contains(tokenizer().peek().type())) {
				CallParser callparser = new CallParser(this);
				lhs = callparser.parse(token);
			} else {
				lhs = ParseUtil.value(token);
			}

			node.lhs(lhs);

			token = tokenizer().current();
			if (OPERATORS.contains(token.type())) {
				node.operator(Operator.fromTokenType(token.type()));
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
