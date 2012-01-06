package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class CompNode extends Node {

	private ExprNode lhs;
	private String compareOp;
	private ExprNode rhs;

	public CompNode(int line) {
		super(line);
	}

	public String compareOp() {
		return compareOp;
	}

	public void compareOp(String compareOp) {
		this.compareOp = compareOp;
	}

	public ExprNode lhs() {
		return lhs;
	}

	public void lhs(ExprNode lhs) {
		this.lhs = lhs;
	}

	public ExprNode rhs() {
		return rhs;
	}

	public void rhs(ExprNode rhs) {
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
