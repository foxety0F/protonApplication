package com.foxety0f.proton.modules.reports.domain.configuration;

public class CustomQueryConditionValue {

	private Integer id;
	private Integer reportId;
	private Integer condId;
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getCondId() {
		return condId;
	}

	public void setCondId(Integer condId) {
		this.condId = condId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condId == null) ? 0 : condId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		CustomQueryConditionValue other = (CustomQueryConditionValue) obj;
		if (condId == null) {
			if (other.condId != null)
				return false;
		} else if (!condId.equals(other.condId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomQueryConditionValue [id=" + id + ", reportId=" + reportId + ", condId=" + condId + ", value="
				+ value + "]";
	}

}
