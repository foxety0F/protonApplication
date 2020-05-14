package com.foxety0f.proton.modules.employees.domain;

public class EmployeeTitle {

	private Integer titleId;
	private String titleName;
	private String description;

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EmployeeTitle [titleId=" + titleId + ", titleName=" + titleName + ", description=" + description + "]";
	}

}
