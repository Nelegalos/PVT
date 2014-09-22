package by.pvt.epam.logic;

import java.util.List;

import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.exception.DAOException;

public class FlightLogic {
	public static List<Flight> findAllFlights() {
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> flights = flightDAO.findAllFlights();
		return flights;
	}

	public static List<Flight> findUnformedFlights() {
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> flights = flightDAO.findUnformedFlights();
		return flights;
	}

	public static boolean validateAddFlight(String idInput, String to,
			String from, String dateInput, String planeInput) {

		boolean flag = false;

		if (isEmpty(to, from, dateInput)) {
			return flag;
		}
		int flightId;
		int plane;
		try {
			flightId = Integer.valueOf(idInput);
			plane = Integer.valueOf(planeInput);
		} catch (IllegalArgumentException e) {
			return flag;
		}
		FlightDAO fd = new FlightDAOImpl();
		List<Flight> allFlights = fd.findAllFlights();
		for (Flight flight : allFlights) {
			if (flightId == flight.getId()) {
				return flag;
			}
		}

		try {
			flag = fd.addFlight(flightId, to, from, dateInput, plane);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	private static boolean isEmpty(String to, String from, String dateInput) {
		return to == "" || from == "" || dateInput == "";
	}

	public static List<Plane> findAllPlanes() throws DAOException {
		FlightDAO fd = new FlightDAOImpl();
		List<Plane> allPlanes = fd.findAllPlanes();		
		return allPlanes;
	}

}
