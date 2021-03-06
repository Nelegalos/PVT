package by.pvt.epam.dao;

import java.util.List;
import java.util.Set;

import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;

public abstract class CrewDAO extends AbstractDAO {

	public abstract boolean addEmployee(String name, String surname,
			int position);

	public abstract Employee findEmployeeById(int id) throws TechnicalException;

	public abstract List<Employee> findAvailableEmployees() throws TechnicalException;

	public abstract boolean addToFlight(int id);

	public abstract Set<Employee> findCrewByFlightId(int flightId)
			throws TechnicalException;

	public abstract boolean releaseEmployee(int employeeId);

	public abstract boolean formCrew(int flightId, List<Employee> crew);

	public abstract boolean modifyEmployee(int id, String name, String surname,
			int position);

}
