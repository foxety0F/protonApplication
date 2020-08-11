package com.foxety0f.proton.modules.reports.exceptions;

public class ThreadDatabaseIdMissingException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4390046362233621870L;

	public ThreadDatabaseIdMissingException() {
		super("Can not create thread if database id is @NULL");
	}

}
