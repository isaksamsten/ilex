package parser.plog;

import parser.tree.plog.LookupVarNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.StringNode;
import parser.tree.plog.TermNode;
import parser.tree.plog.VarNode;
import token.Token;

public final class ParseUtil {
	/**
	 * @param token
	 * @return
	 */
	public static TermNode value(Token token) {
		TermNode termNode;
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

	public static TermNode var(Token token) {
		return new VarNode(token.line(), (String) token.value());
	}

}
