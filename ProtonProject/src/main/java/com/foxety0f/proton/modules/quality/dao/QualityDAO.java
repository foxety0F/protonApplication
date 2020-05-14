package com.foxety0f.proton.modules.quality.dao;

import javax.sql.DataSource;

import com.foxety0f.proton.common.abstracts.AbstractDAO;

public class QualityDAO extends AbstractDAO implements IQualityDAO{

	public QualityDAO(DataSource dataSource) {
		super(dataSource);
	}

}
