package by.pvt.epam.test;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOimpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;

public class Test {
	static {
		DOMConfigurator domConfogurator = new DOMConfigurator();
		domConfogurator.doConfigure("log4j.xml",
				LogManager.getLoggerRepository());
	}
	public static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		FlightDAO fd = new FlightDAOImpl();
		List<Flight> flights = fd.getAllFlights();
		System.out.println(flights);

		CrewDAO cd = new CrewDAOimpl();
		//cd.addEmployee("Olga", "Myrzova", 4);
		Employee em =cd.getEmployeeById(3);
		System.out.println(em);

	}

}