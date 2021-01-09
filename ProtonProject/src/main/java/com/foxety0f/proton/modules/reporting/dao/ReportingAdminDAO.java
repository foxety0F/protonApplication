package com.foxety0f.proton.modules.reporting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.database.DatabaseBeanFactory;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumnType;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumns;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemInformation;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemTypes;
import com.foxety0f.proton.modules.reporting.domain.ReportingTables;
import com.foxety0f.proton.modules.reporting.domain.ReportingThreads;
import com.foxety0f.proton.modules.reporting.domain.mappers.ColumnTypesMapping;
import com.foxety0f.proton.modules.reporting.domain.meta.SourceMetaTable;
import com.foxety0f.proton.modules.reporting.utils.ReportingColumnsTransform;
import com.foxety0f.proton.utils.AESCryptography;

public class ReportingAdminDAO extends AbstractDAO implements IReportingAdminDAO {

    public ReportingAdminDAO(DataSource dataSource) {
	super(dataSource);
	queryStructure.put("PostgreSQL", "select table_schema schemaName, table_name tableName, table_type description"
		+ " from information_schema.tables where table_schema != 'pg_catalog' and table_schema != 'information_schema'");
	queryColumns.put("PostgreSQL",
		"select table_name, column_name, :rpcSystemId || '|' || table_name tablemapper, :rpcSourceId || '|' || data_type typemapper, null description, ordinal_position ordering "
			+ " from information_schema.columns\r\n"
			+ "where table_schema != 'pg_catalog' and table_schema != 'information_schema' order by table_name, ordinal_position asc");
	loadSystemColumnTypeMapper();
	loadSystemTableMapper();
    }

    private Map<String, Object> configurations = new HashedMap<String, Object>();
    ReportingColumnsTransform columnsUtil = new ReportingColumnsTransform();
    ProtonModules module = ProtonModules.CONSTRUCTOR;

    Map<String, String> queryStructure = new HashedMap<String, String>();
    Map<String, String> queryColumns = new HashedMap<String, String>();
    Map<String, Integer> systemColumnTypeMapper = new HashedMap<String, Integer>();
    Map<String, Integer> systemTableMapper = new HashedMap<String, Integer>();

    private static final String TABLE_META_INSERT = "INSERT INTO proton_reporting_meta_tables(table_name, description, system_id) VALUES(?, ?, ?) "
	    + " ON CONFLICT (table_name, system_id) DO NOTHING;";

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void loadSystemColumnTypeMapper() {
	systemColumnTypeMapper.clear();
	querySource.query("select system_id || '|' || value mapper, type_id  from proton_constructor_column_types_mppg",
		new RowMapper() {
		    @Override
		    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			systemColumnTypeMapper.put(rs.getString("mapper"), rs.getInt("type_id"));
			return null;
		    }
		});
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void loadSystemTableMapper() {
	systemTableMapper.clear();
	querySource.query("select system_id || '|' || table_name mapper, id  from proton_reporting_meta_tables",
		new RowMapper() {
		    @Override
		    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			systemTableMapper.put(rs.getString("mapper"), rs.getInt("id"));
			return null;
		    }
		});
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void loadConfigurations() {
	configurations.clear();
	querySource.query("select * from proton_reporting_config_global", new RowMapper() {

	    @Override
	    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		configurations.put(rs.getString("setting"),
			columnsUtil.transformByType(rs.getString("value"), rs.getInt("type")));
		return null;
	    }
	});
    }

    public Map<String, Object> getConfigurations() {
	return configurations;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    
    public List<ReportingSystemInformation> getSystemInformation() {
	return querySource.query("select ms.id\r\n" + "	 , ms.title\r\n" + "	 , ms.username\r\n"
		+ "	 , ms.password\r\n" + "	 , ms.connect_config\r\n" + "	 , ms.system_type\r\n"
		+ "	 , st.title system_title\r\n" + "	 , ms.is_active\r\n , ms.description"
		+ "  from proton_reporting_meta_systems ms\r\n"
		+ "  join proton_meta_system_type st on ms.system_type = st.id	\r\n" + " order by ms.id asc", new RowMapper() {

		    @Override
		    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReportingSystemInformation item = new ReportingSystemInformation();
			item.setId(rs.getInt("id"));
			item.setConnectionName(rs.getString("title"));
			item.setUsername(rs.getString("username"));
			item.setPassword(rs.getString("password"));
			item.setConnectionConfig(rs.getString("connect_config"));
			item.setSystemType(rs.getInt("system_type"));
			item.setSystemName(rs.getString("system_title"));
			item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
			item.setDescription(rs.getString("description"));
			return item;
		    }
		});
    }
    
    public void addSystem(Integer systemType, String title, String description, String userName, String password, String connectionConfig, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<String, Object>();
	map.put("title", title);
	map.put("description", description);
	map.put("systemType", systemType);
	map.put("userName", userName);
	map.put("password", password);
	map.put("connectionConfig", connectionConfig);
	
	String sql = "INSERT INTO proton_reporting_meta_systems(system_type, title, description, username, password, connect_config)"
		+ " VALUES(:systemType, :title, :description, :userName, :password, :connectionConfig)";
	
	querySource.update(sql, map);
	
	loggerWithUser(module, map, ProtonEssences.REPORTING_CREATE_SOURCE, user);
    }
    
    public void markSystemInactive(Integer id, UserDetailsProton user) {
	Map<String, Integer> map = new HashedMap<String, Integer>();
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_systems set is_active = 0 where id = :id", map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_MARK_AS_INACTIVE_SOURCE, user);
    }
    
    public void markSystemActive(Integer id, UserDetailsProton user) {
	Map<String, Integer> map = new HashedMap<String, Integer>();
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_systems set is_active = 1 where id = :id", map);
	
	loggerWithUser(module, map, ProtonEssences.REPORTING_MARK_AS_ACTIVE_SOURCE, user);
    }
    
    public void changeSystem(Integer id,Integer systemType, String title, String description, String userName, String password, String connectionConfig, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<String, Object>();
	map.put("title", title);
	map.put("description", description);
	map.put("systemType", systemType);
	map.put("userName", userName);
	map.put("password", password);
	map.put("connectionConfig", connectionConfig);
	map.put("id", id);
	
	String sql = "UPDATE proton_reporting_meta_systems "
		+ " set system_type = :systemType, "
		+ " title = :title, "
		+ " description = :description, "
		+ " username = :userName ,"
		+ " password = :password ,"
		+ " connection_config = :connectionConfig"
		+ " where id = :id";
    
	querySource.update(sql, map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_UPDATE_SOURCE, user);
    }
    

    public ReportingSystemInformation getSystemInformation(Integer id) {
	Map<String, Integer> map = new HashedMap<String, Integer>();
	map.put("id", id);

	return querySource.query(
		"select ms.id\r\n" + "	 , ms.title\r\n" + "	 , ms.username\r\n" + "	 , ms.password\r\n"
			+ "	 , ms.connect_config\r\n" + "	 , ms.system_type\r\n"
			+ "	 , st.title system_title\r\n" + "	 , ms.is_active\r\n"
			+ "  from proton_reporting_meta_systems ms\r\n"
			+ "  join proton_meta_system_type st on ms.system_type = st.id	\r\n" + " where ms.id = :id",
		map, new ResultSetExtractor<ReportingSystemInformation>() {

		    @Override
		    public ReportingSystemInformation extractData(ResultSet rs)
			    throws SQLException, DataAccessException {
			while (rs.next()) {
			    ReportingSystemInformation item = new ReportingSystemInformation();
			    item.setId(rs.getInt("id"));
			    item.setConnectionName(rs.getString("title"));
			    item.setUsername(rs.getString("username"));
			    item.setPassword(rs.getString("password"));
			    item.setConnectionConfig(rs.getString("connect_config"));
			    item.setSystemType(rs.getInt("system_type"));
			    item.setSystemName(rs.getString("system_title"));
			    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
			    return item;
			}
			return null;
		    }
		});
    }

    public List<ReportingTables> getTables() {
	return querySource.query("select * from proton_reporting_meta_tables order by id asc", new ReportingTablesRowMapper());
    }

    public List<ReportingTables> getTables(Integer idSystem) {
	Map<String, Integer> map = new HashedMap<String, Integer>();
	map.put("idSystem", idSystem);

	return querySource.query("select * from proton_reporting_meta_tables where system_id = :idSystem", map,
		new ReportingTablesRowMapper());
    }

    public ReportingTables getTable(Integer idTable) {
	Map<String, Integer> map = new HashedMap<String, Integer>();
	map.put("idTable", idTable);

	return querySource.query("select * from proton_reporting_meta_tables where id = :idTable", map,
		new ResultSetExtractor<ReportingTables>() {

		    @Override
		    public ReportingTables extractData(ResultSet rs) throws SQLException, DataAccessException {
			while (rs.next()) {
			    ReportingTables item = new ReportingTables();
			    item.setId(rs.getInt(1));
			    item.setSystemId(rs.getInt(2));
			    item.setTableName(rs.getString(3));
			    item.setBusinessName(rs.getString(4));
			    item.setDescription(rs.getString(5));
			    item.setBusinessDescription(rs.getString(6));
			    item.setIsActive(rs.getInt(7) == 1 ? true : false);
			    item.setcDate(rs.getDate(8));
			    item.setuDate(rs.getDate(9));
			    return item;
			}
			return null;
		    }
		});
    }

    /**
     * Class based on table proton_reporting_meta_tables
     */
    private static class ReportingTablesRowMapper implements RowMapper<ReportingTables> {

	@Override
	public ReportingTables mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingTables item = new ReportingTables();
	    item.setId(rs.getInt(1));
	    item.setSystemId(rs.getInt(2));
	    item.setTableName(rs.getString(3));
	    item.setBusinessName(rs.getString(4));
	    item.setDescription(rs.getString(5));
	    item.setBusinessDescription(rs.getString(6));
	    item.setIsActive(rs.getInt(7) == 1 ? true : false);
	    item.setcDate(rs.getDate(8));
	    item.setuDate(rs.getDate(9));
	    return item;
	}

    }

    public List<ReportingColumns> getColumns() {
	return querySource.query("select * from proton_reporting_meta_columns order by id asc, ordering asc", new ReportingColumnsRowMapper());
    }

    /**
     * Class based on table proton_reporting_meta_columns
     */
    private static class ReportingColumnsRowMapper implements RowMapper<ReportingColumns> {

	@Override
	public ReportingColumns mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingColumns item = new ReportingColumns();
	    item.setId(rs.getInt(1));
	    item.setTableId(rs.getInt(2));
	    item.setColumnTypeId(rs.getInt(3));
	    item.setColumnName(rs.getString(4));
	    item.setBusinessColumnName(rs.getString(5));
	    item.setDescription(rs.getString(6));
	    item.setBusinessDescription(rs.getString(7));
	    item.setIsDictVal(rs.getInt(8) == 1 ? true : false);
	    item.setOrdering(9);
	    item.setIsActive(rs.getInt(10) == 1 ? true : false);
	    item.setcDate(rs.getDate(11));
	    item.setuDate(rs.getDate(12));
	    return item;
	}

    }
    
    public List<ReportingSystemTypes> getMetaSystemTypes() {
	return querySource.query("select * from proton_meta_system_type", new ReportingSystemTypesRowMapper());
    }

    /**
     * Class based on table proton_meta_system_type
     */
    private static class ReportingSystemTypesRowMapper implements RowMapper<ReportingSystemTypes> {

	@Override
	public ReportingSystemTypes mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingSystemTypes item = new ReportingSystemTypes();
	    item.setId(rs.getInt(1));
	    item.setTitle(rs.getString(2));
	    item.setDescription(rs.getString(3));
	    item.setcDate(rs.getDate(4));
	    item.setuDate(rs.getDate(5));
	    return item;
	}

    }
    
    
    /**
     * 
     * */
    public void setColumnType(String name, String description, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<String, Object>();
	map.put("name", name);
	map.put("description", description);
	
	querySource.update("insert into proton_reporting_meta_column_types(name, description) values (:name, :description)", map);

	loggerWithUser(module, map, ProtonEssences.REPORTING_CREATE_NEW_COLUMN_TYPE, user);
    }
    
    /**
     * 
     * */
    public void markColumnTypeInactive(Integer id, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_column_types set is_active = 0 where id = :id", map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_MARK_AS_ACTIVE_COLUMN_TYPE, user);
    }
    
    /**
     * 
     * */
    public void markColumnTypeActive(Integer id, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_column_types set is_active = 1 where id = :id", map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_MARK_AS_INACTIVE_COLUMN_TYPE, user);
    }
    
    /**
     * 
     * */
    public void changeColumnType(Integer id, String name, String description, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<String, Object>();
	map.put("name", name);
	map.put("description", description);
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_column_types set name = :name, description = :description where id = :id", map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_UPDATE_COLUMN_TYPE, user);
    }
    
    public List<ReportingColumnType> getMetaColumnTypes() {
	return querySource.query("select * from proton_reporting_meta_column_types", new ReportingColumnTypeRowMapper());
    }

    /**
     * Class based on table proton_reporting_meta_column_types
     */
    private static class ReportingColumnTypeRowMapper implements RowMapper<ReportingColumnType> {

	@Override
	public ReportingColumnType mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingColumnType item = new ReportingColumnType();
	    item.setId(rs.getInt(1));
	    item.setName(rs.getString(2));
	    item.setDescription(rs.getString(3));
	    item.setIsActive(rs.getInt(4) == 1 ? true : false);
	    item.setcDate(rs.getDate(5));
	    item.setuDate(rs.getDate(6));
	    return item;
	}

    }
    
    public void createThread(Integer coreTableId, String title, String description, Boolean isNativeLang, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("coreTableId", coreTableId);
	map.put("title", title);
	map.put("description", description);
	map.put("isNativeLang", isNativeLang == true ? 1 : 0);
	
	String sql = "insert into proton_reporting_meta_threads(core_table_id, title, description, is_native_lang) "
		+ " values (:coreTableId, :title, :description, :isNativeLang)";
	
	querySource.update(sql, map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_CREATE_NEW_THREAD, user);
    }
    
    /**
     * 
     * */
    public void markThreadInactive(Integer id, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_threads set is_active = 0 where id = :id", map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_MARK_AS_INACTIVE_THREAD, user);
    }
    
    /**
     * 
     * */
    public void markThreadActive(Integer id, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("id", id);
	
	querySource.update("update proton_reporting_meta_threads set is_active = 1 where id = :id", map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_MARK_AS_ACTIVE_THREAD, user);
    }
    
    public void updateThread(Integer id, Integer coreTableId, String title, String description, Boolean isNativeLang, UserDetailsProton user) {

	Map<String, Object> map = new HashedMap<>();
	map.put("coreTableId", coreTableId);
	map.put("title", title);
	map.put("description", description);
	map.put("isNativeLang", isNativeLang == true ? 1 : 0);
	map.put("id", id);
	
	String sql = "update proton_reporting_meta_threads set"
		+ " coreTableId = :core_table_id, title = :title, description = :description, is_native_lang = :isNativeLang"
		+ " where id = :id";
	
	querySource.update(sql, map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_UPDATE_THREAD, user);
    
    }
    
    public List<ReportingThreads> getThreads(){
	return querySource.query("select * from proton_reporting_meta_threads", new ReportingThreadsRowMapper());
    }
    

    /**
     * Class based on table proton_reporting_meta_threads
     */
    private static class ReportingThreadsRowMapper implements RowMapper<ReportingThreads> {

	@Override
	public ReportingThreads mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingThreads item = new ReportingThreads();
	    item.setId(rs.getInt(1));
	    item.setCoreTableId(rs.getInt(2));
	    item.setTitle(rs.getString(3));
	    item.setDescription(rs.getString(4));
	    item.setIsNativeLang(rs.getInt(5) == 1 ? true : false);
	    item.setIsActive(rs.getInt(6) == 1 ? true : false);
	    item.setcDate(rs.getDate(7));
	    item.setuDate(rs.getDate(8));
	    return item;
	}

    }
    
    public void createColumnTypesMapping(Integer typeId, Integer systemId, String name, String value, String pattern, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("typeId", typeId);
	map.put("systemId", systemId);
	map.put("name", name);
	map.put("value", value);
	map.put("pattern", pattern);
	
	String sql = "insert into proton_constructor_column_types_mppg(type_id, system_id, name, value, pattern)"
		+ " values (:typeId, :systemId, :name, :value, :pattern)";
	
	querySource.update(sql, map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_CREATE_NEW_COLUMN_MAPPER, user);
    }
    
    public void updateColumnTypesMapping(Integer id, Integer typeId, Integer systemId, String name, String value, String pattern, UserDetailsProton user) {
	Map<String, Object> map = new HashedMap<>();
	map.put("typeId", typeId);
	map.put("systemId", systemId);
	map.put("name", name);
	map.put("value", value);
	map.put("pattern", pattern);
	map.put("id", id);
	
	String sql = "update proton_constructor_column_types_mppg set type_id = :typeId, system_id = :systemId, name = :name, value = :value, pattern = :pattern where id = :id";
	
	querySource.update(sql, map);
	loggerWithUser(module, map, ProtonEssences.REPORTING_UPDATE_COLUMN_MAPPER, user);
    }
    
    public List<ColumnTypesMapping> getColumnTypesMapping(){
	return querySource.query("select * from proton_constructor_column_types_mppg", new ColumnTypesMappingRowMapper());
    }
    
    private static class ColumnTypesMappingRowMapper implements RowMapper<ColumnTypesMapping> {

	@Override
	public ColumnTypesMapping mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ColumnTypesMapping item = new ColumnTypesMapping();
	    item.setId(rs.getInt(1));
	    item.setTypeId(rs.getInt(2));
	    item.setSystemId(rs.getInt(3));
	    item.setName(rs.getString(4));
	    item.setValue(rs.getString(5));
	    item.setPattern(rs.getString(6));
	    item.setcDate(rs.getDate(7));
	    item.setuDate(rs.getDate(8));
	    return item;
	}

    }

    public void fillTablesByDatabase(Integer id, UserDetailsProton user) throws Exception {
	Map<String, Integer> map = new HashedMap<String, Integer>();
	map.put("id", id);
	ReportingSystemInformation system = getSystemInformation(id);
	if (system.getSystemName().equals("PostgreSQL")) {
	    String query = queryStructure.get(system.getSystemName());

	    Connection connection = new DatabaseBeanFactory().createConnection(system.getUsername(),
		   AESCryptography.encrypt(system.getPassword()), system.getConnectionConfig());

	    try {
		PreparedStatement prst = null;
		String finalQuery = "";
		prst = connection.prepareStatement(query);
		ResultSet rs = prst.executeQuery();
		List<SourceMetaTable> tables = new ArrayList<SourceMetaTable>();
		while (rs.next()) {
		    SourceMetaTable table = new SourceMetaTable();
		    table.setSchema(rs.getString(1));
		    table.setTable(rs.getString(2));
		    table.setDescription(rs.getString(3));
		    table.setSystemId(id);

		    finalQuery += "INSERT INTO proton_reporting_meta_tables(table_name, description, system_id) VALUES('"
			    + rs.getString(2) + "', '" + rs.getString(3) + "', " + id + ") "
			    + " ON CONFLICT (table_name, system_id) DO NOTHING;";
		}
		connection.close();
		querySource.update(finalQuery, EMPTY_PARAMS);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
	loggerWithUser(module, map, ProtonEssences.REPORTING_UPDATE_SOURCE, user);
    }

    public void fillColumnsByDatabase(Integer idSystem, UserDetailsProton user) throws Exception {
	Map<String, Object> map = new HashedMap<String, Object>();
	map.put("rpcSystemId", idSystem);
	ReportingSystemInformation system = getSystemInformation(idSystem);
	LOGGER.info("Start load columns from system @id " + idSystem);

	if (system.getSystemName().equals("PostgreSQL")) {
	    String query = queryColumns.get(system.getSystemName());
	    query = query.replaceAll(":rpcSystemId", idSystem.toString());
	    query = query.replaceAll(":rpcSourceId", system.getSystemType().toString());

	    Connection connection = new DatabaseBeanFactory().createConnection(system.getUsername(),
		    AESCryptography.encrypt(system.getPassword()), system.getConnectionConfig());
	    
	    int cnt = 0;
	    try {
		PreparedStatement prst = null;
		String finalQuery = "";
		prst = connection.prepareStatement(query);
		ResultSet rs = prst.executeQuery();

		while (rs.next()) {
		    String tableMapperFind = rs.getString("table_name") + "" + rs.getString("column_name") + ""
			    + "tableMapper";
		    String typeMapperFind = rs.getString("table_name") + "" + rs.getString("column_name") + ""
			    + "typeMapper";
		    String columnName = rs.getString("table_name") + "" + rs.getString("column_name") + ""
			    + "columnName";
		    String description = rs.getString("table_name") + "" + rs.getString("column_name") + ""
			    + "description";
		    String ordering = rs.getString("table_name") + "" + rs.getString("column_name") + "" + "ordering";
		    map.put(tableMapperFind, systemTableMapper.get(rs.getString("tablemapper")));
		    map.put(typeMapperFind, systemColumnTypeMapper.get(rs.getString("typemapper")));
		    map.put(columnName, rs.getString("column_name"));
		    map.put(description, rs.getString("description"));
		    map.put(ordering, rs.getInt("ordering"));

		    finalQuery += "INSERT INTO proton_reporting_meta_columns(table_id, column_type_id, column_name, description, ordering)"
			    + "VALUES (:" + tableMapperFind + ", :" + typeMapperFind + ", :" + columnName + ", :"
			    + description + ", :" + ordering + ")" + " ON CONFLICT (table_id, column_name) DO NOTHING; COMMIT; \r\n";
		    if(cnt == 10) {
			querySource.update(finalQuery, map);
			cnt = 0;
			map.clear();
			finalQuery = "";
		    }else {
			cnt++;
		    }
		}
		
		connection.close();

		LOGGER.info("End load columns from system @id " + idSystem);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	loggerWithUser(module, map, ProtonEssences.REPORTING_UPDATE_SOURCE, user);
    }
}