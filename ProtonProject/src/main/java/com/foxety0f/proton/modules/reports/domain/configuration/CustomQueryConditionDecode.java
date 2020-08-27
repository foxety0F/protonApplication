package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class CustomQueryConditionDecode {

	private Integer id;
	private Integer columnId;
	private String stringValue;
	private Integer parentQueryCond;
	private Date cDate;
	private Date uDate;
	private Long userId;
	private Integer condType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Integer getParentQueryCond() {
		return parentQueryCond;
	}

	public void setParentQueryCond(Integer parentQueryCond) {
		this.parentQueryCond = parentQueryCond;
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

	public Integer getCondType() {
		return condType;
	}

	public void setCondType(Integer condType) {
		this.condType = condType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
		result = prime * result + ((condType == null) ? 0 : condType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentQueryCond == null) ? 0 : parentQueryCond.hashCode());
		result = prime * result + ((stringValue == null) ? 0 : stringValue.hashCode());
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
		CustomQueryConditionDecode other = (CustomQueryConditionDecode) obj;
		if (cDate == null) {
			if (other.cDate != null)
				return false;
		} else if (!cDate.equals(other.cDate))
			return false;
		if (columnId == null) {
			if (other.columnId != null)
				return false;
		} else if (!columnId.equals(other.columnId))
			return false;
		if (condType == null) {
			if (other.condType != null)
				return false;
		} else if (!condType.equals(other.condType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentQueryCond == null) {
			if (other.parentQueryCond != null)
				return false;
		} else if (!parentQueryCond.equals(other.parentQueryCond))
			return false;
		if (stringValue == null) {
			if (other.stringValue != null)
				return false;
		} else if (!stringValue.equals(other.stringValue))
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

	@Override
	public String toString() {
		return "CustomQueryConditionDecode [id=" + id + ", columnId=" + columnId + ", stringValue=" + stringValue
				+ ", parentQueryCond=" + parentQueryCond + ", cDate=" + cDate + ", uDate=" + uDate + ", userId="
				+ userId + ", condType=" + condType + "]";
	}

}
