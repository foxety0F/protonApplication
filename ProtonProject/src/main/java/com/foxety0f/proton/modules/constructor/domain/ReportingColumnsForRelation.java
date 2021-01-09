package com.foxety0f.proton.modules.constructor.domain;

public class ReportingColumnsForRelation {

    private Integer relationId;
    private Integer coreColumnId;
    private Integer refColumnId;

    public Integer getRelationId() {
	return relationId;
    }

    public void setRelationId(Integer relationId) {
	this.relationId = relationId;
    }

    public Integer getCoreColumnId() {
	return coreColumnId;
    }

    public void setCoreColumnId(Integer coreColumnId) {
	this.coreColumnId = coreColumnId;
    }

    public Integer getRefColumnId() {
	return refColumnId;
    }

    public void setRefColumnId(Integer refColumnId) {
	this.refColumnId = refColumnId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((coreColumnId == null) ? 0 : coreColumnId.hashCode());
	result = prime * result + ((refColumnId == null) ? 0 : refColumnId.hashCode());
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
	ReportingColumnsForRelation other = (ReportingColumnsForRelation) obj;
	if (coreColumnId == null) {
	    if (other.coreColumnId != null)
		return false;
	} else if (!coreColumnId.equals(other.coreColumnId))
	    return false;
	if (refColumnId == null) {
	    if (other.refColumnId != null)
		return false;
	} else if (!refColumnId.equals(other.refColumnId))
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
	return "ReportingColumnsForRelation [relationId=" + relationId + ", coreColumnId=" + coreColumnId
		+ ", refColumnId=" + refColumnId + "]";
    }

}
