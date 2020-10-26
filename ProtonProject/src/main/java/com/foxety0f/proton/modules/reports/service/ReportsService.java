package com.foxety0f.proton.modules.reports.service;

import java.util.List;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.reports.dao.IReportsDao;
import com.foxety0f.proton.modules.reports.domain.MetaColumnDict;
import com.foxety0f.proton.modules.reports.domain.MetaColumnType;
import com.foxety0f.proton.modules.reports.domain.MetaColumns;
import com.foxety0f.proton.modules.reports.domain.MetaDatabases;
import com.foxety0f.proton.modules.reports.domain.MetaDatabasesNames;
import com.foxety0f.proton.modules.reports.domain.MetaTables;
import com.foxety0f.proton.modules.reports.domain.MetaTablesRelations;
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

public class ReportsService implements IReportsService {

    private ProtonModules thisModule = ProtonModules.REPORTS;

    public ProtonModules getThisModule() {
	return thisModule;
    }

    private IReportsDao reportsDao;

    public ReportsService(IReportsDao reportsDao) {
	this.reportsDao = reportsDao;
    }

    @Override
    public void createNewDatabaseType(String name, UserDetailsProton user) throws DatabaseTypeExistException {
	reportsDao.createNewDatabaseType(name, user);

    }

    @Override
    public void createNewDatabaseConnection(Integer dataType, String url, String userName, String password,
	    String driverName, String testSql, String databaseName, UserDetailsProton user)
	    throws DatabaseConnectionAlreadyExistException, DatabasePasswordMissingException,
	    DatabaseUserMissingException {
	reportsDao.createNewDatabaseConnection(dataType, url, userName, password, driverName, testSql, databaseName,
		user);

    }

    @Override
    public void createNewThread(Integer databaseId, String name, String description, UserDetailsProton user)
	    throws ThreadAlreadyExistException, ThreadDatabaseIdMissingException, ThreadNameMissingException {
	reportsDao.createNewThread(databaseId, name, description, user);

    }

    @Override
    public void updateDatabaseConnection(Integer id, Integer dataType, String url, String userName, String password,
	    String driverName, String testSql, UserDetailsProton user) throws DatabaseUpdateNullIDException,
	    DatabaseUserMissingException, DatabasePasswordMissingException, DatabaseNotFoundException {
	reportsDao.updateDatabaseConnection(id, dataType, url, userName, password, driverName, testSql, user);

    }

    @Override
    public void updateThread(Integer id, Integer databaseId, String name, String description, UserDetailsProton user)
	    throws ThreadAlreadyExistException, ThreadDatabaseIdMissingException, ThreadNameMissingException,
	    ThreadIdMissingException {
	reportsDao.updateThread(id, databaseId, name, description, user);

    }

    @Override
    public List<MetaDatabases> getDatabases() {
	return reportsDao.getDatabases();
    }

    @Override
    public List<MetaDatabases> getDatabases(Integer idDatabase) {
	return reportsDao.getDatabases(idDatabase);
    }

    @Override
    public List<MetaDatabasesNames> getDatabasesNames() {
	return reportsDao.getDatabasesNames();
    }

    @Override
    public List<MetaThreads> getThreads() {
	return reportsDao.getThreads();
    }

    @Override
    public List<MetaThreadTablesMap> getThreadsTablesMap() {
	return reportsDao.getThreadsTablesMap();
    }

    @Override
    public List<MetaTables> getTables() {
	return reportsDao.getTables();
    }

    @Override
    public List<MetaTables> getTablesDatabase(Integer idDatabase) {
	return reportsDao.getTablesDatabase(idDatabase);
    }

    @Override
    public List<MetaTables> getTablesTable(Integer idTable) {
	return reportsDao.getTablesTable(idTable);
    }

    @Override
    public List<MetaColumns> getColumns() {
	return reportsDao.getColumns();
    }

    @Override
    public List<MetaColumnType> getColumnTypes() {
	return reportsDao.getColumnTypes();
    }

    @Override
    public List<MetaColumnDict> getColumnsDict() {
	return reportsDao.getColumnsDict();
    }

    @Override
    public void updateTableList(Integer idDatabase, UserDetailsProton user, Boolean isFillColumns) {
	reportsDao.updateTableList(idDatabase, user, isFillColumns);

    }

    @Override
    public List<MetaDatabases> getDatabaseFromTableId(Integer idTable) {
	return reportsDao.getDatabaseFromTableId(idTable);
    }

    @Override
    public void fillTablesByDatabase(Integer idDatabase, Boolean setActive, UserDetailsProton user) {
	reportsDao.fillTablesByDatabase(idDatabase, setActive, user);

    }

    @Override
    public void fillTablesByTable(Integer idTable, Boolean setActive, UserDetailsProton user) {
	reportsDao.fillTablesByTable(idTable, setActive, user);

    }

    @Override
    public void updateColumnList(List<MetaTables> tables, MetaDatabases dt, Boolean setActive, UserDetailsProton user) {
	reportsDao.updateColumnList(tables, dt, setActive, user);

    }

    @Override
    public void updateColumnInfo(Integer id, Integer columnId, String param, Object columnValue, UserDetailsProton user)
	    throws SchemaAndTableMissingException, ColumnMissingException, ColumnIdUpdateException,
	    TableIdUpdateException {
	reportsDao.updateColumnInfo(id, columnId, param, columnValue, user);

    }

    @Override
    public void updateTableInfo(Integer id, Integer idDatabase, String field, Object value, UserDetailsProton user)
	    throws DatabaseNotFoundException, TableIdUpdateException, DatabaseIdUpdateException {
	reportsDao.updateTableInfo(id, idDatabase, field, value, user);

    }

    public List<MetaColumns> getColumns(Integer tableId) {
	return reportsDao.getColumns(tableId);
    }

    @Override
    public Integer getTablesNow() {
	return reportsDao.getTablesNow();
    }

    @Override
    public Integer getColumnsNow() {
	return reportsDao.getColumnsNow();
    }

    @Override
    public Integer getColumnsCount() {
	return reportsDao.getColumnsCount();
    }

    @Override
    public Integer getTablesCount() {
	return reportsDao.getTablesCount();
    }

    @Override
    public List<MetaThreads> getThreads(Integer database) {
	return reportsDao.getThreads(database);
    }

    @Override
    public List<MetaTablesRelations> getTablesRelations(Integer tableId) {
	return reportsDao.getTablesRelations(tableId);
    }

    @Override
    public void setNewRelation(Integer idColumn, Integer idColumnSup, String name, String description, Boolean isActive,
	    UserDetailsProton user) throws Exception {
	reportsDao.setNewRelation(idColumn, idColumnSup, name, description, isActive, user);
    }

    @Override
    public void updateRelation(final Integer id, final String columnName, final Object value, UserDetailsProton user)
	    throws Exception {
	reportsDao.updateRelation(id, columnName, value, user);
    }
}
