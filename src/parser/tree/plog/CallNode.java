package parser.tree.plog;

import interpreter.plog.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.tree.Node;

public class CallNode extends TermNode {
	
	private Node name;
	private Node argument;

	private List<ExprListNode> arguments = new ArrayList<ExprListNode>();
	private List<TermNode> names = new ArrayList<TermNode>();

	public CallNode(int line) {
		super(line);
	}
	
	public Node name() {
		return name;
	}
	
	public Node argument() {
		return argument;
	}
	
	public void name(Node name) {
		this.name = name;
	}
	
	public void argument(Node argument) {
		this.argument = argument;
	}

	public List<TermNode> names() {
		return Collections.unmodifiableList(names);
	}

	public List<ExprListNode> arguments() {
		return Collections.unmodifiableList(arguments);
	}

	public ExprListNode arguments(int i) {
		return arguments.get(i);
	}

	public void add(TermNode name) {
		names.add(name);
	}

	public void add(ExprListNode argument) {
		arguments.add(argument);
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitCall(this);
	}

	public TermNode names(int i) {
		return names.get(i);
	}

}
