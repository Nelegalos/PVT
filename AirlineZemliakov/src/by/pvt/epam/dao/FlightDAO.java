package by.pvt.epam.dao;

import java.util.List;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.exception.TechnicalException;

public abstract class FlightDAO extends AbstractDAO {

	public abstract List<Flight> findAllFlights() throws TechnicalException;

	public abstract Plane findPlaneById(int id) throws TechnicalException;

	public abstract Flight findFlightById(int id) throws TechnicalException;

	public abstract boolean setFlightOnAir(int id);

	public abstract boolean setFlightCompleted(int id);

	public abstract List<Flight> findUnformedFlights()
			throws TechnicalException;

	public abstract List<Flight> findStatusFlights(int status)
			throws TechnicalException;

	public abstract List<Flight> findFlightsByStatus(int status, int page)
			throws TechnicalException;

	public abstract boolean addFlight(int flightId, String to, String from,
			String date, int plane);

	public abstract List<Plane> findAllPlanes() throws TechnicalException;

	public abstract boolean deleteFlight(int flightId);
}
