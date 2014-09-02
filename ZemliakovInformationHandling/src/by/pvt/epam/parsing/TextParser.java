package by.pvt.epam.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import by.pvt.epam.composite.Component;
import by.pvt.epam.composite.CompositeBlock;
import by.pvt.epam.composite.LeafCodeBlock;
import by.pvt.epam.composite.LeafPunctuationMark;
import by.pvt.epam.composite.LeafWord;
import static by.pvt.epam.logging.CompositeLogger.*;

public class TextParser {

	public static String readFile(String file) {
		String str = null;
		try {
			Scanner scanner = new Scanner(new File(file));
			str = scanner.useDelimiter("\\Z").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			logger.error("No file to scan", e);
		}
		return str;
	}

	public static Component parseText(String str) {
		Component textComposite = new CompositeBlock();
		String[] sentencesArray = splitTextToSentences(str);
		for (int i = 0; i < sentencesArray.length; i++) {
			if (defineCodeBlock(sentencesArray[i])) {
				Component leafCodeBlock = new LeafCodeBlock(sentencesArray[i]);
				textComposite.add(leafCodeBlock);
			} else {
				Component sentenceComposite = new CompositeBlock();
				String[] wordsAndPunctuationMarksArray = splitSentenceToWordsAndPunctuationMarks(sentencesArray[i]);
				for (int j = 0; j < wordsAndPunctuationMarksArray.length; j++) {
					if (defineWord(wordsAndPunctuationMarksArray[j])) {
						Component leafWord = new LeafWord(
								wordsAndPunctuationMarksArray[j]);
						sentenceComposite.add(leafWord);
					} else {
						Component leafPunctuationMark = new LeafPunctuationMark(
								wordsAndPunctuationMarksArray[j]);
						sentenceComposite.add(leafPunctuationMark);
					}
				}
				textComposite.add(sentenceComposite);
			}
		}
		return textComposite;
	}

	public static String[] splitTextToSentences(String str) {
		String[] sentencesArray = null;
		return sentencesArray;
	}

	static boolean defineCodeBlock(String sentence) {
		return true;
	}

	public static String[] splitSentenceToWordsAndPunctuationMarks(String str) {
		String[] wordsArray = null;
		return wordsArray;
	}

	static boolean defineWord(String sentenceElement) {
		return true;
	}

}
