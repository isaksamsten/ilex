package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class AssignNode extends StmtNode {
	private Node var;
	private Node expr;

	public AssignNode(int line) {
		super(line);
	}

	public Node var() {
		return var;
	}

	public void var(Node var) {
		this.var = var;
	}

	public Node expr() {
		return this.expr;
	}

	public void expr(Node expr) {
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
