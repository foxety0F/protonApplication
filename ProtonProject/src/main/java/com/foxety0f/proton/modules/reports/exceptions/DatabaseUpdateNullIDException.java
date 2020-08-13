package com.foxety0f.proton.modules.reports.exceptions;

public class DatabaseUpdateNullIDException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294829304240613938L;

	
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public DatabaseUpdateNullIDException(Object obj) {
		super("Can not update database with null id");
		this.obj = obj;
	}
}
