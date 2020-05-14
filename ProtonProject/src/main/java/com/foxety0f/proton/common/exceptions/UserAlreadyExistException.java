package com.foxety0f.proton.common.exceptions;

public class UserAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2698578728280982363L;
	
	private String val;

	public String getVal() {
		return val;
	}
	
	public UserAlreadyExistException(String val) {
		super("User " + val + " already exist in Employee Table");
		this.val = val;
	}

}
