package by.pvt.epam.main;

//import java.io.IOException;

/*import static by.pvt.epam.logging.CompositeLogger.*;
 import by.pvt.epam.composite.Client;
 import by.pvt.epam.composite.Component;*/
import by.pvt.epam.parsing.TextParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		String initialFile = "initial.txt";
		//String initialText = TextParser.readFile(initialFile);

		//System.out.println(initialText);

		/*List<String> sentencesList = TextParser
				.splitTextToParagraphs(initialText);
		for (String sentence: sentencesList) {
			System.out.println(sentence);
			System.out.println("----------------------------------------------");
		}*/
		
		String s ="For example, the Bicycle. brakes: to in motion.";
		System.out.println(s);
		List<String> wordsAndPunctMarks = TextParser.splitSentence(s);
		for (String element: wordsAndPunctMarks) {
			System.out.println(element);
			System.out.println("----------------------------------------------");
		}
		

	}

}