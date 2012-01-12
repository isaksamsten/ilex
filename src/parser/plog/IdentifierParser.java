package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import token.Token;

/**
 * 
 * @author isak
 * 
 */
public class IdentifierParser extends Parser {

	public IdentifierParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		Node node = null;
		if (ExpressionParser.START.contains(token.type())) {
			AssignmentParser parser = new AssignmentParser(this);
			node = parser.parse(token);
			token = tokenizer().current();
		} else {

		}

		return node;
	}
}
