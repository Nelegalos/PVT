package by.pvt.epam.composite;

import java.util.ArrayList;

public class CompositeBlock implements Component {

	private ArrayList<Component> blocks;

	public CompositeBlock() {
		blocks = new ArrayList<Component>();
	}

	public void add(Component block) {
		blocks.add(block);
	}

	public void remove(Component block) {
		blocks.remove(block);
	}

	@Override
	public void printToFile() {
		for (Component block : blocks) {
			if (block instanceof CompositeBlock) {
				block.printToFile();
			} else {
				System.out.println(block);
			}
		}
	}

	@Override
	public Component getChild(int index) {
		return blocks.get(index);
	}

	@Override
	public String toString() {
		return blocks.toString();
	}

	@Override
	public void delChars() {
		for (Component block : blocks) {
			block.delChars();
		}
	}

	@Override
	public void delWords() {
		for (Component block : blocks) {
			block.delWords();
		}
	}

}
