package com.foxety0f.proton.modules.reports.exceptions;

public class DatabaseConnectionAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3186149472529406642L;
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public DatabaseConnectionAlreadyExistException(Object obj) {
		super("Database with url-connection " + obj + " already exist");
		this.obj = obj;
	}
}
