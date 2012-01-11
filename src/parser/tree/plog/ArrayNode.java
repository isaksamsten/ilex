package parser.tree.plog;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import interpreter.plog.Visitor;
import parser.tree.Node;

public class ArrayNode extends Node {

	private List<ExprNode> elements = new LinkedList<ExprNode>();

	public ArrayNode(int line) {
		super(line);
	}

	public List<ExprNode> elements() {
		return Collections.unmodifiableList(elements);
	}

	public void add(ExprNode node) {
		elements.add(node);
	}

	@Override
	public Object visit(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
