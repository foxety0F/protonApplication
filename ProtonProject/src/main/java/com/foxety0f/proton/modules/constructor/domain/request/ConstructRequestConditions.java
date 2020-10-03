package com.foxety0f.proton.modules.constructor.domain.request;

public class ConstructRequestConditions {

	private Integer columnId;
	private Integer partId;
	private Integer sepId;
	private String values;
	private Integer condType;

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public Integer getSepId() {
		return sepId;
	}

	public void setSepId(Integer sepId) {
		this.sepId = sepId;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public Integer getCondType() {
		return condType;
	}

	public void setCondType(Integer condType) {
		this.condType = condType;
	}

	@Override
	public String toString() {
		return "ConstructRequestConditions [columnId=" + columnId + ", partId=" + partId + ", sepId=" + sepId
				+ ", values=" + values + ", condType=" + condType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
		result = prime * result + ((condType == null) ? 0 : condType.hashCode());
		result = prime * result + ((partId == null) ? 0 : partId.hashCode());
		result = prime * result + ((sepId == null) ? 0 : sepId.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		ConstructRequestConditions other = (ConstructRequestConditions) obj;
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
		if (partId == null) {
			if (other.partId != null)
				return false;
		} else if (!partId.equals(other.partId))
			return false;
		if (sepId == null) {
			if (other.sepId != null)
				return false;
		} else if (!sepId.equals(other.sepId))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
