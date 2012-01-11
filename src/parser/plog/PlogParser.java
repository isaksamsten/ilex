package parser.plog;

import java.io.IOException;

import message.MessageHandler;
import parser.Parser;
import parser.Tokenizer;
import parser.tree.Node;
import token.Token;
import token.plog.ErrorCode;

public class PlogParser extends Parser {

	public PlogParser(Tokenizer tokenizer) {
		super(tokenizer);
	}

	@Override
	public Node parse(Token token) throws IOException {
		Node root = null;
		long elapsed = System.currentTimeMillis();
		try {
			StatementParser assignment = new StatementParser(this, true);
			root = assignment.parse(token);
		} catch (IOException e) {
			MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR, e);
		}

		elapsed = System.currentTimeMillis() - elapsed;
		MessageHandler.getInstance().parserSummary(elapsed);

		return root;
	}
}
