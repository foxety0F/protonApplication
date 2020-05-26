package com.foxety0f.proton.modules.reports.service;

import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.reports.dao.IReportsDao;

public class ReportsService implements IReportsService{

	private ProtonModules thisModule = ProtonModules.REPORTS;

	public ProtonModules getThisModule() {
		return thisModule;
	}
	
	private IReportsDao reportsDao;
	
	public ReportsService(IReportsDao reportsDao) {
		this.reportsDao = reportsDao;
	}

}
