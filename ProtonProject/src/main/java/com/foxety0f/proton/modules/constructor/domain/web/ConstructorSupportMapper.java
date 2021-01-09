package com.foxety0f.proton.modules.constructor.domain.web;

public class ConstructorSupportMapper {

    private Integer leftSide;
    private Integer rightSide;

    public Integer getLeftSide() {
	return leftSide;
    }

    public void setLeftSide(Integer leftSide) {
	this.leftSide = leftSide;
    }

    public Integer getRightSide() {
	return rightSide;
    }

    public void setRightSide(Integer rightSide) {
	this.rightSide = rightSide;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((leftSide == null) ? 0 : leftSide.hashCode());
	result = prime * result + ((rightSide == null) ? 0 : rightSide.hashCode());
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
	ConstructorSupportMapper other = (ConstructorSupportMapper) obj;
	if (leftSide == null) {
	    if (other.leftSide != null)
		return false;
	} else if (!leftSide.equals(other.leftSide))
	    return false;
	if (rightSide == null) {
	    if (other.rightSide != null)
		return false;
	} else if (!rightSide.equals(other.rightSide))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorSupportMapper [leftSide=" + leftSide + ", rightSide=" + rightSide + "]";
    }

}
