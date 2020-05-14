package com.foxety0f.proton.modules.admin.domain;

import com.foxety0f.proton.modules.ProtonModules;

public class AdminModuleInformation {

	private ProtonModules module;
	private String url;
	private String road;
	private String description;
	private Boolean isActive;
	private Integer idRow;

	public ProtonModules getModule() {
		return module;
	}

	public void setModule(ProtonModules module) {
		this.module = module;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	@Override
	public String toString() {
		return "AdminModuleInformation [module=" + module + ", url=" + url + ", road=" + road + ", description="
				+ description + ", isActive=" + isActive + ", idRow=" + idRow + "]";
	}

}
