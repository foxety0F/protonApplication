package com.foxety0f.proton.modules.hire.domain;

import java.util.Date;

public class EmployeeHiredExperience {

	private Integer experienceId;
	private String titleName;
	private String description;
	private Date startDate;
	private Date endDate;
	private String skillPoints;
	private Integer briefId;
	private Integer orderId;
	private boolean isCurrent;
	private String companyName;

	public Integer getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Integer experienceId) {
		this.experienceId = experienceId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(String skillPoints) {
		this.skillPoints = skillPoints;
	}

	public Integer getBriefId() {
		return briefId;
	}

	public void setBriefId(Integer briefId) {
		this.briefId = briefId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((briefId == null) ? 0 : briefId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((experienceId == null) ? 0 : experienceId.hashCode());
		result = prime * result + (isCurrent ? 1231 : 1237);
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((skillPoints == null) ? 0 : skillPoints.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((titleName == null) ? 0 : titleName.hashCode());
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
		EmployeeHiredExperience other = (EmployeeHiredExperience) obj;
		if (briefId == null) {
			if (other.briefId != null)
				return false;
		} else if (!briefId.equals(other.briefId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (experienceId == null) {
			if (other.experienceId != null)
				return false;
		} else if (!experienceId.equals(other.experienceId))
			return false;
		if (isCurrent != other.isCurrent)
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (skillPoints == null) {
			if (other.skillPoints != null)
				return false;
		} else if (!skillPoints.equals(other.skillPoints))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeHiredExperience [experienceId=" + experienceId + ", titleName=" + titleName + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", skillPoints=" + skillPoints
				+ ", briefId=" + briefId + ", orderId=" + orderId + ", isCurrent=" + isCurrent + ", companyName="
				+ companyName + "]";
	}

}
