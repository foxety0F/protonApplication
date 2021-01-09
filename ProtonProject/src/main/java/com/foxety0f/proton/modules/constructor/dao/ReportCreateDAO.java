package com.foxety0f.proton.modules.constructor.dao;

import javax.sql.DataSource;

import com.foxety0f.proton.common.abstracts.AbstractDAO;

public class ReportCreateDAO extends AbstractDAO{

    public ReportCreateDAO(DataSource dataSource) {
	super(dataSource);
    }
    
    private Integer getNextReportNum() {
	Integer seq = querySource.queryForObject("select nextval('public.\"PROTON_REPORTING_CONFIG_SEQ\"') ", EMPTY_PARAMS, Integer.class);
	return seq;
    }


    
}
