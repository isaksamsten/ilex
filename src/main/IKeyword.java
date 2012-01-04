package main;

import java.util.HashMap;
import java.util.Map;

public class IKeyword extends IObject {

	private static Map<String, IKeyword> keywords = new HashMap<String, IKeyword>();

	public static IKeyword get(String name) {
		IKeyword keyword = keywords.get(name);
		if (keyword == null) {
			keyword = new IKeyword(name);
			keywords.put(name, keyword);
		}

		return keyword;
	}

	public static final char START = ':';

	protected IKeyword(String name) {
		this.parent = new IObject(this.id());
		prop("name", name);
	}

	@Override
	public String name() {
		return prop("name");
	}

	@Override
	public void initialize(Map<String, Object> args) {
		super.initialize(args);
	}

}
