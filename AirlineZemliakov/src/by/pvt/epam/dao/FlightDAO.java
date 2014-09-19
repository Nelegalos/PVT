package by.pvt.epam.dao;

import java.util.List;

import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;

public abstract class FlightDAO extends AbstractDAO {

	public abstract List<Flight> findAllFlights();

	public abstract Plane findPlaneById(int id);

	public abstract Flight findFlightById(int id);
}
