package com.foxety0f.proton.modules.reports.exceptions;

public class DatabaseTypeExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294829304240613938L;

	
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public DatabaseTypeExistException(Object obj) {
		super("The database type " + obj + " already exist");
		this.obj = obj;
	}
}
