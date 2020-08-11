package com.foxety0f.proton.modules.reports.domain;

import java.util.Date;

public class MetaColumns {

	private Integer id;
	private Integer tableId;
	private String columnName;
	private String description;
	private String altName;
	private Integer columnType;
	private Boolean isDictVal;
	private Boolean isActive;
	private Date cDate;
	private Date uDate;
	private Boolean isNeedEncrypt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAltName() {
		return altName;
	}

	public void setAltName(String altName) {
		this.altName = altName;
	}

	public Integer getColumnType() {
		return columnType;
	}

	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}

	public Boolean getIsDictVal() {
		return isDictVal;
	}

	public void setIsDictVal(Boolean isDictVal) {
		this.isDictVal = isDictVal;
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

	public Boolean getIsNeedEncrypt() {
		return isNeedEncrypt;
	}

	public void setIsNeedEncrypt(Boolean isNeedEncrypt) {
		this.isNeedEncrypt = isNeedEncrypt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altName == null) ? 0 : altName.hashCode());
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((columnType == null) ? 0 : columnType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isDictVal == null) ? 0 : isDictVal.hashCode());
		result = prime * result + ((isNeedEncrypt == null) ? 0 : isNeedEncrypt.hashCode());
		result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
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
		MetaColumns other = (MetaColumns) obj;
		if (altName == null) {
			if (other.altName != null)
				return false;
		} else if (!altName.equals(other.altName))
			return false;
		if (cDate == null) {
			if (other.cDate != null)
				return false;
		} else if (!cDate.equals(other.cDate))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnType == null) {
			if (other.columnType != null)
				return false;
		} else if (!columnType.equals(other.columnType))
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
		if (isDictVal == null) {
			if (other.isDictVal != null)
				return false;
		} else if (!isDictVal.equals(other.isDictVal))
			return false;
		if (isNeedEncrypt == null) {
			if (other.isNeedEncrypt != null)
				return false;
		} else if (!isNeedEncrypt.equals(other.isNeedEncrypt))
			return false;
		if (tableId == null) {
			if (other.tableId != null)
				return false;
		} else if (!tableId.equals(other.tableId))
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
		return "MetaColumns [id=" + id + ", tableId=" + tableId + ", columnName=" + columnName + ", description="
				+ description + ", altName=" + altName + ", columnType=" + columnType + ", isDictVal=" + isDictVal
				+ ", isActive=" + isActive + ", cDate=" + cDate + ", uDate=" + uDate + ", isNeedEncrypt="
				+ isNeedEncrypt + "]";
	}

}
