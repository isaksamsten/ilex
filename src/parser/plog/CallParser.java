package parser.plog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import parser.tree.plog.CallNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.TermNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class CallParser extends Parser<CallNode> {

	public CallParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public CallNode parse(Token token) throws IOException {
		CallNode call = null;

		if (ExpressionParser.START.contains(token.type())) {
			call = new CallNode(token.line());
			TermNode root = ParseUtil.value(token);
			call.add(root);

			token = tokenizer().next();
			if (token.type() == TokenType.DOT) {
				parseCall(token, call);
			} else {
				error(ErrorCode.MISSING_DOT);
			}
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
		while (token.type() == TokenType.DOT) {
			token = tokenizer().next();
			if (token.type() == TokenType.IDENTIFIER) {
				call.add(ParseUtil.var(token));

				token = tokenizer().next();
				if (token.type() == TokenType.LEFT_PAREN) {
					token = tokenizer().next();

					if (token.type() != TokenType.RIGHT_PAREN) {
						ExpressionListParser list = new ExpressionListParser(
								this);
						List<ExprNode> node = list.parse(token);
						call.add(node);
					} else {
						call.add(new ArrayList<ExprNode>());
					}

					token = tokenizer().current();
					if (token.type() != TokenType.RIGHT_PAREN) {
						error(ErrorCode.EXPECT_END_PAREN);
					}
				} else {
					error(ErrorCode.EXPECT_START_PAREN);
				}

			} else {
				error(ErrorCode.EXPECTED_IDENTIFIER);
			}
			token = tokenizer().next();
		}
	}

}
