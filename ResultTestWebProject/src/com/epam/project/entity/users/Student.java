package com.epam.project.entity.users;

import com.epam.project.entity.Role;

public class Student extends User {
	private String university;

	public Student(String firstName, String lastName, int peopleId) {
		super(firstName, lastName, peopleId);
		setRole(Role.STUDENT);
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

}
