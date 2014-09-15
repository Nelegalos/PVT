package com.epam.project.entity.test;

import java.util.List;

public class Question {
	private String text;
	private List<Answer> answers;
	private int correctIndex;
	private int mark;
	private int id;

	public Question(String text, List<Answer> arrAnswer, int correctIndex,
			int mark) {
		this.text = text;
		this.answers = arrAnswer;
		this.correctIndex = correctIndex;
		this.mark = mark;
		this.id = 0;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public int getCorrectIndex() {
		return correctIndex;
	}

	public void setCorrectIndex(int correctIndex) {
		this.correctIndex = correctIndex;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + correctIndex;
		result = prime * result + id;
		result = prime * result + mark;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (correctIndex != other.correctIndex)
			return false;
		if (id != other.id)
			return false;
		if (mark != other.mark)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}
