package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorColumn {

    private Integer id;
    private Integer tableId;
    private Integer columnTypeId;
    private String columnName;
    private String businessName;
    private String description;
    private String businessDescription;
    private Integer ordinalPosition;
    private Boolean isActive;
    private Integer isRelColumn;
    private List<ConstructorAvailColumnParams> availColumnParameters;
    private List<ConstructorAvailConditionParams> availConditionParameters;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getTableId() {
	return tableId;
    }

    public void setTableId(Integer tableId) {
	this.tableId = tableId;
    }

    public Integer getColumnTypeId() {
	return columnTypeId;
    }

    public void setColumnTypeId(Integer columnTypeId) {
	this.columnTypeId = columnTypeId;
    }

    public String getColumnName() {
	return columnName;
    }

    public void setColumnName(String columnName) {
	this.columnName = columnName;
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

    public Integer getOrdinalPosition() {
	return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
	this.ordinalPosition = ordinalPosition;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
    }

    public Integer getIsRelColumn() {
	return isRelColumn;
    }

    public void setIsRelColumn(Integer isRelColumn) {
	this.isRelColumn = isRelColumn;
    }

    public List<ConstructorAvailColumnParams> getAvailColumnParameters() {
	return availColumnParameters;
    }

    public void setAvailColumnParameters(List<ConstructorAvailColumnParams> availColumnParameters) {
	this.availColumnParameters = availColumnParameters;
    }

    public List<ConstructorAvailConditionParams> getAvailConditionParameters() {
	return availConditionParameters;
    }

    public void setAvailConditionParameters(List<ConstructorAvailConditionParams> availConditionParameters) {
	this.availConditionParameters = availConditionParameters;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((availColumnParameters == null) ? 0 : availColumnParameters.hashCode());
	result = prime * result + ((availConditionParameters == null) ? 0 : availConditionParameters.hashCode());
	result = prime * result + ((businessDescription == null) ? 0 : businessDescription.hashCode());
	result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
	result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
	result = prime * result + ((columnTypeId == null) ? 0 : columnTypeId.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((isRelColumn == null) ? 0 : isRelColumn.hashCode());
	result = prime * result + ((ordinalPosition == null) ? 0 : ordinalPosition.hashCode());
	result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
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
	ConstructorColumn other = (ConstructorColumn) obj;
	if (availColumnParameters == null) {
	    if (other.availColumnParameters != null)
		return false;
	} else if (!availColumnParameters.equals(other.availColumnParameters))
	    return false;
	if (availConditionParameters == null) {
	    if (other.availConditionParameters != null)
		return false;
	} else if (!availConditionParameters.equals(other.availConditionParameters))
	    return false;
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
	if (columnName == null) {
	    if (other.columnName != null)
		return false;
	} else if (!columnName.equals(other.columnName))
	    return false;
	if (columnTypeId == null) {
	    if (other.columnTypeId != null)
		return false;
	} else if (!columnTypeId.equals(other.columnTypeId))
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
	if (isRelColumn == null) {
	    if (other.isRelColumn != null)
		return false;
	} else if (!isRelColumn.equals(other.isRelColumn))
	    return false;
	if (ordinalPosition == null) {
	    if (other.ordinalPosition != null)
		return false;
	} else if (!ordinalPosition.equals(other.ordinalPosition))
	    return false;
	if (tableId == null) {
	    if (other.tableId != null)
		return false;
	} else if (!tableId.equals(other.tableId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorColumn [id=" + id + ", tableId=" + tableId + ", columnTypeId=" + columnTypeId
		+ ", columnName=" + columnName + ", businessName=" + businessName + ", description=" + description
		+ ", businessDescription=" + businessDescription + ", ordinalPosition=" + ordinalPosition
		+ ", isActive=" + isActive + ", isRelColumn=" + isRelColumn + ", availColumnParameters="
		+ availColumnParameters + ", availConditionParameters=" + availConditionParameters + "]";
    }

}
