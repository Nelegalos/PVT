package by.pvt.epam.parsing.sax;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.pvt.epam.entity.*;

public class PlantHandler extends DefaultHandler {
	private Set<Plant> plants;
	private Plant current = null;
	private PlantEnum currentEnum = null;
	private EnumSet<PlantEnum> withText;

	public PlantHandler() {
		plants = new HashSet<Plant>();
		withText = EnumSet.range(PlantEnum.NAME, PlantEnum.STEM);
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) {
		if ("tree".equals(localName)) {
			current = new Tree();
		} else if ("bush".equals(localName)) {
			current = new Bush();
		} else {
			PlantEnum temp = PlantEnum.valueOf(localName.toUpperCase());
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if ("tree".equals(localName)) {
			plants.add(current);
		} else if ("bush".equals(localName)) {
			plants.add(current);

		}
	}

	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case NAME:
				current.setName(s);
				break;
			case HEIGHT:
				current.setHeight(Double.parseDouble(s));
				break;
			case GROUP:
				current.setGroup(Group.valueOf(s));
				break;
			case DIAMETER:
				((Tree) current).setDiameter(Integer.parseInt(s));
				break;
			case STEM:
				((Bush) current).setStem(Integer.parseInt(s));
				break;			
			default:
				throw new EnumConstantNotPresentException(
						currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}

}
