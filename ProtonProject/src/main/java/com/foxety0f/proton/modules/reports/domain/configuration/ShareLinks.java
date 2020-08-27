package com.foxety0f.proton.modules.reports.domain.configuration;

import java.util.Date;

public class ShareLinks {

	private Integer id;
	private String name;
	private String provideTitle;
	private String provideDescription;
	private Date createDate;
	private Date expiredDate;
	private String link;
	private Integer provideId;
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvideTitle() {
		return provideTitle;
	}

	public void setProvideTitle(String provideTitle) {
		this.provideTitle = provideTitle;
	}

	public String getProvideDescription() {
		return provideDescription;
	}

	public void setProvideDescription(String provideDescription) {
		this.provideDescription = provideDescription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getProvideId() {
		return provideId;
	}

	public void setProvideId(Integer provideId) {
		this.provideId = provideId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((expiredDate == null) ? 0 : expiredDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((provideDescription == null) ? 0 : provideDescription.hashCode());
		result = prime * result + ((provideId == null) ? 0 : provideId.hashCode());
		result = prime * result + ((provideTitle == null) ? 0 : provideTitle.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		ShareLinks other = (ShareLinks) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (expiredDate == null) {
			if (other.expiredDate != null)
				return false;
		} else if (!expiredDate.equals(other.expiredDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (provideDescription == null) {
			if (other.provideDescription != null)
				return false;
		} else if (!provideDescription.equals(other.provideDescription))
			return false;
		if (provideId == null) {
			if (other.provideId != null)
				return false;
		} else if (!provideId.equals(other.provideId))
			return false;
		if (provideTitle == null) {
			if (other.provideTitle != null)
				return false;
		} else if (!provideTitle.equals(other.provideTitle))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShareLinks [id=" + id + ", name=" + name + ", provideTitle=" + provideTitle + ", provideDescription="
				+ provideDescription + ", createDate=" + createDate + ", expiredDate=" + expiredDate + ", link=" + link
				+ ", provideId=" + provideId + ", roleId=" + roleId + "]";
	}

}
