package parser.tree.plog;

import interpreter.plog.Visitor;

public class IfNode extends StmtNode{
	
	private CompNode compare;
	private StmtListNode trueStmt;
	private StmtListNode falseStmt;
	
	public IfNode(int line) {
		super(line);
	}

	public CompNode compare() {
		return compare;
	}

	public void compare(CompNode compare) {
		this.compare = compare;
	}

	public StmtListNode trueStmt() {
		return trueStmt;
	}

	public void trueStmt(StmtListNode trueStmt) {
		this.trueStmt = trueStmt;
	}

	public StmtListNode falseStmt() {
		return falseStmt;
	}

	public void falseStmt(StmtListNode falseStmt) {
		this.falseStmt = falseStmt;
	}
	
	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitIf(this);
	}
}
