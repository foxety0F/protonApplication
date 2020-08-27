package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class RolesSecurity {

	private Integer id;
	private String name;
	private String description;
	private Date cDate;
	private Date uDate;
	private Integer unfaceId;

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

	public Integer getUnfaceId() {
		return unfaceId;
	}

	public void setUnfaceId(Integer unfaceId) {
		this.unfaceId = unfaceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
		result = prime * result + ((unfaceId == null) ? 0 : unfaceId.hashCode());
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
		RolesSecurity other = (RolesSecurity) obj;
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
		if (unfaceId == null) {
			if (other.unfaceId != null)
				return false;
		} else if (!unfaceId.equals(other.unfaceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RolesSecurity [id=" + id + ", name=" + name + ", description=" + description + ", cDate=" + cDate
				+ ", uDate=" + uDate + ", unfaceId=" + unfaceId + "]";
	}

}
