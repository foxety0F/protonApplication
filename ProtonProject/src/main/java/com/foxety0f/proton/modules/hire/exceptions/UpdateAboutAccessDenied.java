package com.foxety0f.proton.modules.hire.exceptions;

public class UpdateAboutAccessDenied extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4713746060840191352L;

    private Integer briefId;
    private Integer userId;
    private Long requestorId;

    public UpdateAboutAccessDenied(Integer briefId, Integer userId, Long requestorId) {
	super("Can not update information @briefId " + briefId + " because it's page @userId " + userId);
	this.briefId = briefId;
	this.requestorId = requestorId;
	this.userId = userId;
    }

    public Integer getBriefId() {
	return briefId;
    }

    public Integer getUserId() {
	return userId;
    }

    public Long getRequestorId() {
	return requestorId;
    }

}
