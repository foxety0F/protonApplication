package com.foxety0f.proton.modules.constructor.domain;

import java.sql.Date;

public class ReportingColumnsOperationsParametersMppg {

    private Integer id;
    private Integer paramId;
    private Integer columnOpId;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getParamId() {
	return paramId;
    }

    public void setParamId(Integer paramId) {
	this.paramId = paramId;
    }

    public Integer getColumnOpId() {
	return columnOpId;
    }

    public void setColumnOpId(Integer columnOpId) {
	this.columnOpId = columnOpId;
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
	result = prime * result + ((columnOpId == null) ? 0 : columnOpId.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((paramId == null) ? 0 : paramId.hashCode());
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
	ReportingColumnsOperationsParametersMppg other = (ReportingColumnsOperationsParametersMppg) obj;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
	    return false;
	if (columnOpId == null) {
	    if (other.columnOpId != null)
		return false;
	} else if (!columnOpId.equals(other.columnOpId))
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
	if (paramId == null) {
	    if (other.paramId != null)
		return false;
	} else if (!paramId.equals(other.paramId))
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
	return "ReportingColumnsOperationsParametersMppg [id=" + id + ", paramId=" + paramId + ", columnOpId="
		+ columnOpId + ", isActive=" + isActive + ", cDate=" + cDate + ", uDate=" + uDate + "]";
    }

}
