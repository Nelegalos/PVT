package by.pvt.epam.dao;

import java.util.List;

import by.pvt.epam.entity.Employee;

public abstract class CrewDAO extends AbstractDAO {

	public abstract boolean addEmployee(String name, String surname,
			int position);

	public abstract Employee findEmployeeById(int id);

	public abstract List<Employee> findAvailableEmployees();

}
