package parser.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Cloneable {

	private Map<NodeAttr, Object> attributes = new HashMap<NodeAttr, Object>();
	private Node parent;
	private List<Node> children = new LinkedList<Node>();
	private NodeType type;

	public Node(NodeType type) {
		this.type = type;
	}

	public void attr(NodeAttr attr, Object value) {
		attributes.put(attr, value);
	}

	public Object attr(NodeAttr attr) {
		return attributes.get(attr);
	}

	public Node parent() {
		return parent;
	}

	public void add(Node node) {
		if (node != null) {
			node.parent = this;
			children.add(node);
		}
	}

	public Node[] childrens() {
		return children.toArray(new Node[0]);
	}

	public Node clone() {
		Node clone = new Node(this.type);
		clone.attributes = new HashMap<NodeAttr, Object>(this.attributes);
		return clone;
	}

	@Override
	public String toString() {
		return "[node " + type + "]";
	}
}
