package by.pvt.epam.command;

import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;

public class Test {

	public static void main(String[] args) {

		FlightDAO fd = new FlightDAOImpl();
		boolean flag = false;
		flag = fd.deleteFlight(224);
		System.out.println(flag);

	}

}
