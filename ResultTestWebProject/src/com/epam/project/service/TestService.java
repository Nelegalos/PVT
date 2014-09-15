package com.epam.project.service;

import java.util.List;

import com.epam.project.database.dao.TestDAO;
import com.epam.project.database.dao.TestDAOImpl;
import com.epam.project.entity.test.Test;
import com.epam.project.exceptions.TechnicalException;

public class TestService {
	private TestDAO testDAO;

	public TestService() {
		testDAO = new TestDAOImpl();
	}

	public Test getTestByID(int id) throws TechnicalException {
		return testDAO.getTestById(id);
	}

	public List<Test> getTests() throws TechnicalException {
		return testDAO.getTests();
	}

	public boolean addTest(Test test){
		return testDAO.addTest(test);
	}

	public boolean deleteTest(int testId){
		return testDAO.deleteTest(testId);
	}

	public boolean modifyTest(Test test, int testId){
		return testDAO.modifyTest(test, testId);
	}
}
