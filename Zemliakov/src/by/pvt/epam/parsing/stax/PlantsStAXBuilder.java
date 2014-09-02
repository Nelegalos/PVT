package by.pvt.epam.parsing.stax;

import static by.pvt.epam.logging.ParsingLogger.logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.pvt.epam.entity.Bush;
import by.pvt.epam.entity.Group;
import by.pvt.epam.entity.Plant;
import by.pvt.epam.entity.Tree;
import by.pvt.epam.factory.AbstractPlantsBuilder;
import by.pvt.epam.parsing.sax.PlantEnum;

public class PlantsStAXBuilder extends AbstractPlantsBuilder{
	private HashSet<Plant> plants = new HashSet<>();
	private XMLInputFactory factory;

	public PlantsStAXBuilder() {
		factory = XMLInputFactory.newInstance();
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	public void buildSetPlants(String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = factory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					switch (PlantEnum.valueOf(name.toUpperCase())) {
					case TREE:
						Tree tree = buildTree(reader);
						plants.add(tree);
						break;
					case BUSH:
						Bush bush = buildBush(reader);
						plants.add(bush);
						break;
					default:
						break;
					}
				}
			}
		} catch (XMLStreamException e) {
			logger.error("StAX parsing error! ", e);
		} catch (FileNotFoundException e) {
			logger.error("File " + fileName + " not found! ", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("Unable to close the file: " + fileName + " : ", e);
			}
		}
	}

	private Tree buildTree(XMLStreamReader reader) throws XMLStreamException {
		Tree tree = new Tree();
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (PlantEnum.valueOf(name.toUpperCase())) {
				case NAME:
					tree.setName(getXMLText(reader));
					break;
				case HEIGHT:
					tree.setHeight(Double.parseDouble(getXMLText(reader)));
					break;
				case GROUP:
					tree.setGroup(Group.valueOf(getXMLText(reader)));
					break;
				case DIAMETER:
					tree.setDiameter(Integer.parseInt(getXMLText(reader)));
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.TREE) {
					return tree;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element at tag \"tree\"");
	}

	private Bush buildBush(XMLStreamReader reader) throws XMLStreamException {
		Bush bush = new Bush();
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (PlantEnum.valueOf(name.toUpperCase())) {
				case NAME:
					bush.setName(getXMLText(reader));
					break;
				case HEIGHT:
					bush.setHeight(Double.parseDouble(getXMLText(reader)));
					break;
				case GROUP:
					bush.setGroup(Group.valueOf(getXMLText(reader)));
					break;
				case STEM:
					bush.setStem(Integer.parseInt(getXMLText(reader)));
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.BUSH) {
					return bush;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element at tag \"bush\"");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}

}
