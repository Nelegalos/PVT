package by.pvt.epam.composite;

import by.pvt.epam.exception.LeafTechnicalException;

public class LeafPunctuationMark implements Component {

	private String punctuationMark;

	public LeafPunctuationMark(String block) {
		this.punctuationMark = block;
	}

	@Override
	public void printToFile() {
		System.out.println(punctuationMark);
	}

	public void setPunctuationMark(String block) {
		this.punctuationMark = block;
	}

	@Override
	public void add(Component c) {
		System.out.println("Leaf (PunctuationMark)-> add. Doing nothing");
	}

	@Override
	public void remove(Component c) {
		System.out.println("Leaf (PunctuationMark)-> remove. Doing nothing");
	}

	@Override
	public Component getChild(int index) throws LeafTechnicalException {
		throw new LeafTechnicalException(
				"Leaf (PunctuationMark) doesn't contain any children");
	}

	@Override
	public String toString() {
		return punctuationMark;
	}

	public void delChars() {
		printToFile();
	}

	@Override
	public void delWords() {
		printToFile();
	}

}
