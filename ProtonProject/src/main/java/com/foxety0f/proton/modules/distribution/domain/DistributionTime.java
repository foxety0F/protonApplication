package com.foxety0f.proton.modules.distribution.domain;

public class DistributionTime {

	private Integer idTime;
	private String nameString;
	private Integer value;
	private String color;
	private String description;
	private Boolean isActive;

	public Integer getIdTime() {
		return idTime;
	}

	public void setIdTime(Integer idTime) {
		this.idTime = idTime;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DistributionTime [idTime=" + idTime + ", nameString=" + nameString + ", value=" + value + ", color="
				+ color + ", description=" + description + ", isActive=" + isActive + "]";
	}

}
