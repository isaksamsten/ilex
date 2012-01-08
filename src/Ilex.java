import interpreter.Stack;
import interpreter.TableEntry;
import interpreter.TableKey;
import interpreter.plog.Interpreter;
import interpreter.plog.Visitor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import message.MessageHandler;
import message.ParseAdapter;
import message.ParseListener;
import message.SourceListener;
import parser.BufferedSource;
import parser.Parser;
import parser.Source;
import parser.Tokenizer;
import parser.plog.PlogParser;
import parser.plog.PlogTokenizer;
import parser.tree.Tree;
import token.Token;

public class Ilex {

	private static String file = "";
	private static Map<Integer, String> source = new HashMap<Integer, String>();
	private static ParseListener errorListener = new ParseAdapter() {
		@Override
		public void error(int line, int pos, String text, String error,
				boolean fatal) {
			if (fatal) {
				MessageHandler.getInstance().parserSummary(0);
				System.exit(-1);
			}
			StringBuilder builder = new StringBuilder();
			builder.append(" File '" + file + "', line " + line + "\n");
			builder.append(" " + source.get(line) + "\n");
			for (int n = 0; n < pos + 1; n++) {
				builder.append(' ');
			}
			builder.append("^\n*** ");
			builder.append(error);
			builder.append(" At [");
			if (text != null) {
				builder.append("'" + text + "' ");
			}
			builder.append(line + ":" + pos);
			builder.append("]");

			System.out.println(builder.toString());
		}
	};

	private static ParseListener parseSummary = new ParseAdapter() {

		@Override
		public void parserSummary(long took, int errors) {
			System.out.format("\n\t*******************************\n"
					+ "\t   %d ms total parsing time  \n"
					+ "\t   %d syntax errors\n\n", took, errors);
		}
	};

	private static ParseListener parseDebug = new ParseAdapter() {

		@Override
		public void token(Token token) {
			System.out
					.format(">>> %-15s \t [line=%03d, pos=%d, text='%s', value='%s']\n",
							token.type(), token.line(), token.position(),
							token.text(), token.value());
		}
	};

	public static void main(String[] args) {
		try {
			args = new String[] { "test.ilex" };

			MessageHandler.getInstance().addParseListener(errorListener);
			MessageHandler.getInstance().addSourceListener(
					new SourceListener() {

						@Override
						public void line(int num, String line) {
							source.put(num, line);
						}
					});

			List<String> arguments = Arrays.asList(args);
			if (arguments.contains("--summary")) {
				MessageHandler.getInstance().addParseListener(parseSummary);
			}

			file = arguments.get(arguments.size() - 1);
			Source source = new BufferedSource(new File(file));
			Tokenizer tokenizer = new PlogTokenizer(source);

			Parser<Tree> parser = new PlogParser(tokenizer);
			Tree tree = parser.parse();
			if (!parser.errors()) {
				// System.out.println(tree.toString());

				Visitor interpreter = new Interpreter();
				interpreter.visit(tree.root());

			}

			System.out.println("\t************ Stack ************");
			System.out.println("\tLevel: "
					+ Stack.getInstance().currentNestingLevel());
			for (TableEntry entry : Stack.getInstance().local().sorted()) {
				System.out.println("\t   " + entry.name() + " "
						+ entry.getAttribute(TableKey.CONSTANT));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
