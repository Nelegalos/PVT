package by.pvt.epam.entity;

public class Plane {
	private int id;
	private int pilot;
	private int navigator;
	private int radioman;
	private int steward;

	public Plane() {
	}

	public Plane(int id, int pilot, int navigator, int radioman, int steward) {
		this.id = id;
		this.pilot = pilot;
		this.navigator = navigator;
		this.radioman = radioman;
		this.steward = steward;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPilot() {
		return pilot;
	}

	public void setPilot(int pilot) {
		this.pilot = pilot;
	}

	public int getNavigator() {
		return navigator;
	}

	public void setNavigator(int navigator) {
		this.navigator = navigator;
	}

	public int getRadioman() {
		return radioman;
	}

	public void setRadioman(int radioman) {
		this.radioman = radioman;
	}

	public int getSteward() {
		return steward;
	}

	public void setSteward(int steward) {
		this.steward = steward;
	}

	@Override
	public String toString() {
		return "Plane [id=" + id + ", pilot=" + pilot + ", navigator="
				+ navigator + ", radioman=" + radioman + ", steward=" + steward
				+ "]";
	}

}
