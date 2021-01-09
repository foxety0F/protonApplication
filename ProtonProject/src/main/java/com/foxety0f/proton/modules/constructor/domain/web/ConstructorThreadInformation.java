package com.foxety0f.proton.modules.constructor.domain.web;

public class ConstructorThreadInformation {

    private Integer id;
    private String title;
    private String description;
    private Integer coreId;
    private String coreTableName;
    private String coreTableTechnicalName;
    private Boolean isNativeLang;
    private Integer systemId;

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

    public Integer getCoreId() {
	return coreId;
    }

    public void setCoreId(Integer coreId) {
	this.coreId = coreId;
    }

    public String getCoreTableName() {
	return coreTableName;
    }

    public void setCoreTableName(String coreTableName) {
	this.coreTableName = coreTableName;
    }

    public String getCoreTableTechnicalName() {
	return coreTableTechnicalName;
    }

    public void setCoreTableTechnicalName(String coreTableTechnicalName) {
	this.coreTableTechnicalName = coreTableTechnicalName;
    }

    public Boolean getIsNativeLang() {
	return isNativeLang;
    }

    public void setIsNativeLang(Boolean isNativeLang) {
	this.isNativeLang = isNativeLang;
    }

    public Integer getSystemId() {
	return systemId;
    }

    public void setSystemId(Integer systemId) {
	this.systemId = systemId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((coreId == null) ? 0 : coreId.hashCode());
	result = prime * result + ((coreTableName == null) ? 0 : coreTableName.hashCode());
	result = prime * result + ((coreTableTechnicalName == null) ? 0 : coreTableTechnicalName.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isNativeLang == null) ? 0 : isNativeLang.hashCode());
	result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
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
	ConstructorThreadInformation other = (ConstructorThreadInformation) obj;
	if (coreId == null) {
	    if (other.coreId != null)
		return false;
	} else if (!coreId.equals(other.coreId))
	    return false;
	if (coreTableName == null) {
	    if (other.coreTableName != null)
		return false;
	} else if (!coreTableName.equals(other.coreTableName))
	    return false;
	if (coreTableTechnicalName == null) {
	    if (other.coreTableTechnicalName != null)
		return false;
	} else if (!coreTableTechnicalName.equals(other.coreTableTechnicalName))
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
	if (isNativeLang == null) {
	    if (other.isNativeLang != null)
		return false;
	} else if (!isNativeLang.equals(other.isNativeLang))
	    return false;
	if (systemId == null) {
	    if (other.systemId != null)
		return false;
	} else if (!systemId.equals(other.systemId))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorThreadInformation [id=" + id + ", title=" + title + ", description=" + description
		+ ", coreId=" + coreId + ", coreTableName=" + coreTableName + ", coreTableTechnicalName="
		+ coreTableTechnicalName + ", isNativeLang=" + isNativeLang + ", systemId=" + systemId + "]";
    }

}
