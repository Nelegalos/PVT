package by.pvt.epam.service;

import java.util.List;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.exception.TechnicalException;

public class FlightService {

	private FlightDAO flightDAO;

	public FlightService() {
		flightDAO = new FlightDAOImpl();
	}

	public List<Flight> findAllFlights() throws TechnicalException {
		return flightDAO.findAllFlights();
	}

	public Flight findFlightById(int id) throws TechnicalException {
		return flightDAO.findFlightById(id);
	}

	public Plane findPlaneById(int id) throws TechnicalException {
		return flightDAO.findPlaneById(id);
	}

	public boolean setFlightOnAir(int id) {
		return flightDAO.setFlightOnAir(id);
	}

	public List<Flight> findUnformedFlights() throws TechnicalException {
		return flightDAO.findUnformedFlights();
	}

	public boolean setFlightCompleted(int id) {
		return flightDAO.setFlightCompleted(id);
	}

	public boolean addFlight(int flightId, String to, String from, String date,
			int plane) {
		return flightDAO.addFlight(flightId, to, from, date, plane);
	}

	public List<Flight> findStatusFlights(int status) throws TechnicalException {
		return flightDAO.findStatusFlights(status);
	}

	public List<Plane> findAllPlanes() throws TechnicalException {
		return flightDAO.findAllPlanes();
	}

	public boolean deleteFlight(int flightId) {
		return flightDAO.deleteFlight(flightId);
	}

	public List<Flight> findFlightsByStatus(int status, int page)
			throws TechnicalException {
		return flightDAO.findFlightsByStatus(status, page);
	}

}
