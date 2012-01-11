package parser.plog;

import java.io.IOException;
import java.util.EnumSet;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.AttrNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class AttributeParser extends Parser {

	public static final EnumSet<TokenType> ATTR = EnumSet.of(TokenType.DOT,
			TokenType.LEFT_PAREN);

	public AttributeParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		AttrNode node = null;
		if (ExpressionParser.START.contains(token.type())) {
			node = new AttrNode(startLine());
			while (ExpressionParser.START.contains(token.type())) {
				Node n = null;
				if (tokenizer().peek().type() == TokenType.LEFT_PAREN) {
					CallParser call = new CallParser(this);
					n = call.parse(token);

					token = tokenizer().next(); // consume dot
				} else if (ExpressionParser.START.contains(token.type())) {
					n = ParseUtil.value(token);
					token = tokenizer().next(); // consume DOTw
				} else {
					error(ErrorCode.INVALID_EXPR);
				}

				if (n != null) {
					node.add(n);
					if (token.type() == TokenType.DOT) //TODO:fulhack
						token = tokenizer().next();
				}

			}

		} else {
			error(ErrorCode.INVALID_EXPR);
		}

		return node;
	}

}
