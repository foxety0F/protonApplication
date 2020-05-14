package com.foxety0f.proton.common.exceptions;

public class UserNotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664423828523210034L;

	private String login;

	public String getLogin() {
		return login;
	}
	
	public UserNotFound(String login) {
		super("User " + login + " not found in Employee table!");
		this.login = login;
	}
}
