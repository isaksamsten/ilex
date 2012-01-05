package parser.tree;

public class Node implements Cloneable {

	private int line = 0;

	public Node(int line) {
		this.line = line;
	}

	public int line() {
		return this.line;
	}

	@Override
	public String toString() {
		return "[node " + getClass().getSimpleName() + " at: " + line + "]";
	}
}
