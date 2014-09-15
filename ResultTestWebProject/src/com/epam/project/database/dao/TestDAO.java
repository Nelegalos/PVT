package com.epam.project.database.dao;

import java.util.List;

import com.epam.project.entity.test.Test;
import com.epam.project.exceptions.TechnicalException;

public interface TestDAO {
	Test getTestById(int id) throws TechnicalException;
	List<Test> getTests() throws TechnicalException;
	boolean addTest(Test test);
	boolean modifyTest(Test test, int testId);
	boolean deleteTest(int testId);
}
