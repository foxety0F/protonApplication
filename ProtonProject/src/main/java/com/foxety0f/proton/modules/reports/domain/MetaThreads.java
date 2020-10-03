package com.foxety0f.proton.modules.reports.domain;

import java.util.Date;

public class MetaThreads {

	private Integer id;
	private Integer databaseId;
	private String name;
	private String description;
	private Boolean isActive;
	private Date cDate;
	private Date uDate;
	private Integer coreTableId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public Integer getCoreTableId() {
		return coreTableId;
	}

	public void setCoreTableId(Integer coreTableId) {
		this.coreTableId = coreTableId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((coreTableId == null) ? 0 : coreTableId.hashCode());
		result = prime * result + ((databaseId == null) ? 0 : databaseId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
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
		MetaThreads other = (MetaThreads) obj;
		if (cDate == null) {
			if (other.cDate != null)
				return false;
		} else if (!cDate.equals(other.cDate))
			return false;
		if (coreTableId == null) {
			if (other.coreTableId != null)
				return false;
		} else if (!coreTableId.equals(other.coreTableId))
			return false;
		if (databaseId == null) {
			if (other.databaseId != null)
				return false;
		} else if (!databaseId.equals(other.databaseId))
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
		if (uDate == null) {
			if (other.uDate != null)
				return false;
		} else if (!uDate.equals(other.uDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetaThreads [id=" + id + ", databaseId=" + databaseId + ", name=" + name + ", description="
				+ description + ", isActive=" + isActive + ", cDate=" + cDate + ", uDate=" + uDate + ", coreTableId="
				+ coreTableId + "]";
	}

}
