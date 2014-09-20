package by.pvt.epam.logic;

import java.util.List;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Flight;

public class FlightLogic {
	public static List<Flight> findAllFlights() {
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> flights = flightDAO.findAllFlights();
		return flights;
	}

}