package com.foxety0f.proton.modules.quality.service;

import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.quality.dao.IQualityDAO;

public class QualityService implements IQualityService {

	private IQualityDAO qualityDao;

	private ProtonModules thisModule = ProtonModules.QUALITY;

	public ProtonModules getThisModule() {
		return thisModule;
	}

	public QualityService(IQualityDAO qualityDao) {
		this.qualityDao = qualityDao;
	}

}
