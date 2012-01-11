package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class ExprNode extends StmtNode {

	private Node lhs;
	private Operator operator;
	private Node rhs;

	public ExprNode(int line) {
		super(line);
	}

	public Node lhs() {
		return lhs;
	}

	public void lhs(Node lhs) {
		this.lhs = lhs;
	}

	public Operator operator() {
		return operator;
	}

	public void operator(Operator operator) {
		this.operator = operator;
	}

	public Node rhs() {
		return rhs;
	}

	public void rhs(Node rhs) {
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
