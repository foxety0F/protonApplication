package com.foxety0f.proton.modules;

/**
 * Core actions in Proton Application. Using for logging any terms modify/add new information into database.
 * <br> Code like 1** using for AdminModule
 * <br> Code like 2** using for ReportsModule
 * <br> Code like 3** using for ConstructorModule
 * */
public enum ProtonEssences {

	/**
	 * Non-specify insert into database
	 * */
	INSERT(100, "INSERT"), 
	/**
	 * Non-specify modify data into database
	 * */
	UPDATE(101, "UPDATE"),
	/**
	 * Non-specify delete from database
	 * */
	DELETE(102, "DELETE"), 
	/**
	 * Using for specify 
	 * */
	MARK_INACTIVE(103, "MARK_INACTIVE"),
	MARK_ACTIVE(104, "MARK_ACTIVE"), 
	FILL_DICTIONARY(105, "FILL_DICTIONARY"),
	HIGH_PRIOR_DATA_ACCESS(106, "HIGH_PRIOR_DATA_ACCESS"), 
	CREATE_STRUCT(107, "CREATE_STRUCT"),
	MARK_INACTIVE_ACTIVE(108, "MARK INACTIVE/ACTIVE"), 
	CREATE_USER(109, "CREATE USER"), 
	EXCEPTION(110, "EXCEPTION"),
	EXCEPTION_INSER(111, "EXCEPTION_INSERT"), 
	EXCEPTION_UPDATE(112, "EXCEPTION_UPDATE"),
	CREATE_USER_EXCEPTION(113, "CREATE_USER_EXCEPTION"), 
	USER_NOT_FOUND_EXCEPTION(114, "USER_NOT_FOUND_EXCEPTION"),
	ADD_ROLE(115, "ADD_ROLE"), 
	REMOVE_ROLE(116, "REMOVE_ROLE"),
	USER_UPDATE(117, "USER_UPDATE"), 
	INSERT_USER_ATTR(118, "INSERT_USER_ATTR"),
	/**
	 * Using for log create new Database Type
	 * */
	META_CREATE_NEW_DATABASE_TYPE(200, "META_CREATE_NEW_DATABASE_TYPE"),
	/**
	 * Using for log create new database connection
	 * */
	META_CREATE_NEW_DATABASE_CONNECTION(201, "META_CREATE_NEW_DATABASE_CONNECTION"),
	/**
	 * Using for log create new thread
	 * */
	META_CREATE_NEW_THREAD(202, "META_CREATE_NEW_THREAD"),
	/**
	 * Using for log update exist database connection
	 * */
	META_UPDATE_DATABASE_CONNECTION(202, "META_UPDATE_DATABASE_CONNECTION"),
	/**
	 * Using for log update exist thread
	 * */
	META_UPDATE_THREAD(202, "META_UPDATE_THREAD"),
	/**
	 * Using for log fill tables information from source to database-target
	 * */
	META_FILL_TABLES(203, "META_FILL_TABLES"),
	/**
	 * Using for log fill columns information from source to database-target based on tables
	 * */
	META_FILL_COLUMNS(203, "META_FILL_COLUMNS"),
	/**
	 * Using for log modify column-information
	 * */
	META_UPDATE_COLUMN(204, "META_UPDATE_COLUMN"),
	/**
	 * Using for log modify table-information
	 * */
	META_UPDATE_TABLE(204, "META_UPDATE_TABLE");

	private Integer value;
	private String string;

	ProtonEssences(Integer value, String string) {
		this.value = value;
		this.string = string;
	}

	/**
	 * Using for get Essence-id
	 * */
	public Integer getValue() {
		return value;
	}

	/**
	 * Using for get Essence-name
	 * */
	public String getString() {
		return string;
	}

}
