package com.epam.project.service;

import java.util.List;

import com.epam.project.database.dao.UserDAO;
import com.epam.project.database.dao.UserDAOImpl;
import com.epam.project.entity.Register;
import com.epam.project.entity.Role;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAOImpl();
	}

	public boolean addUser(String firstName, String lastName, String login,
			String password, Role role, String other) {
		return userDAO.addUser(firstName, lastName, login, password, role,
				other);

	}

	public User getUser(String login, String password)
			throws TechnicalException, LogicException {
		return userDAO.getUser(login, password);
	}

	public boolean setRegister(int testId, int peopleId, int mark) {
		return userDAO.setRegister(testId, peopleId, mark);

	}

	public List<Register> getRegister() throws TechnicalException {
		return userDAO.getRegister();
	}

	public List<Register> getRegister(int peopleId) throws TechnicalException {
		return userDAO.getRegister(peopleId);
	}

	public List<User> getUsers(Role role) throws TechnicalException {
		return userDAO.getUsers(role);

	}

	public User getUser(int userId) throws TechnicalException, LogicException {
		return userDAO.getUser(userId);
	}

	public boolean modifyUser(String firstName, String lastName, String other,
			int userId) {
		return userDAO.modifyUser(firstName, lastName, other, userId);
	}

	public boolean deleteUser(int userId) {
		return userDAO.deleteUser(userId);
	}

	public boolean deleteRegister(int registerId) {
		return userDAO.deleteRegister(registerId);
	}

}
