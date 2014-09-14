package by.pvt.epam.entity;

public enum Position {
	PILOT("pilot"), NAVIGATOR("navigator"), RADIOMAN("radioman"), STEWARD(
			"steward");
	private String value;

	private Position(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
