package parser.tree.plog;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import interpreter.plog.Visitor;

public class WriteNode extends StmtNode {

	private List<ExprNode> expr = new LinkedList<ExprNode>();

	public WriteNode(int line) {
		super(line);
	}

	public List<ExprNode> expr() {
		return Collections.unmodifiableList(expr);
	}

	public void add(ExprNode n) {
		expr.add(n);
	}

	@Override
	public String toTreeString() {
		String str = "(" + toString() + " expr=[";
		for (ExprNode e : expr) {
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
