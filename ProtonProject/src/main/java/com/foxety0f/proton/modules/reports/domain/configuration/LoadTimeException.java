package com.foxety0f.proton.modules.reports.domain.configuration;

public class LoadTimeException {

	private Integer id;
	private Integer reportId;
	private Integer exceptionId;
	private Integer exceptionTitle;
	private Integer exceptionText;

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

	public Integer getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	public Integer getExceptionTitle() {
		return exceptionTitle;
	}

	public void setExceptionTitle(Integer exceptionTitle) {
		this.exceptionTitle = exceptionTitle;
	}

	public Integer getExceptionText() {
		return exceptionText;
	}

	public void setExceptionText(Integer exceptionText) {
		this.exceptionText = exceptionText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exceptionId == null) ? 0 : exceptionId.hashCode());
		result = prime * result + ((exceptionText == null) ? 0 : exceptionText.hashCode());
		result = prime * result + ((exceptionTitle == null) ? 0 : exceptionTitle.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
		LoadTimeException other = (LoadTimeException) obj;
		if (exceptionId == null) {
			if (other.exceptionId != null)
				return false;
		} else if (!exceptionId.equals(other.exceptionId))
			return false;
		if (exceptionText == null) {
			if (other.exceptionText != null)
				return false;
		} else if (!exceptionText.equals(other.exceptionText))
			return false;
		if (exceptionTitle == null) {
			if (other.exceptionTitle != null)
				return false;
		} else if (!exceptionTitle.equals(other.exceptionTitle))
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
		return true;
	}

	@Override
	public String toString() {
		return "LoadTimeException [id=" + id + ", reportId=" + reportId + ", exceptionId=" + exceptionId
				+ ", exceptionTitle=" + exceptionTitle + ", exceptionText=" + exceptionText + "]";
	}

}
