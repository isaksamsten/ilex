package interpreter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Table {

	private int level;
	private Map<String, TableEntry> table = new TreeMap<String, TableEntry>();

	public Table(int level) {
		this.level = level;
	}

	public int level() {
		return level;
	}

	public TableEntry enter(String name) {
		TableEntry entry = new TableEntry(name, this);
		table.put(name, entry);

		return entry;
	}

	public TableEntry lookup(String name) {
		return table.get(name);
	}

	public Collection<TableEntry> sorted() {
		Collection<TableEntry> entries = table.values();
		return Collections.unmodifiableCollection(entries);
	}

}
