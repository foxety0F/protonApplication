package com.foxety0f.proton.modules.constructor.domain.web;

public class ConstructorRelationJoinDefine extends ConstructorAvailJoinTypes {

    private Boolean isDefault;

    public Boolean getIsDefault() {
	return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
	this.isDefault = isDefault;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ConstructorRelationJoinDefine other = (ConstructorRelationJoinDefine) obj;
	if (isDefault == null) {
	    if (other.isDefault != null)
		return false;
	} else if (!isDefault.equals(other.isDefault))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorRelationJoinDefine [isDefault=" + isDefault + "]";
    }

}
