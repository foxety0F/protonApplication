package com.foxety0f.proton.modules.reporting.domain.meta;

public class SourceMetaColumn {

    private String tableName;
    private String columnName;
    private String tableMapper;
    private String typeMapper;
    private String description;

    public String getTableName() {
	return tableName;
    }

    public void setTableName(String tableName) {
	this.tableName = tableName;
    }

    public String getColumnName() {
	return columnName;
    }

    public void setColumnName(String columnName) {
	this.columnName = columnName;
    }

    public String getTableMapper() {
	return tableMapper;
    }

    public void setTableMapper(String tableMapper) {
	this.tableMapper = tableMapper;
    }

    public String getTypeMapper() {
	return typeMapper;
    }

    public void setTypeMapper(String typeMapper) {
	this.typeMapper = typeMapper;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((tableMapper == null) ? 0 : tableMapper.hashCode());
	result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
	result = prime * result + ((typeMapper == null) ? 0 : typeMapper.hashCode());
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
	SourceMetaColumn other = (SourceMetaColumn) obj;
	if (columnName == null) {
	    if (other.columnName != null)
		return false;
	} else if (!columnName.equals(other.columnName))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (tableMapper == null) {
	    if (other.tableMapper != null)
		return false;
	} else if (!tableMapper.equals(other.tableMapper))
	    return false;
	if (tableName == null) {
	    if (other.tableName != null)
		return false;
	} else if (!tableName.equals(other.tableName))
	    return false;
	if (typeMapper == null) {
	    if (other.typeMapper != null)
		return false;
	} else if (!typeMapper.equals(other.typeMapper))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "SourceMetaColumn [tableName=" + tableName + ", columnName=" + columnName + ", tableMapper="
		+ tableMapper + ", typeMapper=" + typeMapper + ", description=" + description + "]";
    }

}
