package com.foxety0f.proton.modules.reports.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.ansible.AnsibleInformation;
import com.foxety0f.proton.ansible.IAnsibleControl;
import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
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
import com.foxety0f.proton.utils.AESCryptography;

public class ReportsDao extends AbstractDAO implements IReportsDao {
	
	@Autowired
	private IAnsibleControl ansible;

	private static final ProtonModules thisModule = ProtonModules.REPORTS;

	private static final Map<String, String> dbTablesQuery = new HashedMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6230832482213926025L;

		{
			put("PostgreSQL",
					"select * from information_schema.tables where table_schema not in ('pg_catalog', 'information_schema')");
		}
	};

	private static final Map<String, String> dbColumnsQuery = new HashedMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5483157739311007594L;

		{
			put("PostgreSQL",
					"select * from information_schema.columns where table_schema = :schema and table_name = :table");
		}
	};

	private Integer tablesCount = 0;
	private Integer tablesNow = 0;
	private Integer columnsCount = 0;
	private Integer columnsNow = 0;

	public Integer getTablesNow() {
		return tablesNow;
	}

	public void setTablesNow(Integer tablesNow) {
		this.tablesNow = tablesNow;
	}

	public Integer getColumnsNow() {
		return columnsNow;
	}

	public void setColumnsNow(Integer columnsNow) {
		this.columnsNow = columnsNow;
	}

	public Integer getTablesCount() {
		return tablesCount;
	}

	public void setTablesCount(Integer tablesCount) {
		this.tablesCount = tablesCount;
	}

	public Integer getColumnsCount() {
		return columnsCount;
	}

	public void setColumnsCount(Integer columnsCount) {
		this.columnsCount = columnsCount;
	}

	public ReportsDao(DataSource dataSource) {
		super(dataSource);

	}

	public void createNewDatabaseType(String name, UserDetailsProton user) throws DatabaseTypeExistException {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("name", name);

		Boolean exist = querySource.queryForObject(
				"select count(*) from proton_meta_databases_names where name = :name", map, Integer.class) == 0 ? false
						: true;

		if (exist) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_DATABASE_TYPE, "DatabaseTypeExistException", name,
					user);
			throw new DatabaseTypeExistException(name);
		}

		querySource.update("insert into proton_meta_databases_names(name) values(:name)", map);
		loggerWithUser(thisModule, map, ProtonEssences.META_CREATE_NEW_DATABASE_TYPE, user);

	}

	public void createNewDatabaseConnection(Integer dataType, String url, String userName, String password,
			String driverName, String testSql, String databaseName, UserDetailsProton user)
			throws DatabaseConnectionAlreadyExistException, DatabasePasswordMissingException,
			DatabaseUserMissingException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (password == null || password.equals("") || password.equals("null")) {
			logException(thisModule, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, "DatabasePasswordMissingException",
					null, user);
			throw new DatabasePasswordMissingException();
		}
		map.put("dataType", dataType);
		map.put("url", url);
		map.put("userName", userName);
		map.put("databaseName", databaseName);
		try {
			map.put("password", AESCryptography.encrypt(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("driverName", driverName);
		map.put("testSql", testSql.equals("") ? "select 1" : testSql);

		if (userName == null || userName.equals("") || userName.equals("null")) {
			logException(thisModule, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, "DatabaseUserMissingException",
					null, user);
			throw new DatabaseUserMissingException();
		}

		if (sqlExistInTableByKey(querySource, "proton_meta_databases", "url", url)) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_DATABASE_CONNECTION,
					"DatabaseConnectionAlreadyExistException", url, user);
			throw new DatabaseConnectionAlreadyExistException(url);
		}

		querySource.update(
				"insert into proton_meta_databases (database_type_id, url, user_name, password, driver_name, test_sql, \"databaseName\")"
						+ " values(:dataType, :url, :userName, :password, :driverName, :testSql, :databaseName)",
				map);

		loggerWithUser(thisModule, map, ProtonEssences.META_CREATE_NEW_DATABASE_CONNECTION, user);

	}

	public void createNewThread(Integer databaseId, String name, String description, UserDetailsProton user)
			throws ThreadAlreadyExistException, ThreadDatabaseIdMissingException, ThreadNameMissingException {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("databaseId", databaseId);
		map.put("name", name);
		map.put("description", description);

		if (databaseId == null) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_THREAD, "ThreadDatabaseIdMissingException", null,
					user);
			throw new ThreadDatabaseIdMissingException();
		}

		if (name.equals("null") || name == null || name.equals("")) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_THREAD, "ThreadNameMissingException", null, user);
			throw new ThreadNameMissingException();
		}

		if (sqlExistInTableByKey(querySource, "proton_meta_threads", "name", name)) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_THREAD, "ThreadAlreadyExistException", name, user);
			throw new ThreadAlreadyExistException(name);
		}

		querySource.update("insert into proton_meta_threads(database_id, name, description, isActive, c_date, u_date)"
				+ " values(:databaseId, :name, :description, 1, CURRENT_DATE, CURRENT_DATE)", map);

		loggerWithUser(thisModule, map, ProtonEssences.META_CREATE_NEW_THREAD, user);
	}

	public void updateDatabaseConnection(Integer id, Integer dataType, String url, String userName, String password,
			String driverName, String testSql, UserDetailsProton user) throws DatabaseUpdateNullIDException,
			DatabaseUserMissingException, DatabasePasswordMissingException, DatabaseNotFoundException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (password == null || password.equals("") || password.equals("null")) {
			logException(thisModule, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, "DatabasePasswordMissingException",
					null, user);
			throw new DatabasePasswordMissingException();
		}
		map.put("dataType", dataType);
		map.put("url", url);
		map.put("userName", userName);
		try {
			map.put("password", AESCryptography.decrypt(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("driverName", driverName);
		map.put("testSql", testSql.equals("") ? "select 1" : testSql);

		if (id == null) {
			logException(thisModule, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, "DatabaseUpdateNullIDException",
					null, user);
			throw new DatabaseUpdateNullIDException(id);
		}

		if (userName == null || userName.equals("") || userName.equals("null")) {
			logException(thisModule, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, "DatabaseUserMissingException",
					null, user);
			throw new DatabaseUserMissingException();
		}

		if (!sqlExistInTableByKey(querySource, "proton_meta_databases", "id", id)) {
			logException(thisModule, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, "DatabaseNotFoundException",
					id.toString(), user);
			throw new DatabaseNotFoundException(id);
		}

		querySource.update("update proton_meta_databases" + " set database_type_id = :dataType, " + "     url = :url,"
				+ "     password = :password," + "     driver_name = :driverName," + "     test_sql = :testSql,"
				+ "     user_name = :userName " + "where id = :id", map);

		loggerWithUser(thisModule, map, ProtonEssences.META_UPDATE_DATABASE_CONNECTION, user);

	}

	public void updateThread(Integer id, Integer databaseId, String name, String description, UserDetailsProton user)
			throws ThreadAlreadyExistException, ThreadDatabaseIdMissingException, ThreadNameMissingException,
			ThreadIdMissingException {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("databaseId", databaseId);
		map.put("name", name);
		map.put("description", description);
		map.put("id", id);

		if (id == null) {
			logException(thisModule, ProtonEssences.META_UPDATE_THREAD, "ThreadIdMissingException", null, user);
			throw new ThreadIdMissingException();
		}

		if (databaseId == null) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_THREAD, "ThreadDatabaseIdMissingException", null,
					user);
			throw new ThreadDatabaseIdMissingException();
		}

		if (name.equals("null") || name == null || name.equals("")) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_THREAD, "ThreadNameMissingException", null, user);
			throw new ThreadNameMissingException();
		}

		if (!sqlExistInTableByKey(querySource, "proton_meta_threads", "name", name)) {
			logException(thisModule, ProtonEssences.META_CREATE_NEW_THREAD, "ThreadAlreadyExistException", name, user);
			throw new ThreadAlreadyExistException(name);
		}

		querySource.update("insert into proton_meta_threads(database_id, name, description, isActive, c_date, u_date)"
				+ " values(:databaseId, :name, :description, 1, CURRENT_DATE, CURRENT_DATE)", map);

		loggerWithUser(thisModule, map, ProtonEssences.META_CREATE_NEW_THREAD, user);
	}

	public List<MetaDatabases> getDatabases() {
		return querySource.query("select * from proton_meta_databases", new RowMapper<MetaDatabases>() {

			@Override
			public MetaDatabases mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetaDatabases item = new MetaDatabases();
				item.setId(rs.getInt("id"));
				item.setDriverName(rs.getString("driver_name"));
				item.setIdType(rs.getInt("database_type_id"));
				item.setUserName(rs.getString("user_name"));
				item.setPassword(null);
				item.setTestSql(rs.getString("test_sql"));
				item.setUrl(rs.getString("url"));
				item.setDatabaseName(rs.getString("databasename"));
				return item;
			}

		});
	}

	public List<MetaDatabases> getDatabases(Integer idDatabase) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("idDatabase", idDatabase);

		return querySource.query("select * from proton_meta_databases where id = :idDatabase", map,
				new RowMapper<MetaDatabases>() {

					@Override
					public MetaDatabases mapRow(ResultSet rs, int rowNum) throws SQLException {
						MetaDatabases item = new MetaDatabases();
						item.setId(rs.getInt("id"));
						item.setDriverName(rs.getString("driver_name"));
						item.setIdType(rs.getInt("database_type_id"));
						item.setUserName(rs.getString("user_name"));
						item.setPassword(rs.getString("password"));
						item.setTestSql(rs.getString("test_sql"));
						item.setUrl(rs.getString("url"));
						item.setDatabaseName(rs.getString("databaseName"));
						return item;
					}

				});
	}

	public List<MetaDatabasesNames> getDatabasesNames() {
		return querySource.query("select * from proton_meta_databases_names", new RowMapper<MetaDatabasesNames>() {

			@Override
			public MetaDatabasesNames mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetaDatabasesNames item = new MetaDatabasesNames();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				return item;
			}
		});
	}

	public List<MetaThreads> getThreads() {
		return querySource.query("select * from proton_meta_threads", new MetaThreadsRowMapper());
	}
	
	public List<MetaThreads> getThreads(Integer database) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("idDatabase", database);
		return querySource.query("select * from proton_meta_threads where id_database = :idDatabase", new MetaThreadsRowMapper());
	}

	public List<MetaThreadTablesMap> getThreadsTablesMap() {
		return querySource.query("select * from proton_meta_thread_table_map", new RowMapper<MetaThreadTablesMap>() {

			@Override
			public MetaThreadTablesMap mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetaThreadTablesMap item = new MetaThreadTablesMap();
				item.setId(rs.getInt("id"));
				item.setTableId(rs.getInt("table_id"));
				item.setThreadId(rs.getInt("thread_id"));
				item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
				return item;
			}
		});
	}

	public List<MetaTables> getTables() {
		return querySource.query("select * from proton_meta_tables", new MetaTablesRowMapper());
	}

	public List<MetaTables> getTablesDatabase(Integer idDatabase) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("param", idDatabase);

		return querySource.query("select * from proton_meta_tables " + " where id_database = :param", map,
				new MetaTablesRowMapper());
	}

	public List<MetaTables> getTablesTable(Integer idTable) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("param", idTable);

		return querySource.query("select * from proton_meta_tables " + " where id = :param",
				new MetaTablesRowMapper());
	}

	public List<MetaColumns> getColumns() {
		return querySource.query("select * from proton_meta_columns", new RowMapper<MetaColumns>() {

			@Override
			public MetaColumns mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetaColumns item = new MetaColumns();
				item.setId(rs.getInt("id"));
				item.setTableId(rs.getInt("table_id"));
				item.setColumnName(rs.getString("column_name"));
				item.setDescription(rs.getString("description"));
				item.setAltName(rs.getString("alt_name"));
				item.setColumnType(rs.getInt("column_type"));
				item.setIsDictVal(rs.getInt("isDictVal") == 0 ? false : true);
				item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
				item.setcDate(rs.getDate("c_date"));
				item.setuDate(rs.getDate("u_date"));
				item.setIsNeedEncrypt(rs.getInt("is_need_encrypt") == 0 ? false : true);
				return item;
			}
		});
	}

	public List<MetaColumnType> getColumnTypes() {
		return querySource.query("select * from proton_meta_columns_dict", new RowMapper<MetaColumnType>() {

			@Override
			public MetaColumnType mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetaColumnType item = new MetaColumnType();
				item.setId(rs.getInt("id"));
				item.setOverName(rs.getString("over_name"));
				item.setNameType(rs.getString("name_type"));
				item.setSourceName(rs.getString("source_name"));
				item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
				return item;
			}
		});
	}

	public List<MetaColumnDict> getColumnsDict() {
		return querySource.query("select * from proton_meta_columns_dict", new RowMapper<MetaColumnDict>() {

			@Override
			public MetaColumnDict mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetaColumnDict item = new MetaColumnDict();
				item.setId(rs.getInt("id"));
				item.setColumnId(rs.getInt("column_id"));
				item.setDictValue(rs.getString("dict_value"));
				item.setAltName(rs.getString("alt_name"));
				return item;
			}
		});
	}

	public List<MetaColumns> getColumns(Integer tableId) {
		return querySource.query("select * from proton_meta_columns where table_id = " + tableId,
				new RowMapper<MetaColumns>() {

					@Override
					public MetaColumns mapRow(ResultSet rs, int rowNum) throws SQLException {
						MetaColumns item = new MetaColumns();
						item.setId(rs.getInt("id"));
						item.setTableId(rs.getInt("table_id"));
						item.setColumnName(rs.getString("column_name"));
						item.setDescription(rs.getString("description"));
						item.setAltName(rs.getString("alt_name"));
						item.setColumnType(rs.getInt("column_type"));
						item.setIsDictVal(rs.getInt("isDictVal") == 0 ? false : true);
						item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
						item.setcDate(rs.getDate("c_date"));
						item.setuDate(rs.getDate("u_date"));
						item.setIsNeedEncrypt(rs.getInt("is_need_encrypt") == 0 ? false : true);
						return item;
					}
				});
	}

	public List<MetaDatabases> getFake() {
		List<MetaDatabases> result = new ArrayList<MetaDatabases>();
		MetaDatabases md = new MetaDatabases();

		md.setUserName("postgres");
		md.setPassword("123642sA");
		md.setUrl("jdbc:postgresql://63.250.60.119:5432/proton");

		result.add(md);

		return result;
	}

	public void updateTableList(Integer idDatabase, UserDetailsProton user, Boolean isFillColumns) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("idDatabase", idDatabase);
		MetaDatabases database = getDatabases(idDatabase).get(0);
		Connection con = null;
		
		ProtonEssences essence = ProtonEssences.META_FILL_TABLES;
		
		String guid = ansible.geenrateGuid(thisModule);
		
		ansible.setInfo(thisModule, guid, null, "updateTableList", essence, new Date(), "Update table list from database", user.getUsername());

		try {
			con = DriverManager.getConnection(database.getUrl(), database.getUserName(),
					AESCryptography.decrypt(database.getPassword()));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (con != null) {
			try {
				String sql = dbTablesQuery.get(con.getMetaData().getDatabaseProductName());

				PreparedStatement ps = con.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				List<Map<String, Object>> lmp = new ArrayList<Map<String, Object>>();

				while (rs.next()) {
					Map<String, Object> mp = new HashedMap<String, Object>();
					for (int i = 1; i < rsmd.getColumnCount(); i++) {
						mp.put(rsmd.getColumnLabel(i), rs.getObject(i));
					}
					lmp.add(mp);

				}

				Set<String> keys = null;

				setTablesCount(lmp.size());
				
				for (int i = 0; i < lmp.size(); i++) {
					
					ansible.setValue(guid, "Loaded " + (i + 1) + " of " + lmp.size());
					
					setTablesNow(i + 1);
					
					Map<String, Object> mapU = new HashedMap<String, Object>();
					mapU.put("idDatabase", idDatabase);
					mapU.put("tableName", lmp.get(i).get("table_name"));
					mapU.put("schema_name", lmp.get(i).get("table_schema"));
					mapU.put("c_date", new Date());
					mapU.put("u_date", new Date());
					mapU.put("isActive", 1);

					Boolean exist = querySource.queryForObject(
							"select count(*) from proton_meta_tables where table_name = :tableName",
							mapU, Integer.class) >= 1 ? true : false;
					
					if(!exist) {
						querySource.update(""
								+ " insert into proton_meta_tables (id_database, schema_name, table_name, c_date, u_date, \"isActive\")"
								+ " select vl.id_database, vl.schema_name, vl.table_name, vl.c_date, vl.u_date, vl.isActive "
								+ " from(select :idDatabase id_database " + ", :schema_name schema_name "
								+ ", :tableName table_name " + ", CURRENT_DATE c_date " + ", CURRENT_DATE u_date "
								+ ", :isActive isActive) vl "
								+ " left join proton_meta_tables sup on vl.id_database = sup.id_database and sup.schema_name = vl.schema_name and vl.table_name = sup.table_name"
								+ " where sup.id is null " + "", mapU);
						loggerWithUser(thisModule, mapU, essence, user);
					}

				}

				con.close();
				

				if(isFillColumns) {
					fillTablesByDatabase(idDatabase, true, user);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		ansible.kill(guid);

	}

	public List<MetaDatabases> getDatabaseFromTableId(Integer idTable) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("param", idTable);
		return querySource.query(
				"select * from proton_meta_databases where id is(select id_database from proton_meta_tables where id = :param)",
				map, new RowMapper<MetaDatabases>() {

					@Override
					public MetaDatabases mapRow(ResultSet rs, int rowNum) throws SQLException {
						MetaDatabases item = new MetaDatabases();
						item.setId(rs.getInt("id"));
						item.setDriverName(rs.getString("driver_name"));
						item.setIdType(rs.getInt("database_type_id"));
						item.setUserName(rs.getString("user_name"));
						item.setPassword(rs.getString("password"));
						item.setTestSql(rs.getString("test_sql"));
						item.setUrl(rs.getString("url"));
						return item;
					}

				});
	}

	public void fillTablesByDatabase(Integer idDatabase, Boolean setActive, UserDetailsProton user) {
		updateColumnList(getTablesDatabase(idDatabase), getDatabases(idDatabase).get(0), setActive, user);
	}

	public void fillTablesByTable(Integer idTable, Boolean setActive, UserDetailsProton user) {
		updateColumnList(getTablesTable(idTable), getDatabaseFromTableId(idTable).get(0), setActive, user);
	}

	public void updateColumnList(List<MetaTables> tables, MetaDatabases dt, Boolean setActive, UserDetailsProton user) {
		Connection con = null;
		
		ProtonEssences essence = ProtonEssences.META_FILL_COLUMNS;
		
		String guid = ansible.geenrateGuid(thisModule);
		
		ansible.setInfo(thisModule, guid, null, "updateColumnList", essence, new Date(), "Update column list from tables for database", user.getUsername());


		try {
			con = DriverManager.getConnection(dt.getUrl(), dt.getUserName(), AESCryptography.decrypt(dt.getPassword()));

			List<MetaColumnType> columnTypes = getColumnTypes();

			setTablesCount(tables.size());
			
			for (int tableI = 0; tableI < tables.size(); tableI++) {
				
				setTablesNow(tableI + 1);
				
				String sql = dbColumnsQuery.get(con.getMetaData().getDatabaseProductName())
						.replaceAll(":schema", "'" + tables.get(tableI).getSchemaName() + "'")
						.replaceAll(":table", "'" + tables.get(tableI).getTableName() + "'");

				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				List<Map<String, Object>> lmp = new ArrayList<Map<String, Object>>();

				while (rs.next()) {
					Map<String, Object> mp = new HashedMap<String, Object>();
					for (int i = 1; i < rsmd.getColumnCount(); i++) {
						mp.put(rsmd.getColumnLabel(i), rs.getObject(i));
					}
					lmp.add(mp);
				}
				
				setColumnsCount(lmp.size());

				for (int i = 0; i < lmp.size(); i++) {
					
					ansible.setValue(guid, "Fill " + (i + 1) + " column of " + lmp.size() + " for " + (tableI + 1) + " table of " + tables.size() );
					
					setColumnsNow(i + 1);
					
					Map<String, Object> map = new HashedMap<String, Object>();
					map.put("table_id", tables.get(tableI).getId());
					map.put("column_name", lmp.get(i).get("column_name"));
					map.put("column_type", 100);
					String typename = "Undefined";

					for (int j = 0; j < columnTypes.size(); j++) {
						if (columnTypes.get(j).getSourceName().equals(lmp.get(i).get("data_type"))) {
							map.put("column_type", columnTypes.get(j).getId());
							typename = columnTypes.get(j).getOverName();
						}
					}
					map.put("ordinal_position", lmp.get(i).get("ordinal_position"));
					map.put("is_nullable", lmp.get(i).get("is_nullable").equals("YES") ? 1 : 0);

					switch (typename) {
					case "Undefined":
						map.put("length", 0);
						break;
					case "Integer":
						map.put("length", lmp.get(i).get("numeric_precision"));
						break;
					case "String":
						map.put("length", lmp.get(i).get("character_maximum_length"));
						break;
					default:
						map.put("length", 0);
						break;
					}

					map.put("is_generate", lmp.get(i).get("is_identity").equals("YES") ? 1 : 0);
					map.put("isActive", setActive == true ? 1 : 0);
					
					
					Boolean exist = querySource.queryForObject(
							"select count(*) from proton_meta_columns where table_id = :table_id and column_name = :column_name",
							map, Integer.class) >= 1 ? true : false;

					if (!exist) {
						String sqlIns = "insert into proton_meta_columns(table_id, column_name, description,"
								+ " alt_name, column_type, \"isDictVal\", \"isActive\", c_date, u_date, is_need_encrypt,"
								+ " position, is_generate, is_nullable, length)" + " SELECT base.*" + " FROM ("
								+ "		SELECT :table_id table_id," + "			   :column_name column_name,"
								+ "			   null description, " + "			   null alt_name, "
								+ "			   :column_type column_type, " + " 		   0 isDictVal,"
								+ "			   :isActive isActive," + "			   CURRENT_DATE c_date,"
								+ " 		   CURRENT_DATE u_date, " + "			   0 is_need_encrypt,"
								+ "			   :ordinal_position ordinal_position,"
								+ "			   :is_generate is_generate, " + "			   :is_nullable is_nullable,"
								+ "			   cast(:length as bigint) length_col" + ") base"
								+ " left join proton_meta_columns col on base.table_id = col.table_id and base.column_name = col.column_name "
								+ " where col.id is null ";
						querySource.update(sqlIns, map);
						loggerWithUser(thisModule, map, essence,  user);
					}
				}

			}

			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ansible.kill(guid);
	}

	public void updateColumnInfo(final Integer id, final Integer columnId, final String param, final Object columnValue,
			final UserDetailsProton user) throws SchemaAndTableMissingException, ColumnMissingException,
			ColumnIdUpdateException, TableIdUpdateException {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("param", id);
		map.put("colParam", columnId);
		map.put("value", columnValue);
		map.put("fieldParam", param);

		if (querySource.queryForObject("select count(*) from proton_meta_tables where id = :param", map,
				Integer.class) == 0) {
			logException(thisModule, ProtonEssences.META_UPDATE_COLUMN, "SchemaAndTableMissingException", id.toString(),
					user);
			throw new SchemaAndTableMissingException(id);
		}

		Boolean existColumn = querySource.queryForObject("select count(*) from proton_meta_tables t "
				+ " join proton_meta_columns c on t.id = c.table_id" + " where t.id = :param and c.id = :colParam", map,
				Integer.class) == 0 ? false : true;

		if (!existColumn) {
			logException(thisModule, ProtonEssences.META_UPDATE_COLUMN, "ColumnMissingException", columnId.toString(),
					user);
			throw new ColumnMissingException(columnId);
		}

		if (param.toLowerCase().equals("id")) {
			logException(thisModule, ProtonEssences.META_UPDATE_COLUMN, "ColumnIdUpdateException", param, user);
			throw new ColumnIdUpdateException();
		}

		if (param.toLowerCase().equals("table_id")) {
			logException(thisModule, ProtonEssences.META_UPDATE_COLUMN, "TableIdUpdateException", param, user);
			throw new TableIdUpdateException();

		}
		
		try {
			querySource.update("update proton_meta_columns " + " set \"" + param + "\" = :value, "
					+ " 	u_date = CURRENT_DATE" + " where id = :colParam and table_id = :param ", map);
	
			loggerWithUser(thisModule, map, ProtonEssences.META_UPDATE_COLUMN, user);
		}catch(Exception e) {
			try {
				map.put("intVal", Integer.parseInt(columnValue.toString()));
				querySource.update("update proton_meta_columns " + " set \"" + param + "\" = :intVal, "
						+ " 	u_date = CURRENT_DATE" + " where id = :colParam and table_id = :param ", map);
		
				loggerWithUser(thisModule, map, ProtonEssences.META_UPDATE_COLUMN, user);
			}catch(Exception e1) {
				map.put("dateVal", new Date(columnValue.toString()));
				querySource.update("update proton_meta_columns " + " set \"" + param + "\" = :dateVal, "
						+ " 	u_date = CURRENT_DATE" + " where id = :colParam and table_id = :param ", map);
		
				loggerWithUser(thisModule, map, ProtonEssences.META_UPDATE_COLUMN, user);
			}
		}

		

	}

	public void updateTableInfo(final Integer id, final Integer idDatabase, final String field, final Object value,
			UserDetailsProton user)
			throws DatabaseNotFoundException, TableIdUpdateException, DatabaseIdUpdateException {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("tableId", id);
		map.put("databaseId", idDatabase);
		map.put("field", field);
		map.put("value", value);

		final ProtonEssences essence = ProtonEssences.META_UPDATE_TABLE;

		if (querySource.queryForObject("select count(*) from proton_meta_databases where id = :databaseId", map,
				Integer.class) == 0) {
			logException(thisModule, essence, "DatabaseNotFoundException", idDatabase.toString());
			throw new DatabaseNotFoundException(idDatabase);
		}

		if (querySource.queryForObject("select count(*) from proton_meta_tables where id = :tableId", map,
				Integer.class) == 0) {
			logException(thisModule, essence, "TableNotFoundException", id.toString(), user);
		}

		if (field.toLowerCase().equals("id")) {
			logException(thisModule, essence, "TableIdUpdateException", field, user);
			throw new TableIdUpdateException();
		}

		if (field.toLowerCase().equals("id_database")) {
			logException(thisModule, essence, "DatabaseIdUpdateException", field, user);
			throw new DatabaseIdUpdateException();
		}

		
		try {
			querySource.update("update proton_meta_tables" + " set \"" + field + "\" = :value, " + " u_date = CURRENT_DATE "
					+ " where id = :tableId and id_database = :databaseId", map);
	
			loggerWithUser(thisModule, map, essence, user);
		}catch(Exception e) {
			try {
				map.put("intVal", Integer.parseInt(value.toString()));
				querySource.update("update proton_meta_tables" + " set \"" + field + "\" = :intVal, " + " u_date = CURRENT_DATE "
						+ " where id = :tableId and id_database = :databaseId", map);
		
				loggerWithUser(thisModule, map, essence, user);
			}catch(Exception e1) {
				map.put("dateVal", new Date(value.toString()));
				querySource.update("update proton_meta_tables" + " set \"" + field + "\" = :dateVal, " + " u_date = CURRENT_DATE "
						+ " where id = :tableId and id_database = :databaseId", map);
		
				loggerWithUser(thisModule, map, essence, user);
			}
		}
		
	}
	
	private static class MetaThreadsRowMapper implements RowMapper<MetaThreads> {

		@Override
		public MetaThreads mapRow(ResultSet rs, int rowNum) throws SQLException {
			MetaThreads item = new MetaThreads();
			item.setId(rs.getInt("id"));
			item.setDatabaseId(rs.getInt("database_id"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			return item;
		}
	}
	
	private static class MetaTablesRowMapper implements RowMapper<MetaTables> {

		@Override
		public MetaTables mapRow(ResultSet rs, int rowNum) throws SQLException {
			MetaTables item = new MetaTables();
			item.setId(rs.getInt("id"));
			item.setIdDatabase(rs.getInt("id_database"));
			item.setSchemaName(rs.getString("schema_name"));
			item.setTableName(rs.getString("table_name"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
			item.setDescription(rs.getString("description"));
			item.setAltName(rs.getString("alt_name"));
			return item;
		}
	}

}
