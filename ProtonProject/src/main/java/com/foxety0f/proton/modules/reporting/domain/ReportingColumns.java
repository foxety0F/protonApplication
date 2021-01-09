package com.foxety0f.proton.modules.reporting.domain;

import java.util.Date;

public class ReportingColumns {

    private Integer id;
    private Integer tableId;
    private Integer columnTypeId;
    private String columnName;
    private String businessColumnName;
    private String description;
    private String businessDescription;
    private Boolean isDictVal;
    private Integer ordering;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

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

    public String getBusinessColumnName() {
	return businessColumnName;
    }

    public void setBusinessColumnName(String businessColumnName) {
	this.businessColumnName = businessColumnName;
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

    public Boolean getIsDictVal() {
	return isDictVal;
    }

    public void setIsDictVal(Boolean isDictVal) {
	this.isDictVal = isDictVal;
    }

    public Integer getOrdering() {
	return ordering;
    }

    public void setOrdering(Integer ordering) {
	this.ordering = ordering;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
    }

    public Date getcDate() {
	return cDate;
    }

    public void setcDate(Date cDate) {
	this.cDate = cDate;
    }

    public Date getuDate() {
	return uDate;
    }

    public void setuDate(Date uDate) {
	this.uDate = uDate;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((businessColumnName == null) ? 0 : businessColumnName.hashCode());
	result = prime * result + ((businessDescription == null) ? 0 : businessDescription.hashCode());
	result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
	result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
	result = prime * result + ((columnTypeId == null) ? 0 : columnTypeId.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((isDictVal == null) ? 0 : isDictVal.hashCode());
	result = prime * result + ((ordering == null) ? 0 : ordering.hashCode());
	result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
	result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
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
	ReportingColumns other = (ReportingColumns) obj;
	if (businessColumnName == null) {
	    if (other.businessColumnName != null)
		return false;
	} else if (!businessColumnName.equals(other.businessColumnName))
	    return false;
	if (businessDescription == null) {
	    if (other.businessDescription != null)
		return false;
	} else if (!businessDescription.equals(other.businessDescription))
	    return false;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
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
	if (isDictVal == null) {
	    if (other.isDictVal != null)
		return false;
	} else if (!isDictVal.equals(other.isDictVal))
	    return false;
	if (ordering == null) {
	    if (other.ordering != null)
		return false;
	} else if (!ordering.equals(other.ordering))
	    return false;
	if (tableId == null) {
	    if (other.tableId != null)
		return false;
	} else if (!tableId.equals(other.tableId))
	    return false;
	if (uDate == null) {
	    if (other.uDate != null)
		return false;
	} else if (!uDate.equals(other.uDate))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ReportingColumns [id=" + id + ", tableId=" + tableId + ", columnTypeId=" + columnTypeId
		+ ", columnName=" + columnName + ", businessColumnName=" + businessColumnName + ", description="
		+ description + ", businessDescription=" + businessDescription + ", isDictVal=" + isDictVal
		+ ", ordering=" + ordering + ", isActive=" + isActive + ", cDate=" + cDate + ", uDate=" + uDate + "]";
    }

}
