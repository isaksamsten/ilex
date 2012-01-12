package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

public class StatementParser extends Parser {

	private boolean silent = false;

	public StatementParser(Parser parent, boolean silent) {
		super(parent);
		this.silent = silent;
	}

	public StatementParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		Node node = null;
		Parser parser = null;
		if (ExpressionParser.START.contains(token.type())) {
			parser = new IdentifierParser(this);
		} else if (token.type() == TokenType.READ) {
			parser = new ReadParser(this);
		} else if (token.type() == TokenType.WRITE) {
			parser = new WriteParser(this);
		} else if (token.type() == TokenType.BEGIN) {
			parser = new StatementListParser(this);
		} else if (token.type() == TokenType.WHILE) {
			parser = new WhileParser(this);
		} else if (token.type() == TokenType.IF) {
			parser = new IfParser(this);
		} else if (token.type() == TokenType.DEF) {
			throw new UnsupportedOperationException(
					"Can't define functions. yet.");
		}

		if (parser != null) {
			node = parser.parse(token);
		}

		if (!silent && node == null) {
			error(ErrorCode.INVALID_STATEMENT_END);
		}

		return node;
	}
}
