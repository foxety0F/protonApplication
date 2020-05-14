package com.foxety0f.proton.common.user;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticatedUserLog {

	private Long tsAuth;
	private Long tsLastAction;
	private String userName;
	private Long userId;
	private List<GrantedAuthority> grantList;

	public Long getTsAuth() {
		return tsAuth;
	}

	public void setTsAuth(Long tsAuth) {
		this.tsAuth = tsAuth;
	}

	public Long getTsLastAction() {
		return tsLastAction;
	}

	public void setTsLastAction(Long tsLastAction) {
		this.tsLastAction = tsLastAction;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<GrantedAuthority> getGrantList() {
		return grantList;
	}

	public void setGrantList(List<GrantedAuthority> grantList) {
		this.grantList = grantList;
	}

	@Override
	public String toString() {
		return "AuthenticatedUserLog [tsAuth=" + tsAuth + ", tsLastAction=" + tsLastAction + ", userName=" + userName
				+ ", userId=" + userId + ", grantList=" + grantList + "]";
	}

}
