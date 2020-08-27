package com.foxety0f.proton.modules.reports.domain.configuration;

public class RoleThreads {

	private Integer id;
	private Integer roleId;
	private Integer threadId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((threadId == null) ? 0 : threadId.hashCode());
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
		RoleThreads other = (RoleThreads) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (threadId == null) {
			if (other.threadId != null)
				return false;
		} else if (!threadId.equals(other.threadId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleThreads [id=" + id + ", roleId=" + roleId + ", threadId=" + threadId + "]";
	}

}
