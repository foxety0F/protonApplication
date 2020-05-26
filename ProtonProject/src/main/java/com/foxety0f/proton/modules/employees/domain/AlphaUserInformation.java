package com.foxety0f.proton.modules.employees.domain;

public class AlphaUserInformation {

	private Long userId;
	private String login;
	private String firstName;
	private String surname;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "AlphaUserInformation [userId=" + userId + ", login=" + login + ", firstName=" + firstName + ", surname="
				+ surname + "]";
	}

}
