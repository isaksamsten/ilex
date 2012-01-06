package parser.tree.plog;

import interpreter.plog.Visitor;

public class AssignNode extends StmtNode {
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

	@Override
	public String toTreeString() {
		String tree = "(" + toString() + " var=" + var.toTreeString()
				+ " expr=" + expr.toTreeString() + ")";

		return tree;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitAssign(this);
	}
}
