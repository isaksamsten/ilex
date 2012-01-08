package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class CompNode extends Node {

	private ExprNode lhs;
	private Operator compareOp;
	private ExprNode rhs;

	public CompNode(int line) {
		super(line);
	}

	public Operator operator() {
		return compareOp;
	}

	public void operator(Operator compareOp) {
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
