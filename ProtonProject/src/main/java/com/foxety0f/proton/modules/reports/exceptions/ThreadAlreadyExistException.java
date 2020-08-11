package com.foxety0f.proton.modules.reports.exceptions;

public class ThreadAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294829304240613938L;

	
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public ThreadAlreadyExistException(Object obj) {
		super("The thread " + obj + " already exist");
		this.obj = obj;
	}
}
