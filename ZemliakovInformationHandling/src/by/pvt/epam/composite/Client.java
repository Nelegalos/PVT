package by.pvt.epam.composite;

import java.util.List;

public class Client {

	private Component component;
	private List<String> words;

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}

	public Client(Component component) {
		this.component = component;
	}

	public void printAsComposite() {
		component.printToFile();
	}

	public void delFirstForRest() {
		component.delChars();
	}

	public void del4LettersWord() {
		component.delWords();
	}

}
