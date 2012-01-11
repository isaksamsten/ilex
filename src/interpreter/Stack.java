package interpreter;

import java.util.ArrayList;

import runtime.plog.PObject;

public class Stack {
	private static Stack instance = null;

	public static Stack getInstance() {
		if (instance == null)
			instance = new Stack();

		return instance;
	}

	private ArrayList<Table> stack = new ArrayList<Table>();
		
	private int currentLevel = 0;

	private Stack() {
		stack.add(new Table(0));
	}

	public int currentNestingLevel() {
		return currentLevel;
	}

	public Table local() {
		return stack.get(currentLevel);
	}

	public Table push() {
		stack.add(new Table(++currentLevel));
		return stack.get(currentLevel);
	}

	public Table push(Table table) {
		++currentLevel;
		stack.add(table);
		return table;
	}

	public Table pop() {
		return stack.remove(currentLevel--);
	}

	public TableEntry lookupLocal(String name) {
		return stack.get(currentLevel).lookup(name);
	}

	public TableEntry enter(String name) {
		return stack.get(currentLevel).enter(name);
	}

	public TableEntry lookup(String name) {
		TableEntry found = null;
		for (int i = currentLevel; i >= 0 && found == null; i--) {
			found = stack.get(i).lookup(name);
		}
		return found;
	}

}
