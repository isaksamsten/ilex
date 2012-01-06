package parser.tree.plog;

import interpreter.plog.Visitor;

public class WriteNode extends StmtNode {

	private ExprNode expr;

	public WriteNode(int line) {
		super(line);
	}

	public ExprNode expr() {
		return expr;
	}

	public void expr(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " expr=" + expr.toTreeString() + ")";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitWrite(this);
	}

}
