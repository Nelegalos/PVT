package com.epam.project.database.dao;

import java.util.List;

import com.epam.project.entity.Register;
import com.epam.project.entity.Role;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;

public interface UserDAO {
	User getUser(String login, String password) throws TechnicalException, LogicException;
	User getUser(int userId) throws TechnicalException, LogicException;
	List<Register> getRegister() throws TechnicalException;
	List<Register> getRegister(int peopleId) throws TechnicalException;
	List<User> getUsers(Role role) throws TechnicalException;
	boolean addUser(String firstName, String lastName, String login, String password, Role role, String other);
	boolean setRegister(int testId, int peopleId, int mark);
	boolean modifyUser(String firstName, String lastName, String other, int userId);
	boolean deleteUser(int userId);
	boolean deleteRegister(int registerId);
}

