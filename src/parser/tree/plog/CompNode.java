package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class CompNode extends Node {

	private Node lhs;
	private Operator compareOp;
	private Node rhs;

	public CompNode(int line) {
		super(line);
	}

	public Operator operator() {
		return compareOp;
	}

	public void operator(Operator compareOp) {
		this.compareOp = compareOp;
	}

	public Node lhs() {
		return lhs;
	}

	public void lhs(Node lhs) {
		this.lhs = lhs;
	}

	public Node rhs() {
		return rhs;
	}

	public void rhs(Node rhs) {
		this.rhs = rhs;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " lhs=" + lhs.toTreeString() + " compareOp="
				+ compareOp + " rhs=" + rhs.toTreeString() + ")";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitComp(this);
	}

}
