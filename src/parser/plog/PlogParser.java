package parser.plog;

import java.io.IOException;

import message.MessageHandler;
import parser.Parser;
import parser.Tokenizer;
import parser.tree.Node;
import parser.tree.Tree;
import token.ErrorCode;
import token.Token;

public class PlogParser extends Parser<Tree> {

	public PlogParser(Tokenizer tokenizer) {
		super(tokenizer);
	}

	@Override
	public Tree parse(Token token) throws IOException {
		Node root = null;
		long elapsed = System.currentTimeMillis();
		try {
			StatementParser assignment = new StatementParser(this);
			root = assignment.parse(token);
		} catch (IOException e) {
			MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR, e);
		}

		elapsed = System.currentTimeMillis() - elapsed;
		MessageHandler.getInstance().parserSummary(elapsed);

		return new Tree(root);
	}
}
