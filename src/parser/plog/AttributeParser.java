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

	public static final EnumSet<TokenType> FOLL = EnumSet.of(
			TokenType.COLON_EQUAL, TokenType.COMMA);

	public AttributeParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		AttrNode node = null;
		if (ExpressionParser.START.contains(token.type())) {
			node = new AttrNode(startLine());
			token = tokenizer().peek();
			Token var = tokenizer().current();
			if (ATTR.contains(token.type())) {
				while (ATTR.contains(token.type())) {
					Node n = null;
					if (tokenizer().peek().type() == TokenType.LEFT_PAREN) {
						CallParser call = new CallParser(this);
						n = call.parse(var);
					} else if (ExpressionParser.START.contains(var.type())) {
						n = ParseUtil.value(var);
					} else {
						error(ErrorCode.INVALID_EXPR);
					}

					if (n != null) {
						node.add(n);
						token = tokenizer().next();
						if (ATTR.contains(token.type())) {
							var = tokenizer().next();
						}
					}

				}
			} else if (tokenizer().current().type() == TokenType.LEFT_BRACKET) {
				ArrayParser arrp = new ArrayParser(this);
				Node n = arrp.parse(tokenizer().current());
				node.add(n);
			} else {
				node.add(ParseUtil.value(tokenizer().current()));
				tokenizer().next();
			}

		} else {
			error(ErrorCode.INVALID_EXPR);
		}

		return node;
	}

}
