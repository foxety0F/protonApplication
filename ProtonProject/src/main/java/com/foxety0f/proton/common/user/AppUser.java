package com.foxety0f.proton.common.user;

public class AppUser {

	private Long userId;
	private String userName;
	private String encryptedPassword;
	private boolean enabled;
	private String firstName;
	private String surname;
	private Boolean isFirstTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Boolean getIsFirstTime() {
		return isFirstTime;
	}

	public void setIsFirstTime(Boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

	@Override
	public String toString() {
		return "AppUser [userId=" + userId + ", userName=" + userName + ", encryptedPassword=" + encryptedPassword
				+ ", enabled=" + enabled + ", firstName=" + firstName + ", surname=" + surname + ", isFirstTime="
				+ isFirstTime + "]";
	}

}
