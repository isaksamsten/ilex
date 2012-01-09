package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.Node;
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

	public static final EnumSet<TokenType> operators = EnumSet.of(
			TokenType.PLUS, TokenType.MINUS, TokenType.STAR, TokenType.SLASH,
			TokenType.PERCENT);

	private static EnumSet<TokenType> start = EnumSet.of(TokenType.NUMBER,
			TokenType.IDENTIFIER, TokenType.STRING, TokenType.LEFT_BRACKET);

	public ExpressionParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public ExprNode parse(Token token) throws IOException {
		ExprNode node = null;
		if (start.contains(token.type()) && !TokenType.isReserved(token.text())) {
			node = new ExprNode(tokenizer().source().line());
			TermNode lhs = new TermNode(token.line());

			if (tokenizer().peek().type() == TokenType.DOT) {
				Node calle = extractVar(token);
				TermNode t = new TermNode(token.line());
				t.term(calle);

				token = tokenizer().next(); // consume dot
				token = tokenizer().next();
				CallNode call = new CallNode(token.line());
				call.lhs(t);
				call.add(new VarNode(token.line(), (String) token.value()));
				
				TermNode tt = new TermNode(token.line());
				tt.term(call);

				node.lhs(tt);				
				//tokenizer().next();
			} else {
				Node termNode = extractVar(token);
				lhs.term(termNode);
				node.lhs(lhs);
			}

			token = tokenizer().next();
			if (operators.contains(token.type())) {
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
	protected Node extractVar(Token token) {
		Node termNode;
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
