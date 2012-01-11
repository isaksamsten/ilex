package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class IfNode extends StmtNode {

	private Node compare;
	private Node trueStmt;
	private Node falseStmt;

	public IfNode(int line) {
		super(line);
	}

	public Node compare() {
		return compare;
	}

	public void compare(Node compare) {
		this.compare = compare;
	}

	public Node trueStmt() {
		return trueStmt;
	}

	public void trueStmt(Node trueStmt) {
		this.trueStmt = trueStmt;
	}

	public Node falseStmt() {
		return falseStmt;
	}

	public void falseStmt(Node falseStmt) {
		this.falseStmt = falseStmt;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitIf(this);
	}
}
