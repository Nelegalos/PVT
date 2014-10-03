package by.pvt.epam.service;

import java.util.List;
import java.util.Set;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;

public class CrewService {

	private CrewDAO crewDAO;

	public CrewService() {
		crewDAO = new CrewDAOImpl();
	}

	public boolean addEmployee(String name, String surname, int position) {
		return crewDAO.addEmployee(name, surname, position);
	}

	public Employee findEmployeeById(int id) throws TechnicalException {
		return crewDAO.findEmployeeById(id);
	}

	public List<Employee> findAvailableEmployees() throws TechnicalException {
		return crewDAO.findAvailableEmployees();
	}

	public boolean addToFlight(int id) {
		return crewDAO.addToFlight(id);
	}

	public Set<Employee> findCrewByFlightId(int flightId) throws TechnicalException {
		return crewDAO.findCrewByFlightId(flightId);
	}

	public boolean releaseEmployee(int employeeId) {
		return crewDAO.releaseEmployee(employeeId);
	}

	public boolean formCrew(int flightId, List<Employee> crew) {
		return crewDAO.formCrew(flightId, crew);
	}

	public boolean modifyEmployee(int id, String name, String surname,
			int position) {
		return crewDAO.modifyEmployee(id, name, surname, position);
	}

}
