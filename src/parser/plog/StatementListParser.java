package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.StmtListNode;
import parser.tree.plog.StmtNode;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class StatementListParser extends Parser<StmtListNode> {

	public StatementListParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public StmtListNode parse(Token token) throws IOException {
		int startLine = token.line();

		token = tokenizer().next(); // consume BEGIN
		StmtListNode stmtList = null;
		StatementParser parser = new StatementParser(this);
		StmtNode node = parser.parse(token);

		if (node != null) {
			stmtList = new StmtListNode(startLine);
			stmtList.add(node);

			token = tokenizer().current();
			while ((node = parser.parse(token)) != null) {
				stmtList.add(node);
				token = tokenizer().current();
			}

			if (token.type() != TokenType.END) {
				error(token, ErrorCode.UNEXPECTED_END_OF_STATEMENT_LIST);
			} else {
				tokenizer().next();
			}
		} else {
			error(token, ErrorCode.UNEXPECTED_START_OF_STATEMENT);
		}

		return stmtList;
	}

}
