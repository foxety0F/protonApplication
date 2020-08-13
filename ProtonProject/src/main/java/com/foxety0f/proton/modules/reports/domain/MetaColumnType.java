package com.foxety0f.proton.modules.reports.domain;

public class MetaColumnType {

	private Integer id;
	private String overName;
	private String nameType;
	private String sourceName;
	private Boolean isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOverName() {
		return overName;
	}

	public void setOverName(String overName) {
		this.overName = overName;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((nameType == null) ? 0 : nameType.hashCode());
		result = prime * result + ((overName == null) ? 0 : overName.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
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
		MetaColumnType other = (MetaColumnType) obj;
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
		if (nameType == null) {
			if (other.nameType != null)
				return false;
		} else if (!nameType.equals(other.nameType))
			return false;
		if (overName == null) {
			if (other.overName != null)
				return false;
		} else if (!overName.equals(other.overName))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetaColumnType [id=" + id + ", overName=" + overName + ", nameType=" + nameType + ", sourceName="
				+ sourceName + ", isActive=" + isActive + "]";
	}

}
