package com.epam.project.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.epam.project.controller.TestController;
import com.epam.project.database.connection.PoolSingleton;
import com.epam.project.database.dao.util.StatementCloseUtil;
import com.epam.project.entity.test.Answer;
import com.epam.project.entity.test.Question;
import com.epam.project.entity.test.Test;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.service.TestService;

public class TestDAOImpl implements TestDAO {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String SQL_QUERY_UPDATE_TEST = "UPDATE test SET name = ?, subject_area = ?, max_mark =? WHERE id = ?";
	private static final String SQL_QUERY_UPDATE_QUESTION = "UPDATE question SET text = ?, mark =? WHERE id = ?";
	private static final String SQL_QUERY_UPDATE_ANSWER = "UPDATE answer SET text = ?, correct =? WHERE id = ?";
	private static final String SQL_QUERY_GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
	private static final String SQL_QUERY_GET_TEST_ID = "SELECT id FROM test";
	private static final String SQL_QUERY_GET_TEST_BY_ID = "SELECT name, subject_area, max_mark FROM test WHERE id = ?";
	private static final String SQL_QUERY_GET_QUESTION = "SELECT id, text, mark FROM question WHERE test_id = ?";
	private static final String SQL_QUERY_GET_ANSWERS = "SELECT text, correct, id FROM answer WHERE question_id = ?";
	private static final String SQL_QUERY_GET_REGISTER_BY_TEST = "SELECT id FROM register WHERE test_id = ?";
	private static final String SQL_QUERY_ADD_TEST = "INSERT INTO test (name,subject_area, max_mark) VALUES (?,?,?)";
	private static final String SQL_QUERY_ADD_QUESTION = "INSERT INTO question (text, mark, test_id) VALUES (?,?,?)";
	private static final String SQL_QUERY_ADD_ANSWER = "INSERT INTO answer (text, correct, question_id) VALUES (?,?,?)";
	private static final String SQL_QUERY_DELETE_TEST = "DELETE FROM test WHERE id = ?";
	private static final String SQL_QUERY_DELETE_QUESTION = "SET FOREIGN_KEY_CHECKS=0; DELETE flight.*, crew.* FROM flight INNER JOIN crew WHERE flight.id=crew.flight_id AND flight.id=? AND flight.`status`=2; SET FOREIGN_KEY_CHECKS=1;";
	private static final String SQL_QUERY_DELETE_ANSWER = "DELETE FROM answer WHERE id = ?";

	@Override
	public Test getTestById(int id) throws TechnicalException {
		Connection connection = null;
		PreparedStatement statementTest = null;
		PreparedStatement statementQuestion = null;
		PreparedStatement statementAnswers = null;
		Test test = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementTest = connection
					.prepareStatement(SQL_QUERY_GET_TEST_BY_ID);
			statementTest.setInt(1, id);
			ResultSet rs = statementTest.executeQuery();
			rs.next();
			Set<Question> setQuest = new HashSet<>();
			String name = rs.getString(1);
			String subjectArea = rs.getString(2);
			int maxMark = rs.getInt(3);
			statementQuestion = connection
					.prepareStatement(SQL_QUERY_GET_QUESTION);
			statementQuestion.setInt(1, id);
			rs = statementQuestion.executeQuery();

			while (rs.next()) {
				int questionId = rs.getInt(1);
				String questionText = rs.getString(2);
				int questionMark = rs.getInt(3);
				List<Answer> arrAnswer = new ArrayList<>();
				int correctIndex = -1;
				statementAnswers = connection
						.prepareStatement(SQL_QUERY_GET_ANSWERS);
				statementAnswers.setInt(1, questionId);
				ResultSet rsAnswers = statementAnswers.executeQuery();
				int i = 0;
				while (rsAnswers.next()) {
					Answer answer = new Answer(rsAnswers.getString(1),
							Integer.valueOf(rsAnswers.getString(3)));
					arrAnswer.add(answer);
					if (1 == rsAnswers.getInt(2)) {
						correctIndex = i;
					}
					i++;
				}
				Question question = new Question(questionText, arrAnswer,
						correctIndex, questionMark);
				question.setId(questionId);
				setQuest.add(question);
			}

			test = new Test(setQuest, name, subjectArea, maxMark);
			test.setId(id);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			StatementCloseUtil.close(statementTest);
			StatementCloseUtil.close(statementQuestion);
			StatementCloseUtil.close(statementAnswers);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return test;
	}

	@Override
	public List<Test> getTests() throws TechnicalException {
		Connection connection = null;
		Statement statementTestID = null;
		List<Test> tests = new ArrayList<>();
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementTestID = connection.createStatement();
			ResultSet rsGetId = statementTestID
					.executeQuery(SQL_QUERY_GET_TEST_ID);
			while (rsGetId.next()) {
				Test test = getTestById(rsGetId.getInt(1));
				tests.add(test);
			}
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			StatementCloseUtil.close(statementTestID);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return tests;
	}

	@Override
	public boolean addTest(Test test) {
		Connection connection = null;
		PreparedStatement statementTest = null;
		PreparedStatement statementQuestion = null;
		PreparedStatement statementAnswer = null;
		Statement statementTestID = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementTest = connection.prepareStatement(SQL_QUERY_ADD_TEST);
			statementTest.setString(1, test.getName());
			statementTest.setString(2, test.getSubjectArea());
			statementTest.setInt(3, test.getMaxMark());
			statementTest.executeUpdate();
			statementTestID = connection.createStatement();
			ResultSet rsGetId = statementTestID
					.executeQuery(SQL_QUERY_GET_LAST_INSERT_ID);
			rsGetId.next();
			int testId = rsGetId.getInt(1);
			statementQuestion = connection
					.prepareStatement(SQL_QUERY_ADD_QUESTION);
			Set<Question> questions = test.getQuestions();
			for (Question question : questions) {
				statementQuestion.setString(1, question.getText());
				statementQuestion.setInt(2, question.getMark());
				statementQuestion.setInt(3, testId);
				statementQuestion.executeUpdate();

				rsGetId = statementTestID
						.executeQuery(SQL_QUERY_GET_LAST_INSERT_ID);
				rsGetId.next();
				int questionId = rsGetId.getInt(1);

				statementAnswer = connection
						.prepareStatement(SQL_QUERY_ADD_ANSWER);
				List<Answer> answers = question.getAnswers();
				int correctIndex = question.getCorrectIndex();
				for (int i = 0; i < answers.size(); i++) {
					statementAnswer.setString(1, answers.get(i).getAnswer());
					if (i == correctIndex) {
						statementAnswer.setInt(2, 1);
					} else {
						statementAnswer.setInt(2, 0);
					}
					statementAnswer.setInt(3, questionId);
					statementAnswer.executeUpdate();
				}
			}

		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} finally {
			StatementCloseUtil.close(statementTestID);
			StatementCloseUtil.close(statementTest);
			StatementCloseUtil.close(statementQuestion);
			StatementCloseUtil.close(statementAnswer);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean modifyTest(Test test, int testId) {
		Connection connection = null;
		PreparedStatement statementTest = null;
		PreparedStatement statementQuestion = null;
		PreparedStatement statementAnswer = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementTest = connection.prepareStatement(SQL_QUERY_UPDATE_TEST);
			statementTest.setString(1, test.getName());
			statementTest.setString(2, test.getSubjectArea());
			statementTest.setInt(3, test.getMaxMark());
			statementTest.setInt(4, test.getId());
			statementTest.executeUpdate();

			statementQuestion = connection
					.prepareStatement(SQL_QUERY_UPDATE_QUESTION);
			Set<Question> questions = test.getQuestions();
			for (Question question : questions) {
				statementQuestion.setString(1, question.getText());
				statementQuestion.setInt(2, question.getMark());
				statementQuestion.setInt(3, question.getId());
				statementQuestion.executeUpdate();

				statementAnswer = connection
						.prepareStatement(SQL_QUERY_UPDATE_ANSWER);
				List<Answer> answers = question.getAnswers();
				int correctIndex = question.getCorrectIndex();
				for (int i = 0; i < answers.size(); i++) {
					statementAnswer.setString(1, answers.get(i).getAnswer());
					if (i == correctIndex) {
						statementAnswer.setInt(2, 1);
					} else {
						statementAnswer.setInt(2, 0);
					}
					statementAnswer.setInt(3, answers.get(i).getId());
					statementAnswer.executeUpdate();
				}
			}

		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} finally {
			StatementCloseUtil.close(statementTest);
			StatementCloseUtil.close(statementQuestion);
			StatementCloseUtil.close(statementAnswer);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean deleteTest(int testId) {
		Connection connection = null;
		PreparedStatement statementRegister = null;
		PreparedStatement statementTest = null;
		PreparedStatement statementQuestion = null;
		PreparedStatement statementAnswer = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementRegister = connection
					.prepareStatement(SQL_QUERY_GET_REGISTER_BY_TEST);
			statementRegister.setInt(1, testId);
			ResultSet rsRegisterId = statementRegister.executeQuery();
			if (!rsRegisterId.next()) {
				TestService testService = new TestService();
				Test test = testService.getTestByID(testId);
				Set<Question> questions = test.getQuestions();
				for (Question question : questions) {
					List<Answer> answers = question.getAnswers();
					for (Answer answer : answers) {
						statementAnswer = connection
								.prepareStatement(SQL_QUERY_DELETE_ANSWER);
						statementAnswer.setInt(1, answer.getId());
						statementAnswer.executeUpdate();
					}
				}
				statementQuestion = connection
						.prepareStatement(SQL_QUERY_DELETE_QUESTION);
				statementQuestion.setInt(1, testId);
				statementQuestion.executeUpdate();

				statementTest = connection
						.prepareStatement(SQL_QUERY_DELETE_TEST);
				statementTest.setInt(1, testId);
				statementTest.executeUpdate();
			} else {
				throw new TechnicalException(
						"Can't be deleted of the test from the active registers");
			}

		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} catch (TechnicalException e) {
			logger.error(e);
			return false;
		} finally {
			StatementCloseUtil.close(statementTest);
			StatementCloseUtil.close(statementRegister);
			StatementCloseUtil.close(statementQuestion);
			StatementCloseUtil.close(statementAnswer);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

}
