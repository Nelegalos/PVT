package by.pvt.epam.main;

import java.io.IOException;

/*import static by.pvt.epam.logging.CompositeLogger.*;
import by.pvt.epam.composite.Client;
import by.pvt.epam.composite.Component;*/
import by.pvt.epam.parsing.TextParser;

public class CompositeRunner {

	public static void main(String[] args) throws IOException {

		String initialFile = "initial.txt";
		String initialText = TextParser.readFile(initialFile);
		System.out.println(initialText);

		/*logger.info("STARTING APPLICATION..\n");
		Component textComposite = TextParser.parseText(initialText);
		Client client = new Client(textComposite);
		client.execute();*/
	}
}
