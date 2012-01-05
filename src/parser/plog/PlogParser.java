package parser.plog;

import interpreter.Stack;
import interpreter.TableEntry;

import java.io.IOException;

import parser.Parser;
import parser.Tokenizer;
import parser.tree.Node;
import parser.tree.Tree;

import message.MessageHandler;

import token.EofToken;
import token.ErrorCode;
import token.Token;
import token.plog.TokenType;

public class PlogParser extends Parser<Tree> {

	private static final Stack stack = Stack.getInstance();
	private static final MessageHandler messages = MessageHandler.getInstance();

	public PlogParser(Tokenizer tokenizer) {
		super(tokenizer);
	}

	@Override
	public Tree parse() throws IOException {
		Node root = null;
		long elapsed = System.currentTimeMillis();
		Token token = null;
		try {
			AssignmentParser assignment = new AssignmentParser(this);
			root = assignment.parse();
		} catch (IOException e) {
			MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR, e);
		}

		elapsed = System.currentTimeMillis() - elapsed;
		MessageHandler.getInstance().summary(elapsed);

		return new Tree(root);
	}
}
