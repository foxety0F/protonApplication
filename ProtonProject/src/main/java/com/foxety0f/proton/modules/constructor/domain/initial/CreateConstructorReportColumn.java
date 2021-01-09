package com.foxety0f.proton.modules.constructor.domain.initial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateConstructorReportColumn {

    private Integer id;
    private String customName;
    private Integer columnAvailParam;

    @JsonProperty("id")
    public Integer getId() {
	return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
	this.id = id;
    }

    @JsonProperty("customName")
    public String getCustomName() {
	return customName;
    }

    @JsonProperty("customName")
    public void setCustomName(String customName) {
	this.customName = customName;
    }

    @JsonProperty("columnAvailParam")
    public Integer getColumnAvailParam() {
	return columnAvailParam;
    }

    @JsonProperty("columnAvailParam")
    public void setColumnAvailParam(Integer columnAvailParam) {
	this.columnAvailParam = columnAvailParam;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columnAvailParam == null) ? 0 : columnAvailParam.hashCode());
	result = prime * result + ((customName == null) ? 0 : customName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	CreateConstructorReportColumn other = (CreateConstructorReportColumn) obj;
	if (columnAvailParam == null) {
	    if (other.columnAvailParam != null)
		return false;
	} else if (!columnAvailParam.equals(other.columnAvailParam))
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
	return true;
    }

    @Override
    public String toString() {
	return "CreateConstructorReportColumn [id=" + id + ", customName=" + customName + ", columnAvailParam="
		+ columnAvailParam + "]";
    }

}
