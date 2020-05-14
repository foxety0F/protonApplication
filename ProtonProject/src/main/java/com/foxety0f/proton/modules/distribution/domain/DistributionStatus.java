package com.foxety0f.proton.modules.distribution.domain;

public class DistributionStatus {

	private Integer id;
	private String name;
	private String color;
	private Boolean isWorked;
	private Boolean isActive;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getIsWorked() {
		return isWorked;
	}

	public void setIsWorked(Boolean isWorked) {
		this.isWorked = isWorked;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DistributionStatus [id=" + id + ", name=" + name + ", color=" + color + ", isWorked=" + isWorked
				+ ", isActive=" + isActive + ", description=" + description + "]";
	}

}
