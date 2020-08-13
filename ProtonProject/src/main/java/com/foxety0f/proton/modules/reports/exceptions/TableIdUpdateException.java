package com.foxety0f.proton.modules.reports.exceptions;

public class TableIdUpdateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7481719969433898602L;

	public TableIdUpdateException() {
		super("You can not update @table_id for this table");
	}
}
