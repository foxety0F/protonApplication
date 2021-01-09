package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorTables {

    private Integer id;
    private Integer systemId;
    private String tableName;
    private String businessName;
    private String description;
    private String businessDescription;
    private Boolean isActive;
    private List<ConstructorColumn> columns;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getSystemId() {
	return systemId;
    }

    public void setSystemId(Integer systemId) {
	this.systemId = systemId;
    }

    public String getTableName() {
	return tableName;
    }

    public void setTableName(String tableName) {
	this.tableName = tableName;
    }

    public String getBusinessName() {
	return businessName;
    }

    public void setBusinessName(String businessName) {
	this.businessName = businessName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getBusinessDescription() {
	return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
	this.businessDescription = businessDescription;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
    }

    public List<ConstructorColumn> getColumns() {
	return columns;
    }

    public void setColumns(List<ConstructorColumn> columns) {
	this.columns = columns;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((businessDescription == null) ? 0 : businessDescription.hashCode());
	result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
	result = prime * result + ((columns == null) ? 0 : columns.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
	result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
	ConstructorTables other = (ConstructorTables) obj;
	if (businessDescription == null) {
	    if (other.businessDescription != null)
		return false;
	} else if (!businessDescription.equals(other.businessDescription))
	    return false;
	if (businessName == null) {
	    if (other.businessName != null)
		return false;
	} else if (!businessName.equals(other.businessName))
	    return false;
	if (columns == null) {
	    if (other.columns != null)
		return false;
	} else if (!columns.equals(other.columns))
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
	if (isActive == null) {
	    if (other.isActive != null)
		return false;
	} else if (!isActive.equals(other.isActive))
	    return false;
	if (systemId == null) {
	    if (other.systemId != null)
		return false;
	} else if (!systemId.equals(other.systemId))
	    return false;
	if (tableName == null) {
	    if (other.tableName != null)
		return false;
	} else if (!tableName.equals(other.tableName))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorTables [id=" + id + ", systemId=" + systemId + ", tableName=" + tableName + ", businessName="
		+ businessName + ", description=" + description + ", businessDescription=" + businessDescription
		+ ", isActive=" + isActive + ", columns=" + columns + "]";
    }

}
