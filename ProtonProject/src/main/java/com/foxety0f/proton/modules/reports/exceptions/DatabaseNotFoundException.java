package com.foxety0f.proton.modules.reports.exceptions;

public class DatabaseNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294829304240613938L;

	
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public DatabaseNotFoundException(Object obj) {
		super("The database with id " + obj + " not found");
		this.obj = obj;
	}
}
