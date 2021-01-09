package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorAvailConditionParams {

    private Integer id;
    private Integer columnType;
    private String title;
    private String description;
    private String pseudoLogic;
    private List<ConstructorAvailConditionParameterReplacer> columnReplacer;
    private Boolean isActive;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getColumnType() {
	return columnType;
    }

    public void setColumnType(Integer columnType) {
	this.columnType = columnType;
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

    public String getPseudoLogic() {
	return pseudoLogic;
    }

    public void setPseudoLogic(String pseudoLogic) {
	this.pseudoLogic = pseudoLogic;
    }

    public List<ConstructorAvailConditionParameterReplacer> getColumnReplacer() {
	return columnReplacer;
    }

    public void setColumnReplacer(List<ConstructorAvailConditionParameterReplacer> columnReplacer) {
	this.columnReplacer = columnReplacer;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columnReplacer == null) ? 0 : columnReplacer.hashCode());
	result = prime * result + ((columnType == null) ? 0 : columnType.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((pseudoLogic == null) ? 0 : pseudoLogic.hashCode());
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
	ConstructorAvailConditionParams other = (ConstructorAvailConditionParams) obj;
	if (columnReplacer == null) {
	    if (other.columnReplacer != null)
		return false;
	} else if (!columnReplacer.equals(other.columnReplacer))
	    return false;
	if (columnType == null) {
	    if (other.columnType != null)
		return false;
	} else if (!columnType.equals(other.columnType))
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
	if (pseudoLogic == null) {
	    if (other.pseudoLogic != null)
		return false;
	} else if (!pseudoLogic.equals(other.pseudoLogic))
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
	return "ConstructorAvailConditionParams [id=" + id + ", columnType=" + columnType + ", title=" + title
		+ ", description=" + description + ", pseudoLogic=" + pseudoLogic + ", columnReplacer=" + columnReplacer
		+ ", isActive=" + isActive + "]";
    }

}
