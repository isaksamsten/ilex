package parser.plog;

import java.io.IOException;

import parser.Parser;
import parser.tree.plog.CompNode;
import parser.tree.plog.StmtListNode;
import parser.tree.plog.WhileNode;
import token.ErrorCode;
import token.Token;

public class WhileParser extends Parser<WhileNode> {

	public WhileParser(Parser<?> parent) {
		super(parent);
	}

	@Override
	public WhileNode parse(Token token) throws IOException {
		WhileNode node = null;
		token = tokenizer().next(); // consume while

		CompareParser compParser = new CompareParser(this);
		CompNode compNode = compParser.parse(token);

		token = tokenizer().current();
		if (compNode != null) {
			node = new WhileNode(startLine());
			node.compare(compNode);

			token = tokenizer().current();
			StatementListParser slp = new StatementListParser(this);
			StmtListNode statementListNode = slp.parse(token);
			
			if(statementListNode != null) {
				node.statementList(statementListNode);
			} else {
				error(tokenizer().current(), ErrorCode.WHILE_NO_BODY);
			}

		} else {
			error(token, ErrorCode.INVALID_CHARACTER);
		}

		return node;
	}
}
