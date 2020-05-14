package com.foxety0f.proton.modules.admin.domain;

public class AdminTableCreation {

	private String columnName;
	private String dataType;
	private Integer columnSize;
	
	public AdminTableCreation(String columnName, String dataType, Integer columnSize) {
		this.columnName = columnName;
		this.dataType = dataType;
		this.columnSize = columnSize;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}

	@Override
	public String toString() {
		return "AdminTableCreation [columnName=" + columnName + ", dataType=" + dataType + ", columnSize=" + columnSize
				+ "]";
	}

}
