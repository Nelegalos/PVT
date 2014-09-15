package com.epam.project.entity.users;

import com.epam.project.entity.Role;

public class Tutor extends User {
	private int experience;

	public Tutor(String firstName, String lastName, int peopleId) {
		super(firstName, lastName, peopleId);
		setRole(Role.TUTOR);
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
}
