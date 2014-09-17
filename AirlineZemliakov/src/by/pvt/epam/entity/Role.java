package by.pvt.epam.entity;

public enum Role {
	ADMIN("admin"), DISPATCHER("dispatcher");
	private String value;

	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
