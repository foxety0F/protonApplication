package com.foxety0f.proton.modules.help.domain;

/**
 * This class using for present information on page
 * <br> helpId - unique identificator for help
 * <br> helpName - name of help, shows on bootbox title
 * <br> helpDescription - description of help, internal information
 * <br> url - value of request url which call help
 * <br> helpText - help content, saved as is with html-tags
 */
public class HelpInformation {

	private Integer helpId;
	private String helpName;
	private String helpDescription;
	private String url;
	private String helpText;

	public Integer getHelpId() {
		return helpId;
	}

	public void setHelpId(Integer helpId) {
		this.helpId = helpId;
	}

	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}

	public String getHelpDescription() {
		return helpDescription;
	}

	public void setHelpDescription(String helpDescription) {
		this.helpDescription = helpDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	@Override
	public String toString() {
		return "HelpInformation [helpId=" + helpId + ", helpName=" + helpName + ", helpDescription=" + helpDescription
				+ ", helpText=" + helpText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((helpDescription == null) ? 0 : helpDescription.hashCode());
		result = prime * result + ((helpId == null) ? 0 : helpId.hashCode());
		result = prime * result + ((helpName == null) ? 0 : helpName.hashCode());
		result = prime * result + ((helpText == null) ? 0 : helpText.hashCode());
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
		HelpInformation other = (HelpInformation) obj;
		if (helpDescription == null) {
			if (other.helpDescription != null)
				return false;
		} else if (!helpDescription.equals(other.helpDescription))
			return false;
		if (helpId == null) {
			if (other.helpId != null)
				return false;
		} else if (!helpId.equals(other.helpId))
			return false;
		if (helpName == null) {
			if (other.helpName != null)
				return false;
		} else if (!helpName.equals(other.helpName))
			return false;
		if (helpText == null) {
			if (other.helpText != null)
				return false;
		} else if (!helpText.equals(other.helpText))
			return false;
		return true;
	}

}
