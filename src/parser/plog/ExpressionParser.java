package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.plog.CallNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.LookupVarNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.Operator;
import parser.tree.plog.StringNode;
import parser.tree.plog.TermNode;
import parser.tree.plog.VarNode;
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

			if (tokenizer().peek().type() == TokenType.DOT) {
				TermNode calle = extractVar(token);

				token = tokenizer().next(); // consume dot
				token = tokenizer().next();
				CallNode call = new CallNode(token.line());
				call.add(calle);
				call.add(new VarNode(token.line(), (String) token.value()));
				node.lhs(call);
			} else {
				TermNode lhs = extractVar(token);
				node.lhs(lhs);
			}

			token = tokenizer().next();
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

	/**
	 * @param token
	 * @return
	 */
	protected TermNode extractVar(Token token) {
		TermNode termNode;
		switch (token.type()) {
		case NUMBER:
			termNode = new NumNode(token.line(), (Number) token.value());
			break;
		case STRING:
			termNode = new StringNode(token.line(), (String) token.value());
			break;
		case LEFT_BRACKET:
			throw new UnsupportedOperationException("Can't create arrays yet");
		default:
			termNode = new LookupVarNode(token.line(), token.text());
		}
		return termNode;
	}
}
