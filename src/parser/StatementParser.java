package parser;

import token.EofToken;
import token.ErrorCode;
import token.Token;
import token.TokenType;

import java.io.IOException;

import message.MessageHandler;

import parser.tree.Node;
import parser.tree.NodeAttr;
import parser.tree.NodeType;

public class StatementParser extends IlexParser {

	public StatementParser(Parser parent) {
		super(parent);
	}

	public Node parse(Token token) {
		Node statement = null;
		switch (token.type()) {
		case FUNCTION:
			statement = parseFunction();
			break;
		case IDENTIFIER:
			statement = parseAssignment();
			break;
		default:
			statement = new Node(NodeType.NO_OP);
		}

		statement.attr(NodeAttr.LINE, token.line());

		return statement;
	}

	private Node parseAssignment() {
		return new Node(NodeType.ASSIGN);
	}

	private Node parseFunction() {
		return new Node(NodeType.FUNCTION);
	}

	protected void parseStatements(Token token, Node parent,
			TokenType terminator) {
		while (!token.eof() && token.type() != terminator) {
			Node statement = parse(token);
			parent.add(statement);
			token = tokenizer().current();
			if (token.type() == TokenType.SEMI_COLON) {
				try {
					token = tokenizer().next();
				} catch (IOException e) {
					MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR, e);
				}
			} else if (token.type() == TokenType.IDENTIFIER) {
				MessageHandler.getInstance().error(token,
						ErrorCode.INVALID_STATEMENT_END);
			} else if (token.type() != terminator) {
				MessageHandler.getInstance().error(token,
						ErrorCode.MISSING_TERMINATOR);
			}
		}

		if (token.eof()) {
			MessageHandler.getInstance().fatal(ErrorCode.PREMATURE_EOF);
		}
	}
}
