package parser.tree;

import interpreter.plog.Visitor;

public abstract class Node implements Cloneable {

	private int line = 0;

	public Node(int line) {
		this.line = line;
	}

	public int line() {
		return this.line;
	}

	public String toTreeString() {
		return toString();
	}

	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + " at: " + line + "]";
	}

	public abstract Object visit(Visitor visitor);
}
