package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import token.Token;

public class StatementParser extends Parser<Node> {

	public StatementParser(Parser parent) {
		super(parent);
	}

	public Node parse() throws IOException {
		Node statement = null;

		return statement;
	}
}
