package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.Node;
import parser.tree.plog.IfNode;
import token.Token;
import token.plog.ErrorCode;
import token.plog.TokenType;

/**
 * TODO: add support for single statements IF
 * @author isak
 *
 */
public class IfParser extends Parser {

	public IfParser(Parser parent) {
		super(parent);
	}

	@Override
	public Node parse(Token token) throws IOException {
		IfNode node = null;
		token = tokenizer().next(); // consume if

		CompareParser compare = new CompareParser(this);
		Node cmpNode = compare.parse(token);
		if (cmpNode != null) {
			node = new IfNode(startLine());
			node.compare(cmpNode);

			StatementListParser parser = new StatementListParser(this);
			Node list = parser.parse(tokenizer().current());
			if (list != null) {
				node.trueStmt(list);

				token = tokenizer().current();
				if (token.type() == TokenType.ELSE) {
					parser = new StatementListParser(this);
					list = parser.parse(tokenizer().next()); // consume ELSE and
																// parse
					if (list != null) {
						node.falseStmt(list);
					} else {
						error(ErrorCode.EXPECTED_STATEMENT_LIST);
					}
				}
			} else {
				error(ErrorCode.EXPECTED_STATEMENT_LIST);
			}
		} else {
			error(ErrorCode.EXPECTED_COMPARE);
		}

		return node;
	}

}
