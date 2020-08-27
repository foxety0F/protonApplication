package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class LoadtimeStates {

	private Integer id;
	private Integer loadingId;
	private Integer status;
	private Date stateChangeTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoadingId() {
		return loadingId;
	}

	public void setLoadingId(Integer loadingId) {
		this.loadingId = loadingId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStateChangeTime() {
		return stateChangeTime;
	}

	public void setStateChangeTime(Date stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loadingId == null) ? 0 : loadingId.hashCode());
		result = prime * result + ((stateChangeTime == null) ? 0 : stateChangeTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		LoadtimeStates other = (LoadtimeStates) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loadingId == null) {
			if (other.loadingId != null)
				return false;
		} else if (!loadingId.equals(other.loadingId))
			return false;
		if (stateChangeTime == null) {
			if (other.stateChangeTime != null)
				return false;
		} else if (!stateChangeTime.equals(other.stateChangeTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoadtimeStates [id=" + id + ", loadingId=" + loadingId + ", status=" + status + ", stateChangeTime="
				+ stateChangeTime + "]";
	}

}
