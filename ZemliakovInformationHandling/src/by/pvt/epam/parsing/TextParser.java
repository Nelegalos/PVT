package by.pvt.epam.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import by.pvt.epam.composite.Component;
import by.pvt.epam.composite.CompositeBlock;
import by.pvt.epam.composite.LeafCodeBlock;
import by.pvt.epam.composite.LeafPunctuationMark;
import by.pvt.epam.composite.LeafWord;

public class TextParser {

	private String initialFile;
	private ResourceBundle rb;
	private Logger logger;
	private Locale locale;

	public TextParser(String file, Locale loc) {
		this.initialFile = file;
		this.locale = loc;
		this.rb = ResourceBundle.getBundle("regexp", loc);
		new DOMConfigurator().doConfigure("log4j.xml",
				LogManager.getLoggerRepository());
		this.logger = Logger.getLogger(TextParser.class);
	}

	public Component parseTextFile() {
		String initialText = null;
		try {
			Scanner scanner = new Scanner(new File(initialFile));
			String delimeter = rb.getString("delimeter");
			initialText = scanner.useDelimiter(delimeter).next();
			scanner.close();
		} catch (FileNotFoundException e) {
			logger.error("No file to scan", e);
		}
		return parse(initialText);
	}

	private Component parse(String initialText) {
		Component textComposite = new CompositeBlock();
		List<String> paragraphs = splitText(initialText);
		for (int i = 0; i < paragraphs.size(); i++) {
			String codeBlock = rb.getString("codeBlock");
			Pattern pattern = Pattern.compile(codeBlock);
			String paragraph = paragraphs.get(i);
			Matcher matcher = pattern.matcher(paragraph);
			if (matcher.matches()) {
				Component leafCodeBlock = new LeafCodeBlock(paragraphs.get(i));
				textComposite.add(leafCodeBlock);
			} else {
				Component sentenceComposite = new CompositeBlock();
				List<String> wordsAndPunct = splitParagraph(paragraphs
						.get(i));
				for (int j = 0; j < wordsAndPunct.size(); j++) {
					String punct = rb.getString("punct");
					Pattern p = Pattern.compile(punct);
					Matcher m = p.matcher(wordsAndPunct.get(j));
					if (m.matches()) {
						Component leafWord = new LeafWord(
								wordsAndPunct.get(j), locale);
						sentenceComposite.add(leafWord);
					} else {
						Component leafPunctuationMark = new LeafPunctuationMark(
								wordsAndPunct.get(j));
						sentenceComposite.add(leafPunctuationMark);
					}
				}
				textComposite.add(sentenceComposite);
			}
		}
		return textComposite;
	}

	private List<String> splitText(String str) {
		String newLine = rb.getString("newLine");
		Pattern pattern = Pattern.compile(newLine);
		String[] blocksArray = pattern.split(str);
		List<String> blocks = Arrays.asList(blocksArray);
		return blocks;
	}

	private List<String> splitParagraph(String paragraph) {
		String splitter = rb.getString("splitter");
		Pattern pattern = Pattern.compile(splitter);
		String[] blocksArray = pattern.split(paragraph);
		List<String> wordsAndPunctMarks = new ArrayList<String>();
		for (String element : blocksArray) {
			String punct = rb.getString("punct");
			Pattern pat = Pattern.compile(punct);
			Matcher mat = pat.matcher(element);
			if (mat.matches()) {
				wordsAndPunctMarks.add(element);
			} else {
				String punctSplit = rb.getString("punctSplit");
				String[] elementArray = element.split(punctSplit);
				for (String part : elementArray) {
					wordsAndPunctMarks.add(part);
				}
			}
		}
		return wordsAndPunctMarks;
	}

}
