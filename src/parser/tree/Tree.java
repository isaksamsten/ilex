package parser.tree;

public class Tree {
	private Node root = null;

	public void root(Node root) {
		this.root = root;
	}

	public Node root() {
		return this.root;
	}

}
