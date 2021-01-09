package com.foxety0f.proton.modules.constructor.domain;

import java.sql.Date;

public class ReportsConfig {

    private Integer id;
    private Integer workspaceId;
    private Integer ownerId;
    private Integer threadId;
    private String title;
    private String description;
    private String url;
    private Boolean isActive;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getWorkspaceId() {
	return workspaceId;
    }

    public void setWorkspaceId(Integer workspaceId) {
	this.workspaceId = workspaceId;
    }

    public Integer getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
	this.ownerId = ownerId;
    }

    public Integer getThreadId() {
	return threadId;
    }

    public void setThreadId(Integer threadId) {
	this.threadId = threadId;
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

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
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
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
	result = prime * result + ((threadId == null) ? 0 : threadId.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
	result = prime * result + ((url == null) ? 0 : url.hashCode());
	result = prime * result + ((workspaceId == null) ? 0 : workspaceId.hashCode());
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
	ReportsConfig other = (ReportsConfig) obj;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
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
	if (ownerId == null) {
	    if (other.ownerId != null)
		return false;
	} else if (!ownerId.equals(other.ownerId))
	    return false;
	if (threadId == null) {
	    if (other.threadId != null)
		return false;
	} else if (!threadId.equals(other.threadId))
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
	if (url == null) {
	    if (other.url != null)
		return false;
	} else if (!url.equals(other.url))
	    return false;
	if (workspaceId == null) {
	    if (other.workspaceId != null)
		return false;
	} else if (!workspaceId.equals(other.workspaceId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ReportsConfig [id=" + id + ", workspaceId=" + workspaceId + ", ownerId=" + ownerId + ", threadId="
		+ threadId + ", title=" + title + ", description=" + description + ", url=" + url + ", isActive="
		+ isActive + ", cDate=" + cDate + ", uDate=" + uDate + "]";
    }

}
