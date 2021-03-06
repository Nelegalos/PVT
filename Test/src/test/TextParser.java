package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.pvt.epam.composite.Component;
import by.pvt.epam.composite.CompositeBlock;
import by.pvt.epam.composite.LeafCodeBlock;
import by.pvt.epam.composite.LeafPunctuationMark;
import by.pvt.epam.composite.LeafWord;
import static by.pvt.epam.logging.CompositeLogger.*;

public class TextParser {
	
	private String initialFile;

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

	public static Component parseText(String initialText) {
		Component textComposite = new CompositeBlock();
		List<String> paragraphs = splitTextToParagraphs(initialText);
		for (int i = 0; i < paragraphs.size(); i++) {
			if (defineCodeBlock(paragraphs.get(i))) {
				Component leafCodeBlock = new LeafCodeBlock(paragraphs.get(i));
				textComposite.add(leafCodeBlock);
			} else {
				Component sentenceComposite = new CompositeBlock();
				List<String> wordsAndPunctMarks = splitParagraph(paragraphs
						.get(i));
				for (int j = 0; j < wordsAndPunctMarks.size(); j++) {
					if (defineLetters(wordsAndPunctMarks.get(j))) {
						Component leafWord = new LeafWord(
								wordsAndPunctMarks.get(j));
						sentenceComposite.add(leafWord);
					} else {
						Component leafPunctuationMark = new LeafPunctuationMark(
								wordsAndPunctMarks.get(j));
						sentenceComposite.add(leafPunctuationMark);
					}
				}
				textComposite.add(sentenceComposite);
			}
		}
		return textComposite;
	}

	public static List<String> splitTextToParagraphs(String str) {
		String regex = "\\r+\\n*";
		Pattern pattern = Pattern.compile(regex);
		String[] blocksArray = pattern.split(str);
		List<String> blocks = Arrays.asList(blocksArray);
		return blocks;
	}

	public static boolean defineCodeBlock(String paragraph) {
		String regexp = ".*[{}/\\t].*";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(paragraph);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static List<String> splitParagraph(String paragraph) {
		String splitter = " +";
		Pattern pattern = Pattern.compile(splitter);
		String[] blocksArray = pattern.split(paragraph);
		List<String> wordsAndPunctMarks = new ArrayList<String>();
		for (String element : blocksArray) {
			if (defineLetters(element)) {
				wordsAndPunctMarks.add(element);
			} else {				
				String[] elementArray = element.split("(?<=[\\p{Punct}-])|(?=[\\p{Punct}-])");
				for (String part : elementArray) { 
						wordsAndPunctMarks.add(part); 
					} 
			}
		}
		return wordsAndPunctMarks;
	}
	public static boolean defineLetters(String sentencePart) {
		String regexp = "[^.*\\p{Punct}+.]*";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(sentencePart);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
