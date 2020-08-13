package com.foxety0f.proton.modules.reports.exceptions;

public class DatabaseIdUpdateException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2035867928770771265L;

	public DatabaseIdUpdateException() {
		super("You can not update @id_database field");
	}
}
