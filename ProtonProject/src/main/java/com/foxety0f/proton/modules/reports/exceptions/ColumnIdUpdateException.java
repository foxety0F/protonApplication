package com.foxety0f.proton.modules.reports.exceptions;

public class ColumnIdUpdateException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3035664010221116774L;

	public ColumnIdUpdateException() {
		super("You can not update filed @id for this table");
	}
}
