package com.foxety0f.proton.modules.employees.domain;

public class EmployeesGroup {

	private Integer idGroup;
	private String nameGroup;
	private String description;

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EmployeesGroups [idGroup=" + idGroup + ", nameGroup=" + nameGroup + ", description=" + description
				+ "]";
	}

}
