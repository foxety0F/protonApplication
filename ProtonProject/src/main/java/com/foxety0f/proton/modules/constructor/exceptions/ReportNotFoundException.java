package com.foxety0f.proton.modules.constructor.exceptions;

public class ReportNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5350801094946982202L;

	private Integer id;
	private String user;

	public Integer getId() {
		return id;
	}

	public String getUser() {
		return user;
	}
	
	public ReportNotFoundException(Integer id, String user) {
		super("Report " + id + " can not be found. Requestor user - " + user);
		this.id = id;
		this.user = user;
	}
}
