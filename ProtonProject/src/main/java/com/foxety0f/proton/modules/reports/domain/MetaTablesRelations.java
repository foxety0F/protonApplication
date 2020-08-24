package com.foxety0f.proton.modules.reports.domain;

import java.util.Date;

public class MetaTablesRelations {

	private Integer id;
	private Integer idColumnCore;
	private String columnName;
	private Integer idTable;
	private String tableName;
	private Integer idColumnSup;
	private String columnNameSup;
	private Integer idTableSup;
	private String tableNameSup;
	private String name;
	private String description;
	private Boolean isActive;
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdColumnCore() {
		return idColumnCore;
	}

	public void setIdColumnCore(Integer idColumnCore) {
		this.idColumnCore = idColumnCore;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getIdTable() {
		return idTable;
	}

	public void setIdTable(Integer idTable) {
		this.idTable = idTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getIdColumnSup() {
		return idColumnSup;
	}

	public void setIdColumnSup(Integer idColumnSup) {
		this.idColumnSup = idColumnSup;
	}

	public String getColumnNameSup() {
		return columnNameSup;
	}

	public void setColumnNameSup(String columnNameSup) {
		this.columnNameSup = columnNameSup;
	}

	public Integer getIdTableSup() {
		return idTableSup;
	}

	public void setIdTableSup(Integer idTableSup) {
		this.idTableSup = idTableSup;
	}

	public String getTableNameSup() {
		return tableNameSup;
	}

	public void setTableNameSup(String tableNameSup) {
		this.tableNameSup = tableNameSup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((columnNameSup == null) ? 0 : columnNameSup.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idColumnCore == null) ? 0 : idColumnCore.hashCode());
		result = prime * result + ((idColumnSup == null) ? 0 : idColumnSup.hashCode());
		result = prime * result + ((idTable == null) ? 0 : idTable.hashCode());
		result = prime * result + ((idTableSup == null) ? 0 : idTableSup.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((tableNameSup == null) ? 0 : tableNameSup.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetaTablesRelations other = (MetaTablesRelations) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnNameSup == null) {
			if (other.columnNameSup != null)
				return false;
		} else if (!columnNameSup.equals(other.columnNameSup))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idColumnCore == null) {
			if (other.idColumnCore != null)
				return false;
		} else if (!idColumnCore.equals(other.idColumnCore))
			return false;
		if (idColumnSup == null) {
			if (other.idColumnSup != null)
				return false;
		} else if (!idColumnSup.equals(other.idColumnSup))
			return false;
		if (idTable == null) {
			if (other.idTable != null)
				return false;
		} else if (!idTable.equals(other.idTable))
			return false;
		if (idTableSup == null) {
			if (other.idTableSup != null)
				return false;
		} else if (!idTableSup.equals(other.idTableSup))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (tableNameSup == null) {
			if (other.tableNameSup != null)
				return false;
		} else if (!tableNameSup.equals(other.tableNameSup))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetaTablesRelations [id=" + id + ", idColumnCore=" + idColumnCore + ", columnName=" + columnName
				+ ", idTable=" + idTable + ", tableName=" + tableName + ", idColumnSup=" + idColumnSup
				+ ", columnNameSup=" + columnNameSup + ", idTableSup=" + idTableSup + ", tableNameSup=" + tableNameSup
				+ ", name=" + name + ", description=" + description + ", isActive=" + isActive + ", createDate="
				+ createDate + "]";
	}

}
