package parser.plog;

import java.io.IOException;

import message.MessageHandler;
import parser.Parser;
import parser.Tokenizer;
import parser.tree.Node;
import parser.tree.plog.StmtListNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class PlogParser extends Parser {

	private boolean file = false;

	public PlogParser(Tokenizer t, boolean file) {
		super(t);
		this.file = file;
	}

	public PlogParser(Tokenizer tokenizer) {
		super(tokenizer);
	}

	@Override
	public Node parse(Token token) throws IOException {
		Node root = null;
		long elapsed = System.currentTimeMillis();
		try {
			if (file) {
				StmtListNode stmtList = null;
				StatementParser parser = new StatementParser(this, true);
				Node node = parser.parse(token);

				if (node != null) {
					stmtList = new StmtListNode(startLine());
					stmtList.add(node);

					token = tokenizer().current();
					while ((node = parser.parse(token)) != null) {
						stmtList.add(node);
						token = tokenizer().current();
					}

					if (token.type() != TokenType.END_OF_FILE) {
						error(ErrorCode.UNEXPECTED_END_OF_STATEMENT_LIST);
					} else {
						tokenizer().next();
					}
				} else {
					error(ErrorCode.UNEXPECTED_START_OF_STATEMENT);
				}
				root = stmtList;
			} else {
				StatementParser assignment = new StatementParser(this, true);
				root = assignment.parse(token);
			}
		} catch (IOException e) {
			MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR, e);
		}

		elapsed = System.currentTimeMillis() - elapsed;
		MessageHandler.getInstance().parserSummary(elapsed);

		return root;
	}
}
