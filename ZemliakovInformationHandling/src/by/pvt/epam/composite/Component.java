package by.pvt.epam.composite;

import by.pvt.epam.exception.LeafTechnicalException;

public interface Component {

	void printToFile();

	void add(Component c);

	void remove(Component c);

	Component getChild(int index) throws LeafTechnicalException;

	void delChars();

	void delWords();

}
