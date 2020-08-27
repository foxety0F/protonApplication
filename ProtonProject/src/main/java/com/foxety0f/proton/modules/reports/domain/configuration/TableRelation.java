package com.foxety0f.proton.modules.reports.domain.configuration;

public class TableRelation {

	private Integer id;
	private Integer tableId;
	private Integer relType;

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

	public Integer getRelType() {
		return relType;
	}

	public void setRelType(Integer relType) {
		this.relType = relType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((relType == null) ? 0 : relType.hashCode());
		result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
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
		TableRelation other = (TableRelation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (relType == null) {
			if (other.relType != null)
				return false;
		} else if (!relType.equals(other.relType))
			return false;
		if (tableId == null) {
			if (other.tableId != null)
				return false;
		} else if (!tableId.equals(other.tableId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TableRelation [id=" + id + ", tableId=" + tableId + ", relType=" + relType + "]";
	}

}
