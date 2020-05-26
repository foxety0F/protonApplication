package com.foxety0f.proton.modules;

public enum ProtonEssences {

	INSERT(100, "INSERT"), UPDATE(101, "UPDATE"), DELETE(102, "DELETE"), MARK_INACTIVE(103, "MARK_INACTIVE"),
	MARK_ACTIVE(104, "MARK_ACTIVE"), FILL_DICTIONARY(105, "FILL_DICTIONARY"),
	HIGH_PRIOR_DATA_ACCESS(106, "HIGH_PRIOR_DATA_ACCESS"), CREATE_STRUCT(107, "CREATE_STRUCT"),
	MARK_INACTIVE_ACTIVE(108, "MARK INACTIVE/ACTIVE"), CREATE_USER(109, "CREATE USER"), EXCEPTION(110, "EXCEPTION"),
	EXCEPTION_INSER(111, "EXCEPTION_INSERT"), EXCEPTION_UPDATE(112, "EXCEPTION_UPDATE"),
	CREATE_USER_EXCEPTION(113, "CREATE_USER_EXCEPTION"), USER_NOT_FOUND_EXCEPTION(114, "USER_NOT_FOUND_EXCEPTION"),
	ADD_ROLE(115, "ADD_ROLE"), REMOVE_ROLE(116, "REMOVE_ROLE"),
	USER_UPDATE(117, "USER_UPDATE"), INSERT_USER_ATTR(118, "INSERT_USER_ATTR");

	private Integer value;
	private String string;

	ProtonEssences(Integer value, String string) {
		this.value = value;
		this.string = string;
	}

	public Integer getValue() {
		return value;
	}

	public String getString() {
		return string;
	}

}
