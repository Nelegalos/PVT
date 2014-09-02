package by.pvt.epam.parsing.dom;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static by.pvt.epam.logging.ParsingLogger.*;
import by.pvt.epam.entity.*;
import by.pvt.epam.factory.AbstractPlantsBuilder;

public class PlantsDOMBuilder extends AbstractPlantsBuilder {
	private Set<Plant> plants;
	private DocumentBuilder docBuilder;

	public PlantsDOMBuilder() {
		this.plants = new HashSet<Plant>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			setDocBuilder(factory.newDocumentBuilder());
		} catch (ParserConfigurationException e) {
			System.err.println("Ошибка конфигурации парсера: " + e);
		}
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	public void buildSetPlants(String fileName) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		Document document = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
			document = documentBuilder.parse(fileName);
		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
		} catch (SAXException e) {
			logger.error("SAXException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		Element root = document.getDocumentElement();
		NodeList plantsList = root.getChildNodes();
		// plants = new HashSet<Plant>();

		for (int i = 0; i < plantsList.getLength(); i++) {
			if (plantsList.item(i) instanceof Element) {
				Element element = (Element) plantsList.item(i);
				if (element.getTagName().contains("tree")) {
					Tree tree = new Tree();
					tree.setName(element.getTextContent());
					NodeList trees = plantsList.item(i).getChildNodes();
					for (int j = 0; j < trees.getLength(); j++) {
						if (trees.item(j) instanceof Element) {
							Element treeElement = (Element) trees.item(j);
							int treeItemLength = treeElement.getTagName()
									.length();
							String treeItem = treeElement.getTagName()
									.substring(4, treeItemLength);
							switch (treeItem) {
							case "name":
								tree.setName(treeElement.getTextContent());
								break;
							case "height":
								tree.setHeight(Double.parseDouble(treeElement
										.getTextContent()));
								break;
							case "group":
								tree.setGroup(Group.valueOf(treeElement
										.getTextContent()));
								break;
							case "diameter":
								tree.setDiameter(Integer.parseInt(treeElement
										.getTextContent()));
								break;
							}
						}
					}
					plants.add(tree);
				}

				if (element.getTagName().contains("bush")) {
					Bush bush = new Bush();
					bush.setName(element.getTextContent());
					NodeList bushes = plantsList.item(i).getChildNodes();
					for (int j = 0; j < bushes.getLength(); j++) {
						if (bushes.item(j) instanceof Element) {
							Element bushElement = (Element) bushes.item(j);

							int bushItemLength = bushElement.getTagName()
									.length();
							String bushItem = bushElement.getTagName()
									.substring(4, bushItemLength);
							switch (bushItem) {
							case "name":
								bush.setName(bushElement.getTextContent());
								break;
							case "height":
								bush.setHeight(Double.parseDouble(bushElement
										.getTextContent()));
								break;
							case "group":
								bush.setGroup(Group.valueOf(bushElement
										.getTextContent()));
								break;
							case "stem":
								bush.setStem(Integer.parseInt(bushElement
										.getTextContent()));
								break;
							}
						}
					}
					plants.add(bush);
				}
			}
		}
	}

	public DocumentBuilder getDocBuilder() {
		return docBuilder;
	}

	public void setDocBuilder(DocumentBuilder docBuilder) {
		this.docBuilder = docBuilder;
	}
}
