package com.foxety0f.proton.database;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseBeanFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseBeanFactory.class);

	public DataSource createDataSource(String name) {
		LOGGER.info("Initialized DatabaseBeanFactory");
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		DatabasePlaceHolder placeHolder = new DatabasePlaceHolder(name);

		try {
			dataSource.setDriverClass(placeHolder.getDriver());
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		LOGGER.info("DatabaseBeanFactory init jdbcURL:" + placeHolder.getUrl());

		dataSource.setJdbcUrl(placeHolder.getUrl());
		LOGGER.info("DatabaseBeanFactory init user:" + placeHolder.getUser());
		dataSource.setUser(placeHolder.getUser());
		dataSource.setPassword(placeHolder.getPassword());
		dataSource.setMaxPoolSize(placeHolder.getMaxPoolSize());
		dataSource.setMinPoolSize(placeHolder.getMinPoolSize());
		dataSource.setMaxIdleTime(30);
		dataSource.setTestConnectionOnCheckin(true);
		dataSource.setIdleConnectionTestPeriod(1);
		dataSource.setPreferredTestQuery(placeHolder.getTestQuery());

		return dataSource;
	}
}
