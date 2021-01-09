package com.foxety0f.proton.modules.reporting.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.reporting.dao.IReportingAdminDAO;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumnType;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumns;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemInformation;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemTypes;
import com.foxety0f.proton.modules.reporting.domain.ReportingTables;
import com.foxety0f.proton.modules.reporting.domain.ReportingThreads;
import com.foxety0f.proton.modules.reporting.domain.mappers.ColumnTypesMapping;

public class ReportingConfigurationService  implements IReportingConfigurationService {
    
    private IReportingAdminDAO reportingAdminDao;
    
    
    public ReportingConfigurationService(IReportingAdminDAO reportingAdminDao) {
	this.reportingAdminDao = reportingAdminDao;
    }

    private Map<String, Object> configurations = new HashedMap<String, Object>();

    public void loadConfiguration() {
	reportingAdminDao.loadConfigurations();
	configurations = reportingAdminDao.getConfigurations();
    }
    
    public void loadSystemColumnTypeMapper() {
	reportingAdminDao.loadSystemColumnTypeMapper();
    }

    public void loadSystemTableMapper() {
	reportingAdminDao.loadSystemTableMapper();
    }

    public void loadConfigurations() {
	reportingAdminDao.loadConfigurations();
    }

    public Map<String, Object> getConfigurations(){
	return reportingAdminDao.getConfigurations();
    }

    public List<ReportingSystemInformation> getSystemInformation(){
	return reportingAdminDao.getSystemInformation();
    }

    public void addSystem(Integer systemType, String title, String description, String userName, String password,
	    String connectionConfig, UserDetailsProton user) {
	reportingAdminDao.addSystem(systemType, title, description, userName, password, connectionConfig, user);
    }

    public void markSystemInactive(Integer id, UserDetailsProton user) {
	reportingAdminDao.markSystemInactive(id, user);
    }

    public void markSystemActive(Integer id, UserDetailsProton user) {
	reportingAdminDao.markSystemActive(id, user);
    }

    public void changeSystem(Integer id, Integer systemType, String title, String description, String userName,
	    String password, String connectionConfig, UserDetailsProton user) {
	reportingAdminDao.changeSystem(id, systemType, title, description, userName, password, connectionConfig, user);
    }

    public ReportingSystemInformation getSystemInformation(Integer id) {
	return reportingAdminDao.getSystemInformation(id);
    }

    public List<ReportingTables> getTables(Integer idSystem){
	return reportingAdminDao.getTables(idSystem);
    }

    public ReportingTables getTable(Integer idTable) {
	return reportingAdminDao.getTable(idTable);
    }

    public List<ReportingColumns> getColumns(){
	return reportingAdminDao.getColumns();
    }

    public List<ReportingSystemTypes> getMetaSystemTypes(){
	return reportingAdminDao.getMetaSystemTypes();
    }

    public void setColumnType(String name, String description, UserDetailsProton user) {
	reportingAdminDao.setColumnType(name, description, user);
    }

    public void markColumnTypeInactive(Integer id, UserDetailsProton user) {
	reportingAdminDao.markColumnTypeInactive(id, user);
    }

    public void markColumnTypeActive(Integer id, UserDetailsProton user) {
	reportingAdminDao.markColumnTypeActive(id, user);
    }

    public void changeColumnType(Integer id, String name, String description, UserDetailsProton user) {
	reportingAdminDao.changeColumnType(id, name, description, user);
    }

    public List<ReportingColumnType> getMetaColumnTypes(){
	return reportingAdminDao.getMetaColumnTypes();
    }

    public void createThread(Integer coreTableId, String title, String description, Boolean isNativeLang,
	    UserDetailsProton user) {
	reportingAdminDao.createThread(coreTableId, title, description, isNativeLang, user);
    }

    public void markThreadInactive(Integer id, UserDetailsProton user) {
	reportingAdminDao.markThreadInactive(id, user);
    }

    public void markThreadActive(Integer id, UserDetailsProton user) {
	reportingAdminDao.markThreadActive(id, user);
    }

    public void updateThread(Integer id, Integer coreTableId, String title, String description, Boolean isNativeLang,
	    UserDetailsProton user) {
	reportingAdminDao.updateThread(id, coreTableId, title, description, isNativeLang, user);
    }

    public List<ReportingThreads> getThreads(){
	return reportingAdminDao.getThreads();
    }

    public void createColumnTypesMapping(Integer typeId, Integer systemId, String name, String value, String pattern,
	    UserDetailsProton user) {
	reportingAdminDao.createColumnTypesMapping(typeId, systemId, name, value, pattern, user);
    }

    public void updateColumnTypesMapping(Integer id, Integer typeId, Integer systemId, String name, String value,
	    String pattern, UserDetailsProton user) {
	reportingAdminDao.updateColumnTypesMapping(id, typeId, systemId, name, value, pattern, user);
    }

    public List<ColumnTypesMapping> getColumnTypesMapping(){
	return reportingAdminDao.getColumnTypesMapping();
    }

    public void fillTablesByDatabase(Integer id, UserDetailsProton user) throws Exception{
	reportingAdminDao.fillTablesByDatabase(id, user);
    }

    public void fillColumnsByDatabase(Integer idSystem, UserDetailsProton user) throws Exception{
	reportingAdminDao.fillColumnsByDatabase(idSystem, user);
    }

    public List<ReportingTables> getTables(){
	return reportingAdminDao.getTables();
    }
}
