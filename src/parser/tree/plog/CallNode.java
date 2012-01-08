package parser.tree.plog;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import interpreter.plog.Visitor;

public class CallNode extends ExprNode {

	private List<VarNode> names = new LinkedList<VarNode>();
	
	public CallNode(int line) {
		super(line);
	}
	
	public List<VarNode> names() {
		return Collections.unmodifiableList(names);
	}
	
	public void add(VarNode name) {
		names.add(name);
	}
	
	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitCall(this);
	}

}
