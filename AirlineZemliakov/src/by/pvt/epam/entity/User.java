package by.pvt.epam.entity;

public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private Role role;
	private String login;

	public User(String name, String surname, Role role, String login) {
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", role=" + role
				+ ", login=" + login + "]";
	}

}
