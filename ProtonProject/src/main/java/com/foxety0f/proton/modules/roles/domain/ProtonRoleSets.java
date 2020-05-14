package com.foxety0f.proton.modules.roles.domain;

public class ProtonRoleSets {

	private Integer roleId;
	private Integer userId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ProtonRoleSets [roleId=" + roleId + ", userId=" + userId + "]";
	}

}
