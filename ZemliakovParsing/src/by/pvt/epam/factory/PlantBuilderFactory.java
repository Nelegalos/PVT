package by.pvt.epam.factory;

import by.pvt.epam.parsing.dom.PlantsDOMBuilder;
import by.pvt.epam.parsing.sax.PlantsSAXBuilder;
import by.pvt.epam.parsing.stax.PlantsStAXBuilder;

public class PlantBuilderFactory {
	private enum TypeParser {
		SAX, STAX, DOM
	}

	public AbstractPlantsBuilder createPlantBuilder(String typeParser) {
		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
		switch (type) {
		case DOM:
			return new PlantsDOMBuilder();
		case STAX:
			return new PlantsStAXBuilder();
		case SAX:
			return new PlantsSAXBuilder();
		default:
			throw new EnumConstantNotPresentException(type.getDeclaringClass(),
					type.name());
		}
	}
}
