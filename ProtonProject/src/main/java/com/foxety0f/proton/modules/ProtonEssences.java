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
	 * 200 - CREATE
	 * 300 - UPDATE
	 * 400 - MARK AS INACTIVE
	 * 500 - MARK AS ACTIVE
	 * 600 - INTEGRATION
	 * */
	
	/*200 - CREATE*/
	/**
	 * 
	 * */
	REPORTING_CREATE_SOURCE(200, "REPORTING_CREATE_SOURCE"),
	
	/**
	 * 
	 * */
	REPORTING_CREATE_NEW_COLUMN_TYPE(201, "REPORTING_CREATE_NEW_COLUMN_TYPE"),
	
	/**
	 * 
	 * */
	REPORTING_CREATE_NEW_THREAD(202, "REPORTING_CREATE_NEW_THREAD"),
	
	/**
	 * 
	 * */
	REPORTING_CREATE_NEW_COLUMN_MAPPER(203, "REPORTING_CREATE_NEW_COLUMN_MAPPER"),
	
	/*300 - UPDATE*/
	/**
	 * 
	 * */
	REPORTING_UPDATE_SOURCE(300, "REPORTING_UPDATE_SOURCE"),
	
	/**
	 * 
	 * */
	REPORTING_UPDATE_COLUMN_TYPE(301, "REPORTING_UPDATE_COLUMN_TYPE"),
	
	/**
	 * 
	 * */
	REPORTING_UPDATE_THREAD(302, "UPDATE_THREAD"),
	/**
	 * 
	 * */
	REPORTING_UPDATE_COLUMN_MAPPER(303, "UPDATE_COLUMN_MAPPER"),

	REPORTING_MARK_AS_ACTIVE_SOURCE(400, "REPORTING_MARK_AS_ACTIVE_SOURCE"),
	
	REPORTING_MARK_AS_ACTIVE_COLUMN_TYPE(401, "REPORTING_MARK_AS_ACTIVE_COLUMN_TYPE"),
	REPORTING_MARK_AS_ACTIVE_THREAD(401, "REPORTING_MARK_AS_ACTIVE_THREAD"),
	
	REPORTING_MARK_AS_INACTIVE_SOURCE(500, "REPORTING_MARK_AS_INACTIVE_SOURCE"),
	REPORTING_MARK_AS_INACTIVE_COLUMN_TYPE(501, "REPORTING_MARK_AS_INACTIVE_COLUMN_TYPE"),
	REPORTING_MARK_AS_INACTIVE_THREAD(502, "REPORTING_MARK_AS_INACTIVE_THREAD"),
	
	
	LOGIN_ACTION(999, "LOGIN_ACTION");

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
