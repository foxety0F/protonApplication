package com.foxety0f.proton.modules.reports.service;

import java.util.List;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.reports.domain.MetaColumnDict;
import com.foxety0f.proton.modules.reports.domain.MetaColumnType;
import com.foxety0f.proton.modules.reports.domain.MetaColumns;
import com.foxety0f.proton.modules.reports.domain.MetaDatabases;
import com.foxety0f.proton.modules.reports.domain.MetaDatabasesNames;
import com.foxety0f.proton.modules.reports.domain.MetaTables;
import com.foxety0f.proton.modules.reports.domain.MetaThreadTablesMap;
import com.foxety0f.proton.modules.reports.domain.MetaThreads;
import com.foxety0f.proton.modules.reports.exceptions.ColumnIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.ColumnMissingException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseConnectionAlreadyExistException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseNotFoundException;
import com.foxety0f.proton.modules.reports.exceptions.DatabasePasswordMissingException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseTypeExistException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseUpdateNullIDException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseUserMissingException;
import com.foxety0f.proton.modules.reports.exceptions.SchemaAndTableMissingException;
import com.foxety0f.proton.modules.reports.exceptions.TableIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.ThreadAlreadyExistException;
import com.foxety0f.proton.modules.reports.exceptions.ThreadDatabaseIdMissingException;
import com.foxety0f.proton.modules.reports.exceptions.ThreadIdMissingException;
import com.foxety0f.proton.modules.reports.exceptions.ThreadNameMissingException;

public interface IReportsService {
	void createNewDatabaseType(String name, UserDetailsProton user) throws DatabaseTypeExistException;

	void createNewDatabaseConnection(Integer dataType, String url, String userName, String password, String driverName,
			String testSql, String databaseName, UserDetailsProton user) throws DatabaseConnectionAlreadyExistException,
			DatabasePasswordMissingException, DatabaseUserMissingException;

	void createNewThread(Integer databaseId, String name, String description, UserDetailsProton user)
			throws ThreadAlreadyExistException, ThreadDatabaseIdMissingException, ThreadNameMissingException;

	void updateDatabaseConnection(Integer id, Integer dataType, String url, String userName, String password,
			String driverName, String testSql, UserDetailsProton user) throws DatabaseUpdateNullIDException,
			DatabaseUserMissingException, DatabasePasswordMissingException, DatabaseNotFoundException;

	void updateThread(Integer id, Integer databaseId, String name, String description, UserDetailsProton user)
			throws ThreadAlreadyExistException, ThreadDatabaseIdMissingException, ThreadNameMissingException,
			ThreadIdMissingException;

	List<MetaDatabases> getDatabases();

	List<MetaDatabases> getDatabases(Integer idDatabase);

	List<MetaDatabasesNames> getDatabasesNames();

	List<MetaThreads> getThreads();

	List<MetaThreadTablesMap> getThreadsTablesMap();

	List<MetaTables> getTables();

	List<MetaTables> getTablesDatabase(Integer idDatabase);

	List<MetaTables> getTablesTable(Integer idTable);

	List<MetaColumns> getColumns();

	List<MetaColumnType> getColumnTypes();

	List<MetaColumnDict> getColumnsDict();

	void updateTableList(Integer idDatabase, UserDetailsProton user, Boolean isFillColumns);

	List<MetaDatabases> getDatabaseFromTableId(Integer idTable);

	void fillTablesByDatabase(Integer idDatabase, Boolean setActive, UserDetailsProton user);

	void fillTablesByTable(Integer idTable, Boolean setActive, UserDetailsProton user);

	void updateColumnList(List<MetaTables> tables, MetaDatabases dt, Boolean setActive, UserDetailsProton user);

	void updateColumnInfo(final Integer id, final Integer columnId, final String param, final Object columnValue,
			final UserDetailsProton user) throws SchemaAndTableMissingException, ColumnMissingException,
			ColumnIdUpdateException, TableIdUpdateException;

	void updateTableInfo(final Integer id, final Integer idDatabase, final String field, final Object value,
			UserDetailsProton user) throws DatabaseNotFoundException, TableIdUpdateException, DatabaseIdUpdateException;

	List<MetaColumns> getColumns(Integer tableId);

	Integer getTablesNow();

	Integer getColumnsNow();

	Integer getColumnsCount();

	Integer getTablesCount();
}
