package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class TermNode extends Node {

	private TermNode value;

	public TermNode(int line) {
		super(line);
	}

	public TermNode value() {
		return this.value;
	}

	public void value(TermNode value) {
		this.value = value;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitTerm(this);
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " value=" + value.toTreeString() + ")";
	}
}
