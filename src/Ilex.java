import interpreter.Stack;
import interpreter.TableEntry;

import java.io.File;
import java.io.IOException;

import message.MessageHandler;
import message.ParseListener;
import message.SourceListener;

import parser.BufferedSource;
import parser.IlexParser;
import parser.IlexTokenizer;
import parser.Parser;
import parser.Source;
import parser.Tokenizer;
import token.Token;

public class Ilex {
	public static void main(String[] args) {
		try {
			MessageHandler.getInstance().addParseListener(new ParseListener() {

				@Override
				public void token(Token token) {
					System.out
							.format(">>> %-15s \t [line=%03d, pos=%d, text='%s', value='%s']\n",
									token.type(), token.line(),
									token.position(), token.text(),
									token.value());
				}

				@Override
				public void summary(long took, int errors) {
					System.out.format("\n\t*******************************\n"
							+ "\t   %d ms total parsing time  \n"
							+ "\t   %d syntax errors\n\n", took, errors);
				}

				@Override
				public void error(int line, int pos, String text, String error,
						boolean fatal) {
					if (fatal) {
						MessageHandler.getInstance().summary(0);
					}
					StringBuilder builder = new StringBuilder();
					for (int n = 0; n < pos + 4; n++) {
						builder.append(' ');
					}
					builder.append("^\n*** ");
					builder.append(error);
					if (text != null) {
						builder.append(" at [" + text + "]");
					}

					System.out.println(builder.toString());
				}
			});

			MessageHandler.getInstance().addSourceListener(
					new SourceListener() {

						@Override
						public void line(int num, String line) {
							System.out.format("%03d %s\n", num, line);
						}
					});

			Source source = new BufferedSource(new File("test.ilex"));
			Tokenizer tokenizer = new IlexTokenizer(source);
			Parser parser = new IlexParser(tokenizer);

			parser.parse();

			System.out.println("\t************ Stack ************");
			System.out.println("\tLevel: "
					+ Stack.getInstance().currentNestingLevel());
			for (TableEntry entry : Stack.getInstance().local().sorted()) {
				System.out
						.println("\t   " + entry.name() + " " + entry.lines());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
