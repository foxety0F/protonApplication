package com.foxety0f.proton.modules.constructor.domain;

import java.sql.Date;

public class ReportingMetaRelations {

    private Integer id;
    private String title;
    private String description;
    private Integer coreTableId;
    private Integer refTableId;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getCoreTableId() {
	return coreTableId;
    }

    public void setCoreTableId(Integer coreTableId) {
	this.coreTableId = coreTableId;
    }

    public Integer getRefTableId() {
	return refTableId;
    }

    public void setRefTableId(Integer refTableId) {
	this.refTableId = refTableId;
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
	result = prime * result + ((coreTableId == null) ? 0 : coreTableId.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((refTableId == null) ? 0 : refTableId.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
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
	ReportingMetaRelations other = (ReportingMetaRelations) obj;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
	    return false;
	if (coreTableId == null) {
	    if (other.coreTableId != null)
		return false;
	} else if (!coreTableId.equals(other.coreTableId))
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
	if (refTableId == null) {
	    if (other.refTableId != null)
		return false;
	} else if (!refTableId.equals(other.refTableId))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
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
	return "ReportingMetaRelations [id=" + id + ", title=" + title + ", description=" + description
		+ ", coreTableId=" + coreTableId + ", refTableId=" + refTableId + ", isActive=" + isActive + ", cDate="
		+ cDate + ", uDate=" + uDate + "]";
    }

}
