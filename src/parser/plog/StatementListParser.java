package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.StmtListNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class StatementListParser extends Parser {

	public StatementListParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		StmtListNode stmtList = null;
		if (token.type() == TokenType.BEGIN) {
			token = tokenizer().next(); // consume BEGIN
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

				if (token.type() != TokenType.END) {
					error(ErrorCode.UNEXPECTED_END_OF_STATEMENT_LIST);
				} else {
					tokenizer().next();
				}
			} else {
				error(ErrorCode.UNEXPECTED_START_OF_STATEMENT);
			}
		} else {
			error(ErrorCode.EXPECTED_BEGIN);
		}

		return stmtList;
	}

}
