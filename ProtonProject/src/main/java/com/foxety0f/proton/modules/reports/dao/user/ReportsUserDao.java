package com.foxety0f.proton.modules.reports.dao.user;

import javax.sql.DataSource;

import com.foxety0f.proton.common.abstracts.AbstractDAO;

public class ReportsUserDao extends AbstractDAO{

	DataSource dataSource;
	
	public ReportsUserDao(DataSource dataSource) {
		super(dataSource);
		this.dataSource = dataSource;
	}
	
	
}
