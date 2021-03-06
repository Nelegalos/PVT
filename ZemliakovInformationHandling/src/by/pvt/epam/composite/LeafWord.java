package by.pvt.epam.composite;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.pvt.epam.exception.LeafTechnicalException;

public class LeafWord implements Component {

	private String word;
	private Locale locale;

	public LeafWord(String block, Locale locale) {
		this.word = block;
		this.locale = locale;
	}

	@Override
	public void printToFile() {
		System.out.println(word);
	}

	public void setWord(String block) {
		this.word = block;
	}

	@Override
	public void add(Component c) {
		System.out.println("Leaf (Word)-> add. Doing nothing");
	}

	@Override
	public void remove(Component c) {
		System.out.println("Leaf (Word) -> remove. Doing nothing");
	}

	@Override
	public Component getChild(int index) throws LeafTechnicalException {
		throw new LeafTechnicalException(
				"Leaf (Word) doesn't contain any children");
	}

	@Override
	public String toString() {
		return word;
	}

	public void delChars() {
		if (word.length() > 2) {
			String temp = String.valueOf(word.charAt(0));
			word = word.substring(1);
			word = word.replaceAll(temp, "");
			word = temp + word;
		}
		printToFile();
	}

	@Override
	public void delWords() {
		ResourceBundle rb = ResourceBundle.getBundle("regexp", locale);
		String consonant = rb.getString("consonant");
		Pattern pattern = Pattern.compile(consonant);
		Matcher matcher = pattern.matcher(word);
		if (word.length() == 4 && matcher.matches()) {
			word = "--deleted--";
		}
		printToFile();
	}

}