package parser.tree.plog;

import interpreter.plog.Visitor;

public class ExprListNode extends AbstractListNode {

	public ExprListNode(int line) {
		super(line);
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitExprList(this);
	}

}
