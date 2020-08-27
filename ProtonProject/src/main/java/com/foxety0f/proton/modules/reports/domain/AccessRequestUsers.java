package com.foxety0f.proton.modules.reports.domain;

import java.util.Date;

public class AccessRequestUsers {

	private Integer id;
	private Integer requestId;
	private Long userId;
	private Boolean isConfirmed;
	private Date cDate;
	private Date uDate;
	private Integer accessUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
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

	public Integer getAccessUser() {
		return accessUser;
	}

	public void setAccessUser(Integer accessUser) {
		this.accessUser = accessUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessUser == null) ? 0 : accessUser.hashCode());
		result = prime * result + ((cDate == null) ? 0 : cDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isConfirmed == null) ? 0 : isConfirmed.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((uDate == null) ? 0 : uDate.hashCode());
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
		AccessRequestUsers other = (AccessRequestUsers) obj;
		if (accessUser == null) {
			if (other.accessUser != null)
				return false;
		} else if (!accessUser.equals(other.accessUser))
			return false;
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
		if (isConfirmed == null) {
			if (other.isConfirmed != null)
				return false;
		} else if (!isConfirmed.equals(other.isConfirmed))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
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
		return true;
	}

	@Override
	public String toString() {
		return "AccessRequestUsers [id=" + id + ", requestId=" + requestId + ", userId=" + userId + ", isConfirmed="
				+ isConfirmed + ", cDate=" + cDate + ", uDate=" + uDate + ", accessUser=" + accessUser + "]";
	}

}
