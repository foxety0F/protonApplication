package com.foxety0f.proton.modules.reports.exceptions;

public class ThreadIdMissingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7729595010691011360L;

	public ThreadIdMissingException() {
		super("Can not update thread with id is @NULL");
	}
}
