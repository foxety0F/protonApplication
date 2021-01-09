package com.foxety0f.proton.modules.constructor.domain;

import java.sql.Date;

public class ReportingOperationsParams {

    private Integer id;
    private Integer columnTypeId;
    private String replacementTitle;
    private String replacementValue;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getColumnTypeId() {
	return columnTypeId;
    }

    public void setColumnTypeId(Integer columnTypeId) {
	this.columnTypeId = columnTypeId;
    }

    public String getReplacementTitle() {
	return replacementTitle;
    }

    public void setReplacementTitle(String replacementTitle) {
	this.replacementTitle = replacementTitle;
    }

    public String getReplacementValue() {
	return replacementValue;
    }

    public void setReplacementValue(String replacementValue) {
	this.replacementValue = replacementValue;
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
	result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
	result = prime * result + ((columnTypeId == null) ? 0 : columnTypeId.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((replacementTitle == null) ? 0 : replacementTitle.hashCode());
	result = prime * result + ((replacementValue == null) ? 0 : replacementValue.hashCode());
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
	ReportingOperationsParams other = (ReportingOperationsParams) obj;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
	    return false;
	if (columnTypeId == null) {
	    if (other.columnTypeId != null)
		return false;
	} else if (!columnTypeId.equals(other.columnTypeId))
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
	if (replacementTitle == null) {
	    if (other.replacementTitle != null)
		return false;
	} else if (!replacementTitle.equals(other.replacementTitle))
	    return false;
	if (replacementValue == null) {
	    if (other.replacementValue != null)
		return false;
	} else if (!replacementValue.equals(other.replacementValue))
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
	return "ReportingOperationsParams [id=" + id + ", columnTypeId=" + columnTypeId + ", replacementTitle="
		+ replacementTitle + ", replacementValue=" + replacementValue + ", isActive=" + isActive + ", cDate="
		+ cDate + ", uDate=" + uDate + "]";
    }

}
