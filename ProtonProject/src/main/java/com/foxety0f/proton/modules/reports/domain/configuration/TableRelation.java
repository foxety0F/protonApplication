package com.foxety0f.proton.modules.reports.domain.configuration;

public class TableRelation {

	private Integer id;
	private Integer tableId;
	private Integer columnId;
	private Integer tableSub;
	private Integer columnSub;
	private Integer relType;
	private Integer linkRow;

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

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public Integer getTableSub() {
		return tableSub;
	}

	public void setTableSub(Integer tableSub) {
		this.tableSub = tableSub;
	}

	public Integer getColumnSub() {
		return columnSub;
	}

	public void setColumnSub(Integer columnSub) {
		this.columnSub = columnSub;
	}

	public Integer getRelType() {
		return relType;
	}

	public void setRelType(Integer relType) {
		this.relType = relType;
	}

	public Integer getLinkRow() {
		return linkRow;
	}

	public void setLinkRow(Integer linkRow) {
		this.linkRow = linkRow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
		result = prime * result + ((columnSub == null) ? 0 : columnSub.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((linkRow == null) ? 0 : linkRow.hashCode());
		result = prime * result + ((relType == null) ? 0 : relType.hashCode());
		result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
		result = prime * result + ((tableSub == null) ? 0 : tableSub.hashCode());
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
		if (columnId == null) {
			if (other.columnId != null)
				return false;
		} else if (!columnId.equals(other.columnId))
			return false;
		if (columnSub == null) {
			if (other.columnSub != null)
				return false;
		} else if (!columnSub.equals(other.columnSub))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (linkRow == null) {
			if (other.linkRow != null)
				return false;
		} else if (!linkRow.equals(other.linkRow))
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
		if (tableSub == null) {
			if (other.tableSub != null)
				return false;
		} else if (!tableSub.equals(other.tableSub))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TableRelation [id=" + id + ", tableId=" + tableId + ", columnId=" + columnId + ", tableSub=" + tableSub
				+ ", columnSub=" + columnSub + ", relType=" + relType + ", linkRow=" + linkRow + "]";
	}

}
