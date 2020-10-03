package com.foxety0f.proton.modules.constructor.domain.request;

public class ConstructRequestColumns {

	private Integer columnId;
	private String altName;
	private Integer colTypeId;
	private String values;

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getAltName() {
		return altName;
	}

	public void setAltName(String altName) {
		this.altName = altName;
	}

	public Integer getColTypeId() {
		return colTypeId;
	}

	public void setColTypeId(Integer colTypeId) {
		this.colTypeId = colTypeId;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ConstructRequestColumns [columnId=" + columnId + ", altName=" + altName + ", colTypeId=" + colTypeId
				+ ", values=" + values + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altName == null) ? 0 : altName.hashCode());
		result = prime * result + ((colTypeId == null) ? 0 : colTypeId.hashCode());
		result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
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
		ConstructRequestColumns other = (ConstructRequestColumns) obj;
		if (altName == null) {
			if (other.altName != null)
				return false;
		} else if (!altName.equals(other.altName))
			return false;
		if (colTypeId == null) {
			if (other.colTypeId != null)
				return false;
		} else if (!colTypeId.equals(other.colTypeId))
			return false;
		if (columnId == null) {
			if (other.columnId != null)
				return false;
		} else if (!columnId.equals(other.columnId))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
