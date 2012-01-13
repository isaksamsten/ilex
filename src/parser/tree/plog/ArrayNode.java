package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class ArrayNode extends Node {

	private Node items;

	public ArrayNode(int line) {
		super(line);
	}

	public void items(Node n) {
		this.items = n;
	}

	public Node items() {
		return items;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitArray(this);
	}

}
