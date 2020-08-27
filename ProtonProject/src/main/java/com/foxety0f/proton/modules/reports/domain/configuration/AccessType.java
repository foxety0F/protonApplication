package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class AccessType {

	private Integer id;
	private String name;
	private String description;
	private Date cDate;
	private Date uDate;
	private Long userId;
	private Boolean isActive;
	private String provideTitle;
	private String provideDescription;
	private String successTitle;
	private String successDescription;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public Date getuDate() {
		return uDate;
	}

	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getProvideTitle() {
		return provideTitle;
	}

	public void setProvideTitle(String provideTitle) {
		this.provideTitle = provideTitle;
	}

	public String getProvideDescription() {
		return provideDescription;
	}

	public void setProvideDescription(String provideDescription) {
		this.provideDescription = provideDescription;
	}

	public String getSuccessTitle() {
		return successTitle;
	}

	public void setSuccessTitle(String successTitle) {
		this.successTitle = successTitle;
	}

	public String getSuccessDescription() {
		return successDescription;
	}

	public void setSuccessDescription(String successDescription) {
		this.successDescription = successDescription;
	}

	@Override
	public String toString() {
		return "AccessType [id=" + id + ", name=" + name + ", description=" + description + ", cDate=" + cDate
				+ ", uDate=" + uDate + ", userId=" + userId + ", isActive=" + isActive + ", provideTitle="
				+ provideTitle + ", provideDescription=" + provideDescription + ", successTitle=" + successTitle
				+ ", successDescription=" + successDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((provideDescription == null) ? 0 : provideDescription.hashCode());
		result = prime * result + ((provideTitle == null) ? 0 : provideTitle.hashCode());
		result = prime * result + ((successDescription == null) ? 0 : successDescription.hashCode());
		result = prime * result + ((successTitle == null) ? 0 : successTitle.hashCode());
		result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AccessType other = (AccessType) obj;
		if (cDate == null) {
			if (other.cDate != null)
				return false;
		} else if (!cDate.equals(other.cDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (provideDescription == null) {
			if (other.provideDescription != null)
				return false;
		} else if (!provideDescription.equals(other.provideDescription))
			return false;
		if (provideTitle == null) {
			if (other.provideTitle != null)
				return false;
		} else if (!provideTitle.equals(other.provideTitle))
			return false;
		if (successDescription == null) {
			if (other.successDescription != null)
				return false;
		} else if (!successDescription.equals(other.successDescription))
			return false;
		if (successTitle == null) {
			if (other.successTitle != null)
				return false;
		} else if (!successTitle.equals(other.successTitle))
			return false;
		if (uDate == null) {
			if (other.uDate != null)
				return false;
		} else if (!uDate.equals(other.uDate))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
