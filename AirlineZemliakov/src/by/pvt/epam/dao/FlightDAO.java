package by.pvt.epam.dao;

import java.util.List;

import by.pvt.epam.entity.Flight;

public abstract class FlightDAO extends AbstractDAO {

	public abstract List<Flight> getAllFlights();
}
