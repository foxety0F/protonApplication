package com.foxety0f.proton.modules.reports.domain.configuration;

public class ValueType {

	private Integer id;
	private String name;
	private String description;
	private String expects;
	private String returnFormat;
	private String parseFormat;

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

	public String getExpects() {
		return expects;
	}

	public void setExpects(String expects) {
		this.expects = expects;
	}

	public String getReturnFormat() {
		return returnFormat;
	}

	public void setReturnFormat(String returnFormat) {
		this.returnFormat = returnFormat;
	}

	public String getParseFormat() {
		return parseFormat;
	}

	public void setParseFormat(String parseFormat) {
		this.parseFormat = parseFormat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expects == null) ? 0 : expects.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parseFormat == null) ? 0 : parseFormat.hashCode());
		result = prime * result + ((returnFormat == null) ? 0 : returnFormat.hashCode());
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
		ValueType other = (ValueType) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expects == null) {
			if (other.expects != null)
				return false;
		} else if (!expects.equals(other.expects))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parseFormat == null) {
			if (other.parseFormat != null)
				return false;
		} else if (!parseFormat.equals(other.parseFormat))
			return false;
		if (returnFormat == null) {
			if (other.returnFormat != null)
				return false;
		} else if (!returnFormat.equals(other.returnFormat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValueType [id=" + id + ", name=" + name + ", description=" + description + ", expects=" + expects
				+ ", returnFormat=" + returnFormat + ", parseFormat=" + parseFormat + "]";
	}

}
