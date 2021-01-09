package com.foxety0f.proton.modules.reporting.dao;

import java.util.List;
import java.util.Map;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumnType;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumns;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemInformation;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemTypes;
import com.foxety0f.proton.modules.reporting.domain.ReportingTables;
import com.foxety0f.proton.modules.reporting.domain.ReportingThreads;
import com.foxety0f.proton.modules.reporting.domain.mappers.ColumnTypesMapping;

public interface IReportingAdminDAO {

    void loadSystemColumnTypeMapper();

    public void loadSystemTableMapper();

    public void loadConfigurations();

    public Map<String, Object> getConfigurations();

    public List<ReportingSystemInformation> getSystemInformation();

    public void addSystem(Integer systemType, String title, String description, String userName, String password,
	    String connectionConfig, UserDetailsProton user);

    public void markSystemInactive(Integer id, UserDetailsProton user);

    public void markSystemActive(Integer id, UserDetailsProton user);

    public void changeSystem(Integer id, Integer systemType, String title, String description, String userName,
	    String password, String connectionConfig, UserDetailsProton user);

    public ReportingSystemInformation getSystemInformation(Integer id);

    public List<ReportingTables> getTables();
    
    public List<ReportingTables> getTables(Integer idSystem);

    public ReportingTables getTable(Integer idTable);

    public List<ReportingColumns> getColumns();

    public List<ReportingSystemTypes> getMetaSystemTypes();

    public void setColumnType(String name, String description, UserDetailsProton user);

    public void markColumnTypeInactive(Integer id, UserDetailsProton user);

    public void markColumnTypeActive(Integer id, UserDetailsProton user);

    public void changeColumnType(Integer id, String name, String description, UserDetailsProton user);

    public List<ReportingColumnType> getMetaColumnTypes();

    public void createThread(Integer coreTableId, String title, String description, Boolean isNativeLang,
	    UserDetailsProton user);

    public void markThreadInactive(Integer id, UserDetailsProton user);

    public void markThreadActive(Integer id, UserDetailsProton user);

    public void updateThread(Integer id, Integer coreTableId, String title, String description, Boolean isNativeLang,
	    UserDetailsProton user);

    public List<ReportingThreads> getThreads();

    public void createColumnTypesMapping(Integer typeId, Integer systemId, String name, String value, String pattern,
	    UserDetailsProton user);

    public void updateColumnTypesMapping(Integer id, Integer typeId, Integer systemId, String name, String value,
	    String pattern, UserDetailsProton user);

    public List<ColumnTypesMapping> getColumnTypesMapping();

    public void fillTablesByDatabase(Integer id, UserDetailsProton user) throws Exception;

    public void fillColumnsByDatabase(Integer idSystem, UserDetailsProton user) throws Exception;
}
