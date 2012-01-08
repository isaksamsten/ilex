package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class TermNode extends Node {

	private Node term;

	public TermNode(int line) {
		super(line);
	}

	public Node term() {
		return this.term;
	}

	public void term(Node value) {
		this.term = value;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitTerm(this);
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " term=" + term.toTreeString() + ")";
	}
}
