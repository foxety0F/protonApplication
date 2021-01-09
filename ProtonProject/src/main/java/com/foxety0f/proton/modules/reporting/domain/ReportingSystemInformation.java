package com.foxety0f.proton.modules.reporting.domain;

public class ReportingSystemInformation {

    private Integer id;
    private String connectionName;
    private String username;
    private String password;
    private String connectionConfig;
    private Integer systemType;
    private String systemName;
    private Boolean isActive;
    private String description;

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "ReportingSystemInformation [id=" + id + ", connectionName=" + connectionName + ", username=" + username
		+ ", password=" + password + ", connectionConfig=" + connectionConfig + ", systemType=" + systemType
		+ ", systemName=" + systemName + ", isActive=" + isActive + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((connectionConfig == null) ? 0 : connectionConfig.hashCode());
	result = prime * result + ((connectionName == null) ? 0 : connectionName.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
	result = prime * result + ((systemType == null) ? 0 : systemType.hashCode());
	result = prime * result + ((username == null) ? 0 : username.hashCode());
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
	ReportingSystemInformation other = (ReportingSystemInformation) obj;
	if (connectionConfig == null) {
	    if (other.connectionConfig != null)
		return false;
	} else if (!connectionConfig.equals(other.connectionConfig))
	    return false;
	if (connectionName == null) {
	    if (other.connectionName != null)
		return false;
	} else if (!connectionName.equals(other.connectionName))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (isActive == null) {
	    if (other.isActive != null)
		return false;
	} else if (!isActive.equals(other.isActive))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (systemName == null) {
	    if (other.systemName != null)
		return false;
	} else if (!systemName.equals(other.systemName))
	    return false;
	if (systemType == null) {
	    if (other.systemType != null)
		return false;
	} else if (!systemType.equals(other.systemType))
	    return false;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getConnectionName() {
	return connectionName;
    }

    public void setConnectionName(String connectionName) {
	this.connectionName = connectionName;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getConnectionConfig() {
	return connectionConfig;
    }

    public void setConnectionConfig(String connectionConfig) {
	this.connectionConfig = connectionConfig;
    }

    public Integer getSystemType() {
	return systemType;
    }

    public void setSystemType(Integer systemType) {
	this.systemType = systemType;
    }

    public String getSystemName() {
	return systemName;
    }

    public void setSystemName(String systemName) {
	this.systemName = systemName;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
    }

}
