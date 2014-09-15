package com.epam.project.entity;

import com.epam.project.entity.test.Test;
import com.epam.project.entity.users.Student;

public class Register {
	private int id;
	private Student student;
	private Test test;
	private int mark;

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
