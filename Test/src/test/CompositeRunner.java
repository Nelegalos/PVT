package test;

import java.io.IOException;
import by.pvt.epam.composite.Client;
import by.pvt.epam.composite.Component;
import by.pvt.epam.parsing.TextParser;

public class CompositeRunner {

	public static void main(String[] args) throws IOException {

		String initialFile = "initial.txt";
		String initialText = TextParser.parseTextFile(initialFile);
		Component textComposite = TextParser.parseText(initialText);
		Client client = new Client(textComposite);
		// client.printAsComposite();
		// client.delFirstLast();
		client.del4LettersWord();
	}
}
