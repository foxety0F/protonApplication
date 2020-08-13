package com.foxety0f.proton.modules.hire.domain;

public class HiredSkills {

	private Integer id;
	private String skillName;
	private String skillDescript;
	private Integer skillMinScale;
	private Integer skillMaxScale;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDescript() {
		return skillDescript;
	}

	public void setSkillDescript(String skillDescript) {
		this.skillDescript = skillDescript;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((skillDescript == null) ? 0 : skillDescript.hashCode());
		result = prime * result + ((skillMaxScale == null) ? 0 : skillMaxScale.hashCode());
		result = prime * result + ((skillMinScale == null) ? 0 : skillMinScale.hashCode());
		result = prime * result + ((skillName == null) ? 0 : skillName.hashCode());
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
		HiredSkills other = (HiredSkills) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (skillDescript == null) {
			if (other.skillDescript != null)
				return false;
		} else if (!skillDescript.equals(other.skillDescript))
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
		return true;
	}

	@Override
	public String toString() {
		return "HiredSkills [id=" + id + ", skillName=" + skillName + ", skillDescript=" + skillDescript
				+ ", skillMinScale=" + skillMinScale + ", skillMaxScale=" + skillMaxScale + "]";
	}

}
