package com.foxety0f.proton.modules.reports.domain.configuration;

public class Unface {

	private Integer id;
	private String name;
	private String description;
	private String method;
	private String replacementString;
	private Integer hashValue;
	private String encryptCode;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReplacementString() {
		return replacementString;
	}

	public void setReplacementString(String replacementString) {
		this.replacementString = replacementString;
	}

	public Integer getHashValue() {
		return hashValue;
	}

	public void setHashValue(Integer hashValue) {
		this.hashValue = hashValue;
	}

	public String getEncryptCode() {
		return encryptCode;
	}

	public void setEncryptCode(String encryptCode) {
		this.encryptCode = encryptCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((encryptCode == null) ? 0 : encryptCode.hashCode());
		result = prime * result + ((hashValue == null) ? 0 : hashValue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((replacementString == null) ? 0 : replacementString.hashCode());
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
		Unface other = (Unface) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (encryptCode == null) {
			if (other.encryptCode != null)
				return false;
		} else if (!encryptCode.equals(other.encryptCode))
			return false;
		if (hashValue == null) {
			if (other.hashValue != null)
				return false;
		} else if (!hashValue.equals(other.hashValue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (replacementString == null) {
			if (other.replacementString != null)
				return false;
		} else if (!replacementString.equals(other.replacementString))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Unface [id=" + id + ", name=" + name + ", description=" + description + ", method=" + method
				+ ", replacementString=" + replacementString + ", hashValue=" + hashValue + ", encryptCode="
				+ encryptCode + "]";
	}

}
