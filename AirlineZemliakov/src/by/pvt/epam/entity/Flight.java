package by.pvt.epam.entity;

import java.sql.Date;



public class Flight {
	private int id;
	private Date date;
	private String from;
	private String to;
	private Plane plane;

	public Flight() {
	}

	public Flight(int id, Date date, String from, String to, Plane plane) {
		this.id = id;
		this.date = date;
		this.from = from;
		this.to = to;
		this.plane = plane;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", date=" + date + ", from=" + from
				+ ", to=" + to + ", plane=" + plane + "]";
	}

}
