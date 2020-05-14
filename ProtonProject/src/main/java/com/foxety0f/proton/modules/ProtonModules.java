package com.foxety0f.proton.modules;

public enum ProtonModules {
	ADMIN(999, "ADMIN"),
	CONSTRUCTOR(100, "CONSTRUCTOR"),
	DISTRIBUTION(101, "DISTRIBUTION"),
	EMPLOYEES(102, "EMPLOYEES"),
	QUALITY(103, "QUALITY"),
	REPORTS(104, "REPORTS"),
	RESFULL(105, "RESTFULL"),
	ROLES(106, "ROLES"),
	TESTS(107, "TESTS"),
	HELP(108, "HELP");

	private int i;
	private String string;
	ProtonModules(int i, String string) {
		this.i = i;
		this.string = string;
	}
	
	public int moduleValue() {
		return this.i;
	}
	
	public String moduleName() {
		return this.string;
	}
	
	public String showInfo() {
		return "Module " + this.string + " ID is " + this.i;
	}
}
