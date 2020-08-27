package com.foxety0f.proton.modules.reports.domain.configuration;

public class RestCycle {

	private Integer id;
	private Integer restId;
	private Integer cycleDuration;
	private Integer cycleTimes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cycleDuration == null) ? 0 : cycleDuration.hashCode());
		result = prime * result + ((cycleTimes == null) ? 0 : cycleTimes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((restId == null) ? 0 : restId.hashCode());
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
		RestCycle other = (RestCycle) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (restId == null) {
			if (other.restId != null)
				return false;
		} else if (!restId.equals(other.restId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestCycle [id=" + id + ", restId=" + restId + ", cycleDuration=" + cycleDuration + ", cycleTimes="
				+ cycleTimes + "]";
	}

}
