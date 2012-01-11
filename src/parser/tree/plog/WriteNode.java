package parser.tree.plog;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class WriteNode extends AbstractListNode {

	private Node expr;
	private Node restExpr;
	
	public WriteNode(int line) {
		super(line);
	}
	
	public Node expr() {
		return expr;
	}
	
	public void expr(Node n) {
		this.expr = n;
	}
	
	public Node restExpr() {
		return restExpr;
	}
	
	public void restExpr(Node n) {
		this.restExpr = n;
	}

	@Override
	public String toTreeString() {
		String str = "(" + toString() + " expr=[";
		for (Node e : elements()) {
			str += e.toTreeString() + ", ";
		}
		str += "])";
		return str;
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitWrite(this);
	}

}
