package com.foxety0f.proton.h2database;

import javax.sql.DataSource;

import com.foxety0f.proton.h2database.domain.Configuration;

public class InitializeH2Database {

    DataSource H2dataSource;
    
    static final String JDBC_DRIVER = "org.h2.Driver";
    
    private Configuration config;
    
    public InitializeH2Database() {
	 this.config = new Configuration();
    }

    public DataSource getH2dataSource() {
	return H2dataSource;
    }
}
