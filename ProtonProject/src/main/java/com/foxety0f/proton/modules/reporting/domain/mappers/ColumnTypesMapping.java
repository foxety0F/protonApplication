package com.foxety0f.proton.modules.reporting.domain.mappers;

import java.util.Date;

public class ColumnTypesMapping {

    private Integer id;
    private Integer typeId;
    private Integer systemId;
    private String name;
    private String value;
    private String pattern;
    private Date cDate;
    private Date uDate;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getTypeId() {
	return typeId;
    }

    public void setTypeId(Integer typeId) {
	this.typeId = typeId;
    }

    public Integer getSystemId() {
	return systemId;
    }

    public void setSystemId(Integer systemId) {
	this.systemId = systemId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getPattern() {
	return pattern;
    }

    public void setPattern(String pattern) {
	this.pattern = pattern;
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
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
	result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
	result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
	result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	ColumnTypesMapping other = (ColumnTypesMapping) obj;
	if (cDate == null) {
	    if (other.cDate != null)
		return false;
	} else if (!cDate.equals(other.cDate))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (pattern == null) {
	    if (other.pattern != null)
		return false;
	} else if (!pattern.equals(other.pattern))
	    return false;
	if (systemId == null) {
	    if (other.systemId != null)
		return false;
	} else if (!systemId.equals(other.systemId))
	    return false;
	if (typeId == null) {
	    if (other.typeId != null)
		return false;
	} else if (!typeId.equals(other.typeId))
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
	return true;
    }

    @Override
    public String toString() {
	return "ColumnTypesMapping [id=" + id + ", typeId=" + typeId + ", systemId=" + systemId + ", name=" + name
		+ ", value=" + value + ", pattern=" + pattern + ", cDate=" + cDate + ", uDate=" + uDate + "]";
    }

}
