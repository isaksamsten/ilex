package parser;

import token.ErrorCode;
import token.Token;

import java.io.IOException;

import message.MessageHandler;

import parser.tree.Node;
import parser.tree.NodeType;

public class FunctionParser extends StatementParser {

	public FunctionParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) {
		Node function = new Node(NodeType.FUNCTION);
		try {
			token = tokenizer().next(); // extract the function
		} catch (IOException e) {
			MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR);
		}

		return function;

	}

}
