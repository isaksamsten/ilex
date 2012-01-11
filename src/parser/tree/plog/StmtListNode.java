package parser.tree.plog;

import interpreter.plog.Visitor;

import parser.tree.Node;

public class StmtListNode extends AbstractListNode {

	public StmtListNode(int line) {
		super(line);
	}

	@Override
	public String toTreeString() {
		StringBuilder builder = new StringBuilder();
		for (Node s : elements()) {
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
