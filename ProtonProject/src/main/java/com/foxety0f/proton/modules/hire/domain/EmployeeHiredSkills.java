package com.foxety0f.proton.modules.hire.domain;

public class EmployeeHiredSkills {

	private Integer skillId;
	private String value;
	private String purpose;
	private String skillName;
	private String skillDescription;
	private Integer skillMinScale;
	private Integer skillMaxScale;

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public Integer getSkillMinScale() {
		return skillMinScale;
	}

	public void setSkillMinScale(Integer skillMinScale) {
		this.skillMinScale = skillMinScale;
	}

	public Integer getSkillMaxScale() {
		return skillMaxScale;
	}

	public void setSkillMaxScale(Integer skillMaxScale) {
		this.skillMaxScale = skillMaxScale;
	}

	@Override
	public String toString() {
		return "EmployeeHiredSkills [skillId=" + skillId + ", value=" + value + ", purpose=" + purpose + ", skillName="
				+ skillName + ", skillDescription=" + skillDescription + ", skillMinScale=" + skillMinScale
				+ ", skillMaxScale=" + skillMaxScale + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + ((skillDescription == null) ? 0 : skillDescription.hashCode());
		result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
		result = prime * result + ((skillMaxScale == null) ? 0 : skillMaxScale.hashCode());
		result = prime * result + ((skillMinScale == null) ? 0 : skillMinScale.hashCode());
		result = prime * result + ((skillName == null) ? 0 : skillName.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		EmployeeHiredSkills other = (EmployeeHiredSkills) obj;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (skillDescription == null) {
			if (other.skillDescription != null)
				return false;
		} else if (!skillDescription.equals(other.skillDescription))
			return false;
		if (skillId == null) {
			if (other.skillId != null)
				return false;
		} else if (!skillId.equals(other.skillId))
			return false;
		if (skillMaxScale == null) {
			if (other.skillMaxScale != null)
				return false;
		} else if (!skillMaxScale.equals(other.skillMaxScale))
			return false;
		if (skillMinScale == null) {
			if (other.skillMinScale != null)
				return false;
		} else if (!skillMinScale.equals(other.skillMinScale))
			return false;
		if (skillName == null) {
			if (other.skillName != null)
				return false;
		} else if (!skillName.equals(other.skillName))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
