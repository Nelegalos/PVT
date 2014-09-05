package by.pvt.epam.composite;

import by.pvt.epam.exception.LeafTechnicalException;

public class LeafCodeBlock implements Component {

	private String codeBlock;

	public LeafCodeBlock(String block) {
		this.codeBlock = block;
	}

	@Override
	public void printToFile() {
		System.out.println(codeBlock);
	}

	public void setCodeBlock(String block) {
		this.codeBlock = block;
	}

	@Override
	public void add(Component c) {
		System.out.println("Leaf (CodeBlock)-> add. Doing nothing");
	}

	@Override
	public void remove(Component c) {
		System.out.println("Leaf (CodeBlock)-> remove. Doing nothing");
	}

	@Override
	public Component getChild(int index) throws LeafTechnicalException {
		throw new LeafTechnicalException("Leaf doesn't contain any children");
	}

	@Override
	public String toString() {
		return codeBlock;
	}

	public void delChars() {
		printToFile();
	}

	@Override
	public void delWords() {
		printToFile();
	}

}
