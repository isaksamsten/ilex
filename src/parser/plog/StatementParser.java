package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.StmtNode;
import token.Token;
import token.plog.TokenType;

public class StatementParser extends Parser<StmtNode> {

	public StatementParser(Parser<?> parent) {
		super(parent);
	}

	public StmtNode parse(Token token) throws IOException {
		StmtNode node = null;
		Parser<? extends StmtNode> parser = null;
		if (token.type() == TokenType.IDENTIFIER) {
			parser = new AssignmentParser(this);
		} else if (token.type() == TokenType.READ) {
			parser = new ReadParser(this);
		} else if (token.type() == TokenType.WRITE) {
			parser = new WriteParser(this);
		} else if (token.type() == TokenType.BEGIN) {
			parser = new StatementListParser(this);
		} else if (token.type() == TokenType.WHILE) {
			parser = new WhileParser(this);
		}

		if (parser != null) {
			node = parser.parse(token);
		}

		return node;
	}
}
