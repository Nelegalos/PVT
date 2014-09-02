package by.pvt.epam.composite;

import static by.pvt.epam.logging.CompositeLogger.*;
import by.pvt.epam.exceptions.LeafTechnicalException;

public class LeafWord implements Component {

	private String word;

	public LeafWord(String block) {
		this.word = block;
	}

	@Override
	public void printToFile() {
		logger.info(word);
	}

	public void setWord(String block) {
		this.word = block;
	}

	@Override
	public void add(Component c) {
		logger.warn("Leaf -> add. Doing nothing");
	}

	@Override
	public void remove(Component c) {
		logger.warn("Leaf -> remove. Doing nothing");
	}

	@Override
	public Component getChild(int index) throws LeafTechnicalException {
		throw new LeafTechnicalException("Leaf doesn't contain any children");
	}

	@Override
	public String toString() {
		return word;
	}

}