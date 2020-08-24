package com.foxety0f.proton.modules.reports.exceptions;

public class RelationMissingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733545379249541833L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public RelationMissingException(Integer id) {
		super("Relation with id " + id + " not found");
		this.id = id;
	}

}
