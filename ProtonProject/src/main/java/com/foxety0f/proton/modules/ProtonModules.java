package com.foxety0f.proton.modules;


/**
 * This Enum present modules names in Proton Application
 * */
public enum ProtonModules {
	  /**
	   * AdminModule, infinity id 999
	   * */
	  ADMIN(999, "ADMIN"),
	  /**
	   * ConstructorModule - head of Proton Application
	   * */
	 CONSTRUCTOR(100, "CONSTRUCTOR"),
	  /**
	   * DistributionModule - using for control employees work time. Based on EmployeesModule.
	   * @see EmployeesModule
	   * */
	 DISTRIBUTION(101, "DISTRIBUTION"),
	  /**
	   * EmployeesModule - using for control employees information
	   * */
	 EMPLOYEES(102, "EMPLOYEES"),
	  /**
	   * QualityModule - using for control employees knowledge and skills
	   * */
	 QUALITY(103, "QUALITY"),
	  /**
	   * ReportsModule - using for monitoring business significant indicators
	   * */
	 REPORTS(104, "REPORTS"),
	  /**
	   * RestfulModule - using for create/modify rest-api. Based on ConstructorModule.
	   * @see ConstructorModule
	   * */
	 RESFULL(105, "RESTFULL"),
	  /**
	   * RolesModule - using for add/modify/delete roles to users and delimit access. Based on AdminModule.
	   * @see AdminModule
	   * */
	 ROLES(106, "ROLES"),
	  /**
	   * TestsModule - using for check employees knowledge and skills
	   * */
	 TESTS(107, "TESTS"),
	  /**
	   * HelpModule - using for create/modify native hints on pages.
	   * */
	 HELP(108, "HELP"), 
	  /**
	   * HiringModule - using for internal Head Hunting
	   * */
	 HIRING(109, "HIRING");

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
