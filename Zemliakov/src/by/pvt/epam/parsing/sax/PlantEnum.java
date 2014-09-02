package by.pvt.epam.parsing.sax;

public enum PlantEnum {
	GARDEN("garden"), TREE("tree"), BUSH("bush"), NAME("name"), HEIGHT("height"), GROUP(
			"group"), DIAMETER("diameter"), STEM("stem");
	private String value;

	private PlantEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
