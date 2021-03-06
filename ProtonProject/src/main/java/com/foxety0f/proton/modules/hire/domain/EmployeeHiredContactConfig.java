package com.foxety0f.proton.modules.hire.domain;

public class EmployeeHiredContactConfig {

	private Integer id;
	private String name;
	private String icon;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EmployeeHiredContactConfig [id=" + id + ", name=" + name + ", icon=" + icon + ", description="
				+ description + "]";
	}

}
