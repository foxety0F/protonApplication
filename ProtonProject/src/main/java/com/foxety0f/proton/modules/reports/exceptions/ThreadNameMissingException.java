package com.foxety0f.proton.modules.reports.exceptions;

public class ThreadNameMissingException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7336592078083772753L;

	public ThreadNameMissingException() {
		super("Can not create thread with name is @NULL");
	}
}
