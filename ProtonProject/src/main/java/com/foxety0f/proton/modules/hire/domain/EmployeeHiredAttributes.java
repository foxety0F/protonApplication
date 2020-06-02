package com.foxety0f.proton.modules.hire.domain;

import java.util.List;

public class EmployeeHiredAttributes {

	private String about;
	private String userPhone;
	private List<EmployeeHiredExperience> experience;
	private List<EmployeeHiredSkills> skills;
	private Boolean isCurrentUser;

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public List<EmployeeHiredExperience> getExperience() {
		return experience;
	}

	public void setExperience(List<EmployeeHiredExperience> experience) {
		this.experience = experience;
	}

	public List<EmployeeHiredSkills> getSkills() {
		return skills;
	}

	public void setSkills(List<EmployeeHiredSkills> skills) {
		this.skills = skills;
	}

	public Boolean getIsCurrentUser() {
		return isCurrentUser;
	}

	public void setIsCurrentUser(Boolean isCurrentUser) {
		this.isCurrentUser = isCurrentUser;
	}

	@Override
	public String toString() {
		return "EmployeeHiredAttributes [about=" + about + ", userPhone=" + userPhone + ", experience=" + experience
				+ ", skills=" + skills + ", isCurrentUser=" + isCurrentUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result + ((experience == null) ? 0 : experience.hashCode());
		result = prime * result + ((isCurrentUser == null) ? 0 : isCurrentUser.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		result = prime * result + ((userPhone == null) ? 0 : userPhone.hashCode());
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
		EmployeeHiredAttributes other = (EmployeeHiredAttributes) obj;
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (isCurrentUser == null) {
			if (other.isCurrentUser != null)
				return false;
		} else if (!isCurrentUser.equals(other.isCurrentUser))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		if (userPhone == null) {
			if (other.userPhone != null)
				return false;
		} else if (!userPhone.equals(other.userPhone))
			return false;
		return true;
	}

}
