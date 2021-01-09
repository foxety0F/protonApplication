package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorSystemInformation {

    private Integer id;
    private String systemName;
    private String description;
    private String systemType;
    private Integer systemTypeId;
    private List<ConstructorThreadInformation> threads;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getSystemName() {
	return systemName;
    }

    public void setSystemName(String systemName) {
	this.systemName = systemName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getSystemType() {
	return systemType;
    }

    public void setSystemType(String systemType) {
	this.systemType = systemType;
    }

    public Integer getSystemTypeId() {
	return systemTypeId;
    }

    public void setSystemTypeId(Integer systemTypeId) {
	this.systemTypeId = systemTypeId;
    }

    public List<ConstructorThreadInformation> getThreads() {
	return threads;
    }

    public void setThreads(List<ConstructorThreadInformation> threads) {
	this.threads = threads;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
	result = prime * result + ((systemType == null) ? 0 : systemType.hashCode());
	result = prime * result + ((systemTypeId == null) ? 0 : systemTypeId.hashCode());
	result = prime * result + ((threads == null) ? 0 : threads.hashCode());
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
	ConstructorSystemInformation other = (ConstructorSystemInformation) obj;
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
	if (systemName == null) {
	    if (other.systemName != null)
		return false;
	} else if (!systemName.equals(other.systemName))
	    return false;
	if (systemType == null) {
	    if (other.systemType != null)
		return false;
	} else if (!systemType.equals(other.systemType))
	    return false;
	if (systemTypeId == null) {
	    if (other.systemTypeId != null)
		return false;
	} else if (!systemTypeId.equals(other.systemTypeId))
	    return false;
	if (threads == null) {
	    if (other.threads != null)
		return false;
	} else if (!threads.equals(other.threads))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorSystemInformation [id=" + id + ", systemName=" + systemName + ", description=" + description
		+ ", systemType=" + systemType + ", systemTypeId=" + systemTypeId + ", threads=" + threads + "]";
    }

}
