package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class RestLogFlow {

	private Integer id;
	private Integer restId;
	private Integer state;
	private Date state_change_time;

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getState_change_time() {
		return state_change_time;
	}

	public void setState_change_time(Date state_change_time) {
		this.state_change_time = state_change_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((restId == null) ? 0 : restId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((state_change_time == null) ? 0 : state_change_time.hashCode());
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
		RestLogFlow other = (RestLogFlow) obj;
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
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (state_change_time == null) {
			if (other.state_change_time != null)
				return false;
		} else if (!state_change_time.equals(other.state_change_time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestLogFlow [id=" + id + ", restId=" + restId + ", state=" + state + ", state_change_time="
				+ state_change_time + "]";
	}

}
