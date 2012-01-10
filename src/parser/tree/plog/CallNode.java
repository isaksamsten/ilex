package parser.tree.plog;

import interpreter.plog.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallNode extends TermNode {

	private List<List<ExprNode>> arguments = new ArrayList<List<ExprNode>>();
	private List<TermNode> names = new ArrayList<TermNode>();

	public CallNode(int line) {
		super(line);
	}

	public List<TermNode> names() {
		return Collections.unmodifiableList(names);
	}

	public List<List<ExprNode>> arguments() {
		return Collections.unmodifiableList(arguments);
	}

	public List<ExprNode> arguments(int i) {
		return Collections.unmodifiableList(arguments.get(i - 1));
	}

	public void add(TermNode name) {
		names.add(name);
	}

	public void add(List<ExprNode> argument) {
		arguments.add(argument);
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitCall(this);
	}

}
