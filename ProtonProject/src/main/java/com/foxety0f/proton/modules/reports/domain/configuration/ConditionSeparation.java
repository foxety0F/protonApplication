package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class ConditionSeparation {

	private Integer id;
	private Integer reportId;
	private Integer separationType;
	private Integer path;
	private Date cDate;
	private Date uDate;
	private Boolean isActive;
	private Integer originalId;
	private Integer version;
	private Long userId;

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

	public Integer getSeparationType() {
		return separationType;
	}

	public void setSeparationType(Integer separationType) {
		this.separationType = separationType;
	}

	public Integer getPath() {
		return path;
	}

	public void setPath(Integer path) {
		this.path = path;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Integer originalId) {
		this.originalId = originalId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((originalId == null) ? 0 : originalId.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
		result = prime * result + ((separationType == null) ? 0 : separationType.hashCode());
		result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		ConditionSeparation other = (ConditionSeparation) obj;
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
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (originalId == null) {
			if (other.originalId != null)
				return false;
		} else if (!originalId.equals(other.originalId))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		if (separationType == null) {
			if (other.separationType != null)
				return false;
		} else if (!separationType.equals(other.separationType))
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
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConditionSeparation [id=" + id + ", reportId=" + reportId + ", separationType=" + separationType
				+ ", path=" + path + ", cDate=" + cDate + ", uDate=" + uDate + ", isActive=" + isActive
				+ ", originalId=" + originalId + ", version=" + version + ", userId=" + userId + "]";
	}

}
