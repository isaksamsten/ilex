package parser.tree.plog;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import interpreter.plog.Visitor;

public class CallNode extends TermNode {

	private List<TermNode> names = new LinkedList<TermNode>();
	private List<ExprNode> arguments = new LinkedList<ExprNode>();
	
	public CallNode(int line) {
		super(line);
	}
	
	public List<TermNode> names() {
		return Collections.unmodifiableList(names);
	}
	
	public List<ExprNode> arguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public void add(TermNode name) {
		names.add(name);
	}
	
	public void add(ExprNode argument) {
		arguments.add(argument);
	}
	
	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitCall(this);
	}

}
