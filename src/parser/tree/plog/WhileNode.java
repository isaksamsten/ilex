package parser.tree.plog;

import interpreter.plog.Visitor;

public class WhileNode extends StmtNode {

	private CompNode compare;
	private StmtListNode statementList;

	public WhileNode(int line) {
		super(line);
	}

	public StmtListNode statementList() {
		return statementList;
	}

	public void statementList(StmtListNode statementList) {
		this.statementList = statementList;
	}

	public CompNode compare() {
		return compare;
	}

	public void compare(CompNode compare) {
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
