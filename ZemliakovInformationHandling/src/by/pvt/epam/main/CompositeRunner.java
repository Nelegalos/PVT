package by.pvt.epam.main;

import java.io.IOException;
import java.util.Locale;
import by.pvt.epam.composite.Client;
import by.pvt.epam.composite.Component;
import by.pvt.epam.parsing.TextParser;

public class CompositeRunner {

	public static void main(String[] args) throws IOException {

		String initialFile = "initial.txt";
		Locale loc = new Locale("en", "EN");
		TextParser parser = new TextParser(initialFile, loc);
		Component textComposite = parser.parseTextFile();
		Client client = new Client(textComposite);
		// client.printAsComposite();
		// client.del4LettersWord();
		client.delFirstForRest();

	}
}
