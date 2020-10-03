package com.foxety0f.proton.modules.reports.domain;

public enum ProtonTablesRelations {

	JOIN(100, "JOIN", "left join"),
	
	LEFT_JOIN (101, "LEFT_JOIN", "left join"),
	
	RIGTH_JOIN(102, "RIGHT_JOIN", "right join");
	
	private int i;
	private String name;
	private String description;
	
	ProtonTablesRelations(int i, String name, String description){
		this.i = i;
		this.name = name;
		this.description = description;
	}
	
	public int getI() {
		return i;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	
}
