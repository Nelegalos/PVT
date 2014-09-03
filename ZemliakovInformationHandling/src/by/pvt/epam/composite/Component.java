package by.pvt.epam.composite;

import by.pvt.epam.exceptions.LeafTechnicalException;

public interface Component {

	void printToFile();

	void add(Component c);

	void remove(Component c);

	Component getChild(int index) throws LeafTechnicalException;
}