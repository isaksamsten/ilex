package parser.tree.plog;

import parser.tree.Node;

public class NumNode extends Node {

	private Number number;

	public NumNode(int line, Number number) {
		super(line);
		this.number = number;
	}

	public Number number() {
		return this.number;
	}

}
