package parser.tree.plog;

import parser.tree.Node;

public class AssignNode extends Node {
	private VarNode var;
	private ExprNode expr;

	public AssignNode(int line) {
		super(line);
	}

	public VarNode var() {
		return var;
	}

	public void var(VarNode var) {
		this.var = var;
	}

	public ExprNode expr() {
		return this.expr;
	}

	public void expr(ExprNode expr) {
		this.expr = expr;
	}

}
