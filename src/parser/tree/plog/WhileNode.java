package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class WhileNode extends StmtNode {

	private Node compare;
	private Node statementList;

	public WhileNode(int line) {
		super(line);
	}

	public Node statementList() {
		return statementList;
	}

	public void statementList(Node statementList) {
		this.statementList = statementList;
	}

	public Node compare() {
		return compare;
	}

	public void compare(Node compare) {
		this.compare = compare;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " compare=" + compare.toTreeString()
				+ " statementList=" + statementList.toTreeString() + ")";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitWhile(this);
	}

}
