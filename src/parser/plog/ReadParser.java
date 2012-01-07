package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.ReadNode;
import parser.tree.plog.VarNode;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class ReadParser extends Parser<ReadNode> {

	public ReadParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public ReadNode parse(Token token) throws IOException {
		ReadNode node = null;

		token = tokenizer().next();
		if (token.type() == TokenType.IDENTIFIER) {
			node = new ReadNode(token.line());

			VarNode var = new VarNode(token.line(), (String) token.value());
			node.var(var);
			
			tokenizer().next(); // consume identifier
		} else {
			error(token, ErrorCode.INVALID_READ);
		}

		return node;
	}

}
