package by.pvt.epam.dao;

import java.util.List;
import java.util.Set;

import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.DAOException;

public abstract class CrewDAO extends AbstractDAO {

	public abstract boolean addEmployee(String name, String surname,
			int position);

	public abstract Employee findEmployeeById(int id) throws DAOException;

	public abstract List<Employee> findAvailableEmployees()
			throws DAOException;

	public abstract boolean addToFlight(int employeeId);

	public abstract Set<Employee> findCrewByFlightId(int id)
			throws DAOException;

	public abstract boolean releaseEmployee(int employeeId);

	public abstract boolean formCrew(int flightId, List<Employee> crew);

}
