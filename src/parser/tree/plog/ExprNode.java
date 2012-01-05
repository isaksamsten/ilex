package parser.tree.plog;

import parser.tree.Node;

public class ExprNode extends Node {

	private NumNode lhs;
	private String operator;
	private ExprNode rhs;

	public ExprNode(int line) {
		super(line);
	}

	public NumNode lhs() {
		return lhs;
	}

	public void lhs(NumNode lhs) {
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

}
