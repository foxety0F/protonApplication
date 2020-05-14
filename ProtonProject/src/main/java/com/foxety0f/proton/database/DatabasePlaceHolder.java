package com.foxety0f.proton.database;

import java.util.Locale;
import java.util.ResourceBundle;

public class DatabasePlaceHolder {
	private String name;
	private String url;
	private String driver;
	private String user;
	private String password;
	private String testQuery;
	private Integer maxPoolSize;
	private Integer minPoolSize;
	private Integer timeOut;

	public DatabasePlaceHolder(String name) {
		this.name = name;

		ResourceBundle rb = ResourceBundle.getBundle("application", Locale.ENGLISH);
		this.url = rb.getString(name + ".database.url");
		this.driver = rb.getString(name + ".database.driver");
		this.user = rb.getString(name + ".database.user");
		this.password = rb.getString(name + ".database.password");
		this.testQuery = rb.getString(name + ".database.select");
		this.maxPoolSize = Integer.parseInt(rb.getString(name + ".database.max.pool.size"));
		this.minPoolSize = Integer.parseInt(rb.getString(name + ".database.min.pool.size"));
		this.timeOut = Integer.parseInt(rb.getString(name + ".database.query.timeout"));

	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getDriver() {
		return driver;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getTestQuery() {
		return testQuery;
	}

	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	public Integer getMinPoolSize() {
		return minPoolSize;
	}

	public Integer getTimeOut() {
		return timeOut;
	}
}
