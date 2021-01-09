package com.foxety0f.proton.h2database.domain;

import java.util.Locale;
import java.util.ResourceBundle;

public class Configuration {

    private String url;
    private String dbName;
    private String username;
    private String password;
    private String usernameReader;
    private String passwordReader;
    private Boolean hashPassword;
    private String queryStructure;
    private String queryIntegrity;

    public Configuration() {

	ResourceBundle rb = ResourceBundle.getBundle("h2database", Locale.ENGLISH);
	setUrl(rb.getString("h2.configuration.url"));
	setDbName(rb.getString("h2.configuration.dbName"));
	setUsername(rb.getString("h2.configuration.username"));
	setPassword(rb.getString("h2.configuration.password"));
	setUsernameReader(rb.getString("h2.configuration.reader.user"));
	setPasswordReader(rb.getString("h2.configuration.reader.password"));
	setHashPassword(rb.getString("h2.configuration.is.hash.password").equals("true") ? true : false);
	setQueryStructure(rb.getString("h2.configuration.query.structure"));
	setQueryIntegrity(rb.getString("h2.configuration.query.integrity"));
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
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

    public String getUsernameReader() {
	return usernameReader;
    }

    public void setUsernameReader(String usernameReader) {
	this.usernameReader = usernameReader;
    }

    public String getPasswordReader() {
	return passwordReader;
    }

    public void setPasswordReader(String passwordReader) {
	this.passwordReader = passwordReader;
    }

    public Boolean getHashPassword() {
	return hashPassword;
    }

    public void setHashPassword(Boolean hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getQueryStructure() {
	return queryStructure;
    }

    public void setQueryStructure(String queryStructure) {
	this.queryStructure = queryStructure;
    }

    public String getQueryIntegrity() {
	return queryIntegrity;
    }

    public void setQueryIntegrity(String queryIntegrity) {
	this.queryIntegrity = queryIntegrity;
    }

    public String getDbName() {
	return dbName;
    }

    public void setDbName(String dbName) {
	this.dbName = dbName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
	result = prime * result + ((hashPassword == null) ? 0 : hashPassword.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((passwordReader == null) ? 0 : passwordReader.hashCode());
	result = prime * result + ((queryIntegrity == null) ? 0 : queryIntegrity.hashCode());
	result = prime * result + ((queryStructure == null) ? 0 : queryStructure.hashCode());
	result = prime * result + ((url == null) ? 0 : url.hashCode());
	result = prime * result + ((username == null) ? 0 : username.hashCode());
	result = prime * result + ((usernameReader == null) ? 0 : usernameReader.hashCode());
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
	Configuration other = (Configuration) obj;
	if (dbName == null) {
	    if (other.dbName != null)
		return false;
	} else if (!dbName.equals(other.dbName))
	    return false;
	if (hashPassword == null) {
	    if (other.hashPassword != null)
		return false;
	} else if (!hashPassword.equals(other.hashPassword))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (passwordReader == null) {
	    if (other.passwordReader != null)
		return false;
	} else if (!passwordReader.equals(other.passwordReader))
	    return false;
	if (queryIntegrity == null) {
	    if (other.queryIntegrity != null)
		return false;
	} else if (!queryIntegrity.equals(other.queryIntegrity))
	    return false;
	if (queryStructure == null) {
	    if (other.queryStructure != null)
		return false;
	} else if (!queryStructure.equals(other.queryStructure))
	    return false;
	if (url == null) {
	    if (other.url != null)
		return false;
	} else if (!url.equals(other.url))
	    return false;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	if (usernameReader == null) {
	    if (other.usernameReader != null)
		return false;
	} else if (!usernameReader.equals(other.usernameReader))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Configuration [url=" + url + ", dbName=" + dbName + ", username=" + username + ", password=" + password
		+ ", usernameReader=" + usernameReader + ", passwordReader=" + passwordReader + ", hashPassword="
		+ hashPassword + ", queryStructure=" + queryStructure + ", queryIntegrity=" + queryIntegrity + "]";
    }

}
