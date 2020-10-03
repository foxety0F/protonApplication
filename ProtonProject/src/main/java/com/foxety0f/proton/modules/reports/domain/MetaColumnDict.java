package com.foxety0f.proton.modules.reports.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaColumnDict {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("columnId")
	private Integer columnId;
	@JsonProperty("dictValue")
	private String dictValue;
	@JsonProperty("altName")
	private String altName;

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

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getAltName() {
		return altName;
	}

	public void setAltName(String altName) {
		this.altName = altName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altName == null) ? 0 : altName.hashCode());
		result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
		result = prime * result + ((dictValue == null) ? 0 : dictValue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MetaColumnDict other = (MetaColumnDict) obj;
		if (altName == null) {
			if (other.altName != null)
				return false;
		} else if (!altName.equals(other.altName))
			return false;
		if (columnId == null) {
			if (other.columnId != null)
				return false;
		} else if (!columnId.equals(other.columnId))
			return false;
		if (dictValue == null) {
			if (other.dictValue != null)
				return false;
		} else if (!dictValue.equals(other.dictValue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetaColumnDict [id=" + id + ", columnId=" + columnId + ", dictValue=" + dictValue + ", altName="
				+ altName + "]";
	}

}
