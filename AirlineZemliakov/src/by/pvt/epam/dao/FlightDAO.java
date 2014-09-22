package by.pvt.epam.dao;

import java.util.List;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.exception.DAOException;

public abstract class FlightDAO extends AbstractDAO {

	public abstract List<Flight> findAllFlights();

	public abstract Plane findPlaneById(int id);

	public abstract Flight findFlightById(int id);

	public abstract boolean setFlightOnAir(int id) throws DAOException;

	public abstract boolean setFlightCompleted(int id) throws DAOException;

	public abstract List<Flight> findUnformedFlights();

	public abstract boolean addFlight(int flightId, String to, String from,
			String date, int plane) throws DAOException;

	public abstract List<Plane> findAllPlanes() throws DAOException;

}
