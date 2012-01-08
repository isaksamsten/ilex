package parser.tree.plog;

import parser.tree.Node;
import interpreter.plog.Visitor;

public class StringNode extends Node{

	private String string;

	public StringNode(int line, String string) {
		super(line);
		this.string = string;
	}
	
	public String string() {
		return string;
	}
	
	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitString(this);
	}

}
