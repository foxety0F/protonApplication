package com.foxety0f.proton.modules.reports.exceptions;

public class DatabaseUserMissingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294829304240613938L;

	
	public DatabaseUserMissingException() {
		super("Can not create or update database with @NULL user name");
	}
}