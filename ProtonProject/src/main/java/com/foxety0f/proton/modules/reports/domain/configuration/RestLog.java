package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class RestLog {

	private Integer id;
	private String guid;
	private Integer userId;
	private Integer reportId;
	private Boolean isFinish;
	private Boolean isCycle;
	private Date creationDatatime;
	private Date lastUsedTime;
	private Date finishDatetime;
	private Integer cycleDuration;
	private Integer cycleTimes;
	private Boolean cycleConfirmed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Boolean getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

	public Boolean getIsCycle() {
		return isCycle;
	}

	public void setIsCycle(Boolean isCycle) {
		this.isCycle = isCycle;
	}

	public Date getCreationDatatime() {
		return creationDatatime;
	}

	public void setCreationDatatime(Date creationDatatime) {
		this.creationDatatime = creationDatatime;
	}

	public Date getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(Date lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	public Date getFinishDatetime() {
		return finishDatetime;
	}

	public void setFinishDatetime(Date finishDatetime) {
		this.finishDatetime = finishDatetime;
	}

	public Integer getCycleDuration() {
		return cycleDuration;
	}

	public void setCycleDuration(Integer cycleDuration) {
		this.cycleDuration = cycleDuration;
	}

	public Integer getCycleTimes() {
		return cycleTimes;
	}

	public void setCycleTimes(Integer cycleTimes) {
		this.cycleTimes = cycleTimes;
	}

	public Boolean getCycleConfirmed() {
		return cycleConfirmed;
	}

	public void setCycleConfirmed(Boolean cycleConfirmed) {
		this.cycleConfirmed = cycleConfirmed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDatatime == null) ? 0 : creationDatatime.hashCode());
		result = prime * result + ((cycleConfirmed == null) ? 0 : cycleConfirmed.hashCode());
		result = prime * result + ((cycleDuration == null) ? 0 : cycleDuration.hashCode());
		result = prime * result + ((cycleTimes == null) ? 0 : cycleTimes.hashCode());
		result = prime * result + ((finishDatetime == null) ? 0 : finishDatetime.hashCode());
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isCycle == null) ? 0 : isCycle.hashCode());
		result = prime * result + ((isFinish == null) ? 0 : isFinish.hashCode());
		result = prime * result + ((lastUsedTime == null) ? 0 : lastUsedTime.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
		RestLog other = (RestLog) obj;
		if (creationDatatime == null) {
			if (other.creationDatatime != null)
				return false;
		} else if (!creationDatatime.equals(other.creationDatatime))
			return false;
		if (cycleConfirmed == null) {
			if (other.cycleConfirmed != null)
				return false;
		} else if (!cycleConfirmed.equals(other.cycleConfirmed))
			return false;
		if (cycleDuration == null) {
			if (other.cycleDuration != null)
				return false;
		} else if (!cycleDuration.equals(other.cycleDuration))
			return false;
		if (cycleTimes == null) {
			if (other.cycleTimes != null)
				return false;
		} else if (!cycleTimes.equals(other.cycleTimes))
			return false;
		if (finishDatetime == null) {
			if (other.finishDatetime != null)
				return false;
		} else if (!finishDatetime.equals(other.finishDatetime))
			return false;
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isCycle == null) {
			if (other.isCycle != null)
				return false;
		} else if (!isCycle.equals(other.isCycle))
			return false;
		if (isFinish == null) {
			if (other.isFinish != null)
				return false;
		} else if (!isFinish.equals(other.isFinish))
			return false;
		if (lastUsedTime == null) {
			if (other.lastUsedTime != null)
				return false;
		} else if (!lastUsedTime.equals(other.lastUsedTime))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
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
		return "RestLog [id=" + id + ", guid=" + guid + ", userId=" + userId + ", reportId=" + reportId + ", isFinish="
				+ isFinish + ", isCycle=" + isCycle + ", creationDatatime=" + creationDatatime + ", lastUsedTime="
				+ lastUsedTime + ", finishDatetime=" + finishDatetime + ", cycleDuration=" + cycleDuration
				+ ", cycleTimes=" + cycleTimes + ", cycleConfirmed=" + cycleConfirmed + "]";
	}

}
