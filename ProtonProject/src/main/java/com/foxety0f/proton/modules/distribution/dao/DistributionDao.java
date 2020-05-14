package com.foxety0f.proton.modules.distribution.dao;

import javax.sql.DataSource;

import com.foxety0f.proton.common.abstracts.AbstractDAO;

public class DistributionDao extends AbstractDAO implements IDistributionDao{

	public DistributionDao(DataSource dataSource) {
		super(dataSource);
	}

	
}
