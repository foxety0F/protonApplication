package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class CustomQuery {

	private Integer id;
	private Integer reportId;
	private String query;
	private Date cDate;
	private Date uDate;

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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
		CustomQuery other = (CustomQuery) obj;
		if (cDate == null) {
			if (other.cDate != null)
				return false;
		} else if (!cDate.equals(other.cDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
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
		return "CustomQuery [id=" + id + ", reportId=" + reportId + ", query=" + query + ", cDate=" + cDate + ", uDate="
				+ uDate + "]";
	}

}
