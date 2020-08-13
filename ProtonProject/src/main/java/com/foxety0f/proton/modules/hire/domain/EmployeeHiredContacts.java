package com.foxety0f.proton.modules.hire.domain;

public class EmployeeHiredContacts {

	private Integer id;
	private Integer contactId;
	private String socialIcon;
	private String socialHref;
	private String socialName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getSocialIcon() {
		return socialIcon;
	}

	public void setSocialIcon(String socialIcon) {
		this.socialIcon = socialIcon;
	}

	public String getSocialHref() {
		return socialHref;
	}

	public void setSocialHref(String socialHref) {
		this.socialHref = socialHref;
	}

	public String getSocialName() {
		return socialName;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	@Override
	public String toString() {
		return "EmployeeHiredContacts [id=" + id + ", contactId=" + contactId + ", socialIcon=" + socialIcon
				+ ", socialHref=" + socialHref + ", socialName=" + socialName + "]";
	}

}
