package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorRelationJoin {

    private Integer relationId;
    private List<ConstructorRelationJoinDefine> joins;
    private Integer coreId;
    private String coreTableName;
    private Integer refId;
    private String refTableName;
    private List<ConstructorSupportMapper> columnsRel;

    public Integer getRelationId() {
	return relationId;
    }

    public void setRelationId(Integer relationId) {
	this.relationId = relationId;
    }

    public List<ConstructorRelationJoinDefine> getJoins() {
	return joins;
    }

    public void setJoins(List<ConstructorRelationJoinDefine> joins) {
	this.joins = joins;
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

    public Integer getRefId() {
	return refId;
    }

    public void setRefId(Integer refId) {
	this.refId = refId;
    }

    public String getRefTableName() {
	return refTableName;
    }

    public void setRefTableName(String refTableName) {
	this.refTableName = refTableName;
    }

    public List<ConstructorSupportMapper> getColumnsRel() {
	return columnsRel;
    }

    public void setColumnsRel(List<ConstructorSupportMapper> columnsRel) {
	this.columnsRel = columnsRel;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columnsRel == null) ? 0 : columnsRel.hashCode());
	result = prime * result + ((coreId == null) ? 0 : coreId.hashCode());
	result = prime * result + ((coreTableName == null) ? 0 : coreTableName.hashCode());
	result = prime * result + ((joins == null) ? 0 : joins.hashCode());
	result = prime * result + ((refId == null) ? 0 : refId.hashCode());
	result = prime * result + ((refTableName == null) ? 0 : refTableName.hashCode());
	result = prime * result + ((relationId == null) ? 0 : relationId.hashCode());
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
	ConstructorRelationJoin other = (ConstructorRelationJoin) obj;
	if (columnsRel == null) {
	    if (other.columnsRel != null)
		return false;
	} else if (!columnsRel.equals(other.columnsRel))
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
	if (joins == null) {
	    if (other.joins != null)
		return false;
	} else if (!joins.equals(other.joins))
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
	if (relationId == null) {
	    if (other.relationId != null)
		return false;
	} else if (!relationId.equals(other.relationId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorRelationJoin [relationId=" + relationId + ", joins=" + joins + ", coreId=" + coreId
		+ ", coreTableName=" + coreTableName + ", refId=" + refId + ", refTableName=" + refTableName
		+ ", columnsRel=" + columnsRel + "]";
    }

}
