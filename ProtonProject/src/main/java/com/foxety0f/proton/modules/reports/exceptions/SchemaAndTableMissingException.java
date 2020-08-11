package com.foxety0f.proton.modules.reports.exceptions;

public class SchemaAndTableMissingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718025523608235354L;

	private Integer id;

	public Integer getId() {
		return id;
	}
	
	public SchemaAndTableMissingException(Integer id) {
		super("Can not find shema name and table name for column id - " + id);
	}
}
