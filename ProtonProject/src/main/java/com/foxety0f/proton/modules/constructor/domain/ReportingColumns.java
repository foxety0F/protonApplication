package com.foxety0f.proton.modules.constructor.domain;

import java.sql.Date;

public class ReportingColumns {

    private Integer id;
    private Integer reportId;
    private Integer columnId;
    private Integer operationId;
    private String custom_name;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getReportId() {
	return reportId;
    }

    public void setReportId(Integer reportId) {
	this.reportId = reportId;
    }

    public Integer getColumnId() {
	return columnId;
    }

    public void setColumnId(Integer columnId) {
	this.columnId = columnId;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public String getCustom_name() {
	return custom_name;
    }

    public void setCustom_name(String custom_name) {
	this.custom_name = custom_name;
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
	result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
	result = prime * result + ((custom_name == null) ? 0 : custom_name.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((operationId == null) ? 0 : operationId.hashCode());
	result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
	    return false;
	if (columnId == null) {
	    if (other.columnId != null)
		return false;
	} else if (!columnId.equals(other.columnId))
	    return false;
	if (custom_name == null) {
	    if (other.custom_name != null)
		return false;
	} else if (!custom_name.equals(other.custom_name))
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
	if (operationId == null) {
	    if (other.operationId != null)
		return false;
	} else if (!operationId.equals(other.operationId))
	    return false;
	if (reportId == null) {
	    if (other.reportId != null)
		return false;
	} else if (!reportId.equals(other.reportId))
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
	return "ReportingColumns [id=" + id + ", reportId=" + reportId + ", columnId=" + columnId + ", operationId="
		+ operationId + ", custom_name=" + custom_name + ", isActive=" + isActive + ", cDate=" + cDate
		+ ", uDate=" + uDate + "]";
    }

}
