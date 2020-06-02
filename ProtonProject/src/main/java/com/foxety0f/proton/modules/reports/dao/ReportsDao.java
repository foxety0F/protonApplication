package com.foxety0f.proton.modules.reports.dao;

import javax.sql.DataSource;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.modules.ProtonModules;

public class ReportsDao extends AbstractDAO implements IReportsDao {
	
	private static final ProtonModules thisModule = ProtonModules.REPORTS;

	public ReportsDao(DataSource dataSource) {
		super(dataSource);
	}

}
