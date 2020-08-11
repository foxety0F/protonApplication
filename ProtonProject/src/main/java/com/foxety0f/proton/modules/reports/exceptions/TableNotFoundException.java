package com.foxety0f.proton.modules.reports.exceptions;

public class TableNotFoundException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3715400740583710700L;

	public TableNotFoundException(Integer id) {
		super("Table with @id " + id + " not found");
	}
}
