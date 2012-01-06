package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class TermNode extends Node {

	private NumNode number;
	private VarNode var;

	public TermNode(int line) {
		super(line);
	}

	public VarNode var() {
		return var;
	}

	public void var(VarNode var) {
		this.var = var;
	}

	public NumNode number() {
		return number;
	}

	public void number(NumNode number) {
		this.number = number;
	}

	public void set(NumNode n) {
		this.number = n;
	}

	public void set(VarNode n) {
		this.var = n;
	}

	public boolean isNumber() {
		return number != null;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitTerm(this);
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " number="
				+ (number != null ? number.toTreeString() : "none") + " var="
				+ (var != null ? var.toTreeString() : "none") + ")";
	}
}
