package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class ConditionType {

	private Integer id;
	private String name;
	private String description;
	private String replacementString;
	private Boolean isAggregate;
	private Date cDate;
	private Date uDate;
	private Boolean isActive;
	private Long userId;
	private Integer valueType;
	private Integer databaseType;

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

	public String getReplacementString() {
		return replacementString;
	}

	public void setReplacementString(String replacementString) {
		this.replacementString = replacementString;
	}

	public Boolean getIsAggregate() {
		return isAggregate;
	}

	public void setIsAggregate(Boolean isAggregate) {
		this.isAggregate = isAggregate;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getValueType() {
		return valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	public Integer getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(Integer databaseType) {
		this.databaseType = databaseType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((databaseType == null) ? 0 : databaseType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isAggregate == null) ? 0 : isAggregate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((replacementString == null) ? 0 : replacementString.hashCode());
		result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
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
		ConditionType other = (ConditionType) obj;
		if (cDate == null) {
			if (other.cDate != null)
				return false;
		} else if (!cDate.equals(other.cDate))
			return false;
		if (databaseType == null) {
			if (other.databaseType != null)
				return false;
		} else if (!databaseType.equals(other.databaseType))
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
		if (isAggregate == null) {
			if (other.isAggregate != null)
				return false;
		} else if (!isAggregate.equals(other.isAggregate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (replacementString == null) {
			if (other.replacementString != null)
				return false;
		} else if (!replacementString.equals(other.replacementString))
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
		if (valueType == null) {
			if (other.valueType != null)
				return false;
		} else if (!valueType.equals(other.valueType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConditionType [id=" + id + ", name=" + name + ", description=" + description + ", replacementString="
				+ replacementString + ", isAggregate=" + isAggregate + ", cDate=" + cDate + ", uDate=" + uDate
				+ ", isActive=" + isActive + ", userId=" + userId + ", valueType=" + valueType + ", databaseType="
				+ databaseType + "]";
	}

}
