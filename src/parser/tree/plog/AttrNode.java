package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class AttrNode extends AbstractListNode {

	private Node call;
	private Node attr;

	public AttrNode(int line) {
		super(line);
	}

	public void call(Node n) {
		this.call = n;
	}

	public void attr(Node n) {
		this.attr = n;
	}

	public Node call() {
		return call;
	}

	public Node attr() {
		return attr;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitAttr(this);
	}

}
