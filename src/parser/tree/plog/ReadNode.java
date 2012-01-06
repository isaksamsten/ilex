package parser.tree.plog;

import interpreter.plog.Visitor;

public class ReadNode extends StmtNode {

	private VarNode var;

	public ReadNode(int line) {
		super(line);
	}

	public VarNode var() {
		return var;
	}

	public void var(VarNode v) {
		var = v;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " var=" + var.toTreeString() + ")";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitRead(this);
	}

}
