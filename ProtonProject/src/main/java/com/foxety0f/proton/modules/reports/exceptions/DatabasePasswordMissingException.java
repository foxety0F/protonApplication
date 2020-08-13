package com.foxety0f.proton.modules.reports.exceptions;

public class DatabasePasswordMissingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5251919883845583278L;

	public DatabasePasswordMissingException() {
		super("Can not create or update database with @NULL password");
	}
}
