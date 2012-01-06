package parser.tree.plog;

import interpreter.plog.Visitor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StmtListNode extends StmtNode {

	private List<StmtNode> statements = new LinkedList<StmtNode>();

	public StmtListNode(int line) {
		super(line);
	}

	public List<StmtNode> statements() {
		return Collections.unmodifiableList(statements);
	}

	public void add(StmtNode stmt) {
		statements.add(stmt);
	}

	@Override
	public String toTreeString() {
		StringBuilder builder = new StringBuilder();
		for (StmtNode s : statements) {
			builder.append(s.toTreeString());
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		return "(" + toString() + " statements=[" + builder.toString() + "])";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitStmtList(this);
	}

}
