package com.foxety0f.proton.modules.modules_config;

import com.foxety0f.proton.modules.ProtonModules;

public class Module {

	private ProtonModules module;
	private Boolean isActive;
	private Integer id;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public ProtonModules getModule() {
		return module;
	}

	public void setModule(ProtonModules module) {
		this.module = module;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Module [module=" + module + ", isActive=" + isActive + ", id=" + id + "]";
	}

}
