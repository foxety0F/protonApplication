package com.foxety0f.proton.modules.reports.dao;

import javax.sql.DataSource;

import com.foxety0f.proton.common.abstracts.AbstractDAO;

public class ReportsDao extends AbstractDAO implements IReportsDao {

	public ReportsDao(DataSource dataSource) {
		super(dataSource);
	}

}
