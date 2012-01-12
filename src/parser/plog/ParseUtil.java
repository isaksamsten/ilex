package parser.plog;

import parser.tree.Node;
import parser.tree.plog.LookupVarNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.StringNode;
import parser.tree.plog.VarNode;
import token.Token;

public final class ParseUtil {
	/**
	 * @param token
	 * @return
	 */
	public static Node value(Token token) {
		Node termNode;
		switch (token.type()) {
		case NUMBER:
			termNode = new NumNode(token.line(), (Number) token.value());
			break;
		case STRING:
			termNode = new StringNode(token.line(), (String) token.value());
			break;
		case LEFT_BRACKET:
			throw new UnsupportedOperationException("Can't create arrays yet");
		default:
			termNode = new LookupVarNode(token.line(), token.text());
		}
		return termNode;
	}

	public static Node attr(Token token) {
		return new LookupVarNode(token.line(), (String) token.value());
	}

	public static Node var(Token token) {
		return new VarNode(token.line(), (String) token.value());
	}

}
