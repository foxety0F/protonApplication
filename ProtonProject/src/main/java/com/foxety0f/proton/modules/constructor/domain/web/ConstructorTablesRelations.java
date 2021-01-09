package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorTablesRelations {

    private Integer coreId;
    private Integer refId;
    private List<ConstructorRelationJoinDefine> availJoins;
    private List<ConstructorSupportMapper> columnsJoin;
    private String coreTableName;
    private String refTableName;

    public Integer getCoreId() {
	return coreId;
    }

    public void setCoreId(Integer coreId) {
	this.coreId = coreId;
    }

    public Integer getRefId() {
	return refId;
    }

    public void setRefId(Integer refId) {
	this.refId = refId;
    }

    public List<ConstructorRelationJoinDefine> getAvailJoins() {
	return availJoins;
    }

    public void setAvailJoins(List<ConstructorRelationJoinDefine> availJoins) {
	this.availJoins = availJoins;
    }

    public List<ConstructorSupportMapper> getColumnsJoin() {
	return columnsJoin;
    }

    public void setColumnsJoin(List<ConstructorSupportMapper> columnsJoin) {
	this.columnsJoin = columnsJoin;
    }

    public String getCoreTableName() {
	return coreTableName;
    }

    public void setCoreTableName(String coreTableName) {
	this.coreTableName = coreTableName;
    }

    public String getRefTableName() {
	return refTableName;
    }

    public void setRefTableName(String refTableName) {
	this.refTableName = refTableName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((availJoins == null) ? 0 : availJoins.hashCode());
	result = prime * result + ((columnsJoin == null) ? 0 : columnsJoin.hashCode());
	result = prime * result + ((coreId == null) ? 0 : coreId.hashCode());
	result = prime * result + ((coreTableName == null) ? 0 : coreTableName.hashCode());
	result = prime * result + ((refId == null) ? 0 : refId.hashCode());
	result = prime * result + ((refTableName == null) ? 0 : refTableName.hashCode());
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
	ConstructorTablesRelations other = (ConstructorTablesRelations) obj;
	if (availJoins == null) {
	    if (other.availJoins != null)
		return false;
	} else if (!availJoins.equals(other.availJoins))
	    return false;
	if (columnsJoin == null) {
	    if (other.columnsJoin != null)
		return false;
	} else if (!columnsJoin.equals(other.columnsJoin))
	    return false;
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
	if (refId == null) {
	    if (other.refId != null)
		return false;
	} else if (!refId.equals(other.refId))
	    return false;
	if (refTableName == null) {
	    if (other.refTableName != null)
		return false;
	} else if (!refTableName.equals(other.refTableName))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorTablesRelations [coreId=" + coreId + ", refId=" + refId + ", availJoins=" + availJoins
		+ ", columnsJoin=" + columnsJoin + ", coreTableName=" + coreTableName + ", refTableName=" + refTableName
		+ "]";
    }

}
