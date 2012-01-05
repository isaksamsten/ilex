package parser.tree.plog;

import parser.tree.Node;

public class VarNode extends Node {
	private String value;

	public VarNode(int line, String value) {
		super(line);
	}

	public String value() {
		return this.value;
	}

}
