package com.foxety0f.proton.common.abstracts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;

@Component
public abstract class AbstractDAO {
	
	private Boolean showLog = true;

	protected final static Map<String, Object> EMPTY_PARAMS = Collections
			.unmodifiableMap(new HashedMap<String, Object>());

	protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractDAO.class);

	protected final String database;
	protected final NamedParameterJdbcTemplate querySource;
	protected final JdbcTemplate jdbcTemplate;

	public AbstractDAO(DataSource dataSource) {
		String database = "MS SQL";
		try (Connection connection = dataSource.getConnection();) {
			database = connection.getMetaData().getDatabaseProductName();
		} catch (SQLException e) {

		}

		this.database = database;
		this.querySource = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getDatabase() {
		return database;
	}
	
	public static class StringRowMapper implements RowMapper<String>{

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return rs.getString(1);
		}
		
	}
	
	public String createPK(String tableName, String columnName) {
		return "alter table " + tableName + " add constraint " + tableName.toUpperCase() + "_PK primary key (" + columnName + ")";
	}
	
	public Integer logger(ProtonModules module, Map<String, ?> params, ProtonEssences essence, NamedParameterJdbcTemplate src) {
		String logTable = loggerMapper().get(module);
		Integer seqNum = querySource.queryForObject("select nextval('public.\"LoggerNumSequence\"')", EMPTY_PARAMS, Integer.class);
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("seqNum", seqNum);
		map.put("logTable", logTable);
		map.put("module", module.moduleName());
		map.put("essence", essence.getString());
		
		String sql = "insert into " + logTable + " values(" + seqNum + ", '" + module.moduleName() + "', '" + essence.getString() + "', CURRENT_TIMESTAMP)";
		
		querySource.update(sql, map);
		
		if(showLog) {
			LOGGER.info(sql);
		}
		
		Set<String> keys = params.keySet();
		
		
		for(String key : keys) {
			String sql2 = "insert into " + logTable + "_attr (id_core, module, param, value, sys_mod_ts)"
					+ "values (:seqNum, :module, '" + key + "', " + "'" + params.get(key) + "', CURRENT_TIMESTAMP)";
			
			querySource.update(sql2 , map);
			
			if(showLog) {
				LOGGER.info(sql2);
			}
		}
		
		return seqNum;
		
	}
	
	public void logException(ProtonModules module, ProtonEssences essence, String exceptionName, String value) {
		String logTable = loggerMapper().get(module);
		Integer seqNum = querySource.queryForObject("select nextval('public.\"LoggerNumSequence\"')", EMPTY_PARAMS, Integer.class);
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("seqNum", seqNum);
		map.put("logTable", logTable);
		map.put("module", module.moduleName());
		map.put("essence", essence.getString());
		map.put("exception", exceptionName);
		map.put("value", value);
		
		String sql = "insert into " + logTable + " values(" + seqNum + ", '" + module.moduleName() + "', '" + essence.getString() + "', CURRENT_TIMESTAMP)";
		
		querySource.update(sql, map);
		
		if(showLog) {
			LOGGER.error(sql);
		}
		
		String sql2 = "insert into " + logTable + "_attr (id_core, module, param, value, sys_mod_ts)"
				+ "values (:seqNum, :module, :exception, :value, CURRENT_TIMESTAMP)";
		
		querySource.update(sql2 , map);
		
		if(showLog) {
			LOGGER.error(sql2);
		}
	}
	
	public void loggerWithUser(ProtonModules thisModule, Map<String, ?> map, ProtonEssences essence, UserDetailsProton user) {
		Integer seqNum = logger(thisModule, map, essence, null);
		Map<String, Object> map1 = new HashedMap<String, Object>();
		map1.put("seq", seqNum);
		map1.put("username", user.getUsername());
		querySource.update("insert into z_logger_user (id, username) "
				+ " values(:seq, :username)", map1);
	}
	
	public Map<ProtonModules, String> loggerMapper(){
		Map<ProtonModules, String> mapper = new HashedMap<ProtonModules, String>();
		mapper.put(ProtonModules.ADMIN, "z_proton_admin_logger");
		mapper.put(ProtonModules.CONSTRUCTOR, "z_proton_constructor_logger");
		mapper.put(ProtonModules.DISTRIBUTION, "z_proton_distribution_logger");
		mapper.put(ProtonModules.EMPLOYEES, "z_proton_employees_logger");
		mapper.put(ProtonModules.QUALITY, "z_proton_quality_logger");
		mapper.put(ProtonModules.REPORTS, "z_proton_reports_logger");
		mapper.put(ProtonModules.RESFULL, "z_proton_restfull_logger");
		mapper.put(ProtonModules.ROLES, "z_proton_roles_logger");
		mapper.put(ProtonModules.TESTS, "z_proton_tests_logger");
		mapper.put(ProtonModules.HELP, "z_proton_help_logger");
		
		return mapper;
	}

}
