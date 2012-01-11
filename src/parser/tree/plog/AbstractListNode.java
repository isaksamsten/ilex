package parser.tree.plog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.tree.Node;

public abstract class AbstractListNode extends Node {
	private List<Node> elements = new ArrayList<Node>();

	public AbstractListNode(int line) {
		super(line);
	}

	public List<Node> elements() {
		return Collections.unmodifiableList(elements);
	}

	public void add(Node node) {
		elements.add(node);
	}

}
