package com.foxety0f.proton.modules.constructor.domain.web;

public class ConstructorAvailConditionParameterReplacer {
    private Integer id;
    private Integer operationId;
    private Integer systemId;
    private String replacementPattern;
    private Boolean isActive;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public Integer getSystemId() {
	return systemId;
    }

    public void setSystemId(Integer systemId) {
	this.systemId = systemId;
    }

    public String getReplacementPattern() {
	return replacementPattern;
    }

    public void setReplacementPattern(String replacementPattern) {
	this.replacementPattern = replacementPattern;
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
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((operationId == null) ? 0 : operationId.hashCode());
	result = prime * result + ((replacementPattern == null) ? 0 : replacementPattern.hashCode());
	result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
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
	ConstructorAvailConditionParameterReplacer other = (ConstructorAvailConditionParameterReplacer) obj;
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
	if (operationId == null) {
	    if (other.operationId != null)
		return false;
	} else if (!operationId.equals(other.operationId))
	    return false;
	if (replacementPattern == null) {
	    if (other.replacementPattern != null)
		return false;
	} else if (!replacementPattern.equals(other.replacementPattern))
	    return false;
	if (systemId == null) {
	    if (other.systemId != null)
		return false;
	} else if (!systemId.equals(other.systemId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorAvailConditionParameterReplacer [id=" + id + ", operationId=" + operationId + ", systemId="
		+ systemId + ", replacementPattern=" + replacementPattern + ", isActive=" + isActive + "]";
    }

}
