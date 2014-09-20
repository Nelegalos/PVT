package by.pvt.epam.dao;

import java.util.List;

import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;

public abstract class CrewDAO extends AbstractDAO {

	public abstract boolean addEmployee(String name, String surname,
			int position);

	public abstract Employee findEmployeeById(int id) throws TechnicalException;

	public abstract List<Employee> findAvailableEmployees() throws TechnicalException;

}
