package com.foxety0f.proton.modules.constructor.domain;

import java.sql.Date;

public class ReportingColumnsParamsMppg {

    private Integer id;
    private Integer reportConditionId;
    private Integer opsParameterId;
    private String value;
    private Integer valueSeq;
    private String customName;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getReportConditionId() {
	return reportConditionId;
    }

    public void setReportConditionId(Integer reportConditionId) {
	this.reportConditionId = reportConditionId;
    }

    public Integer getOpsParameterId() {
	return opsParameterId;
    }

    public void setOpsParameterId(Integer opsParameterId) {
	this.opsParameterId = opsParameterId;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public Integer getValueSeq() {
	return valueSeq;
    }

    public void setValueSeq(Integer valueSeq) {
	this.valueSeq = valueSeq;
    }

    public String getCustomName() {
	return customName;
    }

    public void setCustomName(String customName) {
	this.customName = customName;
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
	result = prime * result + ((customName == null) ? 0 : customName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((opsParameterId == null) ? 0 : opsParameterId.hashCode());
	result = prime * result + ((reportConditionId == null) ? 0 : reportConditionId.hashCode());
	result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	result = prime * result + ((valueSeq == null) ? 0 : valueSeq.hashCode());
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
	ReportingColumnsParamsMppg other = (ReportingColumnsParamsMppg) obj;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
	    return false;
	if (customName == null) {
	    if (other.customName != null)
		return false;
	} else if (!customName.equals(other.customName))
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
	if (opsParameterId == null) {
	    if (other.opsParameterId != null)
		return false;
	} else if (!opsParameterId.equals(other.opsParameterId))
	    return false;
	if (reportConditionId == null) {
	    if (other.reportConditionId != null)
		return false;
	} else if (!reportConditionId.equals(other.reportConditionId))
	    return false;
	if (uDate == null) {
	    if (other.uDate != null)
		return false;
	} else if (!uDate.equals(other.uDate))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	if (valueSeq == null) {
	    if (other.valueSeq != null)
		return false;
	} else if (!valueSeq.equals(other.valueSeq))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ReportingColumnsParamsMppg [id=" + id + ", reportConditionId=" + reportConditionId + ", opsParameterId="
		+ opsParameterId + ", value=" + value + ", valueSeq=" + valueSeq + ", customName=" + customName
		+ ", isActive=" + isActive + ", cDate=" + cDate + ", uDate=" + uDate + "]";
    }

}
