package by.pvt.epam.test;

import java.util.List;

import org.apache.log4j.Logger;

import by.pvt.epam.controller.Controller;
//import by.pvt.epam.controller.Controller;
import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOimpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;

public class Test {
//	static {
//		DOMConfigurator domConfogurator = new DOMConfigurator();
//		domConfogurator.doConfigure("log4j.xml",
//				LogManager.getLoggerRepository());
//	}
//	public static Logger logger = Logger.getLogger(Test.class);
	
	public static Logger logger = Logger.getLogger(Controller.class);

	public static void main(String[] args) {
		FlightDAO fd = new FlightDAOImpl();
		List<Flight> flights = fd.findAllFlights();
		System.out.println(flights);

		CrewDAO cd = new CrewDAOimpl();
		cd.addEmployee("Olga", "Myrzova", 4);
		Employee em = cd.getEmployeeById(3);
		System.out.println(em);

//		UserDAO ud = new UserDAOImpl();
//		//User user;
//		try {
//			user = ud.findUser("adminn", "admin");
//		} catch (TechnicalException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println(user);
//
//		logger.error("hi!!!!!");

	}

}
