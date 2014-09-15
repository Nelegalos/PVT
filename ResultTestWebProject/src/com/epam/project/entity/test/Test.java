package com.epam.project.entity.test;

import java.util.Set;

public class Test {
	private Set<Question> questions;
	private String name;
	private String subjectArea;
	private int id;
	private int maxMark;

	public Test(Set<Question> setQuest, String name, String subjectArea,
			int maxMark) {
		this.questions = setQuest;
		this.name = name;
		this.subjectArea = subjectArea;
		this.id = 0;
		this.maxMark = maxMark;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> test) {
		this.questions = test;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectArea() {
		return subjectArea;
	}

	public void setSubjectArea(String subjectArea) {
		this.subjectArea = subjectArea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(int maxMark) {
		this.maxMark = maxMark;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((subjectArea == null) ? 0 : subjectArea.hashCode());
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subjectArea == null) {
			if (other.subjectArea != null)
				return false;
		} else if (!subjectArea.equals(other.subjectArea))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}
}
