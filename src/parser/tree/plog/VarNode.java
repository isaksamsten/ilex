package parser.tree.plog;

import interpreter.plog.Visitor;

public class VarNode extends TermNode {
	private String var;

	public VarNode(int line, String var) {
		super(line);
		this.var = var;
	}

	public String var() {
		return this.var;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " value='" + var + "')";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitVar(this);
	}

}
