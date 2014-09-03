package by.pvt.epam.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
		List<String> blocks = splitTextToParagraphs(str);
		for (int i = 0; i < blocks.size(); i++) {
			if (defineCodeBlock(blocks.get(i))) {
				Component leafCodeBlock = new LeafCodeBlock(blocks.get(i));
				textComposite.add(leafCodeBlock);
			} else {
				Component sentenceComposite = new CompositeBlock();
				List<String> wordsAndPunctMarks = splitSentence(blocks.get(i));
				for (int j = 0; j < wordsAndPunctMarks.size(); j++) {
					if (defineWord(wordsAndPunctMarks.get(j))) {
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
		String regex= "\\r+\\n*";
		Pattern pattern = Pattern.compile(regex);
		String[ ] blocksArray = pattern.split(str);
		List<String> blocks = Arrays.asList(blocksArray);
		return blocks;		
	}

	static boolean defineCodeBlock(String sentence) {
		return true;
	}

	public static List<String> splitSentence(String str) {				
		String splitter= " +";
		Pattern pattern = Pattern.compile(splitter);
		String[] blocksArray = pattern.split(str);		
		List<String> wordsAndPunctMarks = new ArrayList<String>();		
		for (String element:blocksArray) {			
				if(element.contains(",")||element.contains(".")||element.contains(":")||element.contains(";")) {
					String word= element.substring(0, (element.length()-1));
					wordsAndPunctMarks.add(word);
					String punctuation = String.valueOf(element.charAt((element.length()-1)));
					wordsAndPunctMarks.add(punctuation);
				} else {
					wordsAndPunctMarks.add(element);
				}	
			}		
		return wordsAndPunctMarks;		
	}
		
	

	static boolean defineWord(String sentenceElement) {
		return true;
	}

}
