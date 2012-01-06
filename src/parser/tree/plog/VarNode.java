package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class VarNode extends Node {
	private String value;

	public VarNode(int line, String value) {
		super(line);
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " value='" + value + "')";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitVar(this);
	}

}
