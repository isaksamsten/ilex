package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class NumNode extends Node {

	private Number number;

	public NumNode(int line, Number number) {
		super(line);
		this.number = number;
	}

	public Number number() {
		return this.number;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " value='" + number + "')";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitNum(this);
	}

}
