package com.foxety0f.proton.modules.distribution.service;

import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.distribution.dao.IDistributionDao;

public class DistributionService implements IDistributionService{

	private ProtonModules thisModule = ProtonModules.DISTRIBUTION;

	public ProtonModules getThisModule() {
		return thisModule;
	}
	
	private IDistributionDao distributionDao;
	
	public DistributionService(IDistributionDao distributionDao) {
		this.distributionDao = distributionDao;
	}
	
}
