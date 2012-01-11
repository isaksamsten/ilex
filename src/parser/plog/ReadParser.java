package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.ReadNode;
import parser.tree.plog.VarNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class ReadParser extends Parser {

	public ReadParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		ReadNode node = null;

		token = tokenizer().next();
		if (token.type() == TokenType.IDENTIFIER) {
			node = new ReadNode(token.line());

			VarNode var = new VarNode(token.line(), (String) token.value());
			node.var(var);

			tokenizer().next(); // consume identifier
		} else {
			error(ErrorCode.INVALID_READ);
		}

		return node;
	}

}
