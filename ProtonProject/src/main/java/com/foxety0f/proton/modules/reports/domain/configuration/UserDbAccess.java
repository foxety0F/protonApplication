package com.foxety0f.proton.modules.reports.domain.configuration;

public class UserDbAccess {

	private Integer id;
	private Integer userId;
	private Integer databaseId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((databaseId == null) ? 0 : databaseId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserDbAccess other = (UserDbAccess) obj;
		if (databaseId == null) {
			if (other.databaseId != null)
				return false;
		} else if (!databaseId.equals(other.databaseId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "UserDbAccess [id=" + id + ", userId=" + userId + ", databaseId=" + databaseId + "]";
	}

}
