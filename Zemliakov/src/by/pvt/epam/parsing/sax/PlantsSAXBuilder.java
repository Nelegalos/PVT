package by.pvt.epam.parsing.sax;

import java.io.IOException;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.pvt.epam.entity.*;
import by.pvt.epam.factory.AbstractPlantsBuilder;
import static by.pvt.epam.logging.ParsingLogger.*;

public class PlantsSAXBuilder extends AbstractPlantsBuilder {

	private Set<Plant> plants;
	private PlantHandler ph;
	private XMLReader reader;

	public PlantsSAXBuilder() {
		ph = new PlantHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(ph);
		} catch (SAXException e) {
			logger.error("SAXException", e);
		}
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	public void buildSetPlants(String fileName) {
		try {
			reader.parse(fileName);
		} catch (SAXException e) {
			logger.error("SAXException",e);
		} catch (IOException e) {
			logger.error("SAXException",e);
		}
		plants = ph.getPlants();
	}
}
