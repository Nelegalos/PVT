package com.epam.project.entity.users;

import com.epam.project.entity.Role;

public class Admin extends User {
	private String phone;

	public Admin(String firstName, String lastName, int peopleId) {
		super(firstName, lastName, peopleId);
		setRole(Role.ADMIN);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
