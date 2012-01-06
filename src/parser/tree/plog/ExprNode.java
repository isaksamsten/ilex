package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class ExprNode extends Node {

	private TermNode lhs;
	private String operator;
	private ExprNode rhs;

	public ExprNode(int line) {
		super(line);
	}

	public TermNode lhs() {
		return lhs;
	}

	public void lhs(TermNode lhs) {
		this.lhs = lhs;
	}

	public String operator() {
		return operator;
	}

	public void operator(String operator) {
		this.operator = operator;
	}

	public ExprNode rhs() {
		return rhs;
	}

	public void rhs(ExprNode rhs) {
		this.rhs = rhs;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " operator='"
				+ (operator() != null ? operator() : "none") + "' lhs="
				+ lhs.toTreeString() + " rhs="
				+ (rhs != null ? rhs.toTreeString() : "none") + ")";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitExpr(this);
	}

}
