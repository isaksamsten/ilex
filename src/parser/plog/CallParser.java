package parser.plog;

import java.io.IOException;
import java.util.EnumSet;
import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.CallNode;
import parser.tree.plog.ExprListNode;
import parser.tree.plog.TermNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class CallParser extends Parser {

	public static final EnumSet<TokenType> START = EnumSet.of(TokenType.DOT,
			TokenType.LEFT_PAREN);

	public CallParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		CallNode call = null;

		if (ExpressionParser.START.contains(token.type())) {
			call = new CallNode(token.line());

			Node root = ParseUtil.value(token);
			if (tokenizer().peek().type() == TokenType.LEFT_PAREN) {
				tokenizer().next(); // consume LEFT
				call.name(root);

				token = tokenizer().next();
				parseExprList(token, call);
				token = tokenizer().current();
				if (token.type() != TokenType.RIGHT_PAREN) {
					error(ErrorCode.EXPECT_END_PAREN);
				}
			} else {
				call.name(root);
				tokenizer().next();
			}
			// else {
			// error(ErrorCode.MISSING_DOT);
			// }
		}

		return call;
	}

	/**
	 * @param token
	 * @param call
	 * @return
	 * @throws IOException
	 */
	protected void parseCall(Token token, CallNode call) throws IOException {
//		while (token.type() == TokenType.DOT) {
//			token = tokenizer().next();
//			if (token.type() == TokenType.IDENTIFIER) {
//				call.add(ParseUtil.var(token));
//
//				token = tokenizer().next();
//				if (token.type() == TokenType.LEFT_PAREN) {
//					token = tokenizer().next();
//					parseExprList(token, call);
//					token = tokenizer().current();
//					if (token.type() != TokenType.RIGHT_PAREN) {
//						error(ErrorCode.EXPECT_END_PAREN);
//					}
//				} else {
//					error(ErrorCode.EXPECT_START_PAREN);
//				}
//
//			} else {
//				error(ErrorCode.EXPECTED_IDENTIFIER);
//			}
//			token = tokenizer().next();
//		}
	}

	private void parseExprList(Token token, CallNode call) throws IOException {
		if (token.type() != TokenType.RIGHT_PAREN) {
			ExpressionListParser list = new ExpressionListParser(this);
			Node node = list.parse(token);
			if (node != null) {
				call.argument(node);
			}
		}
	}

}
