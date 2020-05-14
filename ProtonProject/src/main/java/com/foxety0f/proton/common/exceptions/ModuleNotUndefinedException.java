package com.foxety0f.proton.common.exceptions;

public class ModuleNotUndefinedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3203936528935342453L;

	private Object obj;

	public Object getObj() {
		return obj;
	}

	public ModuleNotUndefinedException(Object obj) {
		super("Error when searching for the module " + obj);
		this.obj = obj;
	}

}
