package com.foxety0f.proton.web;

import java.io.Serializable;
import java.util.Date;

public class JwtInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3511680792395767454L;
	
	public final Date createdDate;
	public final String token;
	public final Date expiredDate;
	
	public JwtInfo(Date createdDate, String token, Date expiredDate) {
		this.createdDate = createdDate;
		this.token = token;
		this.expiredDate = expiredDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getToken() {
		return token;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

}
