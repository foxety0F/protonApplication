package com.foxety0f.proton.modules.reports.exceptions;

public class ColumnMissingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718025523608235353L;

	private Integer id;

	public Integer getId() {
		return id;
	}
	
	public ColumnMissingException(Integer id) {
		super("Can not find column with column id is " + id);
	}
}
