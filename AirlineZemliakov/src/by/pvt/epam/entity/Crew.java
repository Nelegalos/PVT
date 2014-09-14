package by.pvt.epam.entity;

import java.util.ArrayList;
import java.util.List;

public class Crew {

	private int flightId;
	private List<Employee> crew;

	public Crew() {
		this.crew = new ArrayList<Employee>();
	}

	public Crew(int flightId, List<Employee> crew) {
		this.flightId = flightId;
		this.crew = crew;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public List<Employee> getCrew() {
		return crew;
	}

	public void setCrew(List<Employee> crew) {
		this.crew = crew;
	}

	@Override
	public String toString() {
		return "Crew [flightId=" + flightId + ", crew=" + crew + "]";
	}

}
