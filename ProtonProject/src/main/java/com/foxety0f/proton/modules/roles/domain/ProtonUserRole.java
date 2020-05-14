package com.foxety0f.proton.modules.roles.domain;

public class ProtonUserRole {

	private Integer idRole;
	private String roleName;
	private Long userId;
	private String username;
	private Boolean hasRole;

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getHasRole() {
		return hasRole;
	}

	public void setHasRole(Boolean hasRole) {
		this.hasRole = hasRole;
	}

	@Override
	public String toString() {
		return "ProtonUserRole [idRole=" + idRole + ", roleName=" + roleName + ", userId=" + userId + ", username="
				+ username + ", hasRole=" + hasRole + "]";
	}

}
