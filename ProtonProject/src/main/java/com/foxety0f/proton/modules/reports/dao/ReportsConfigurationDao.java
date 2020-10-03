package com.foxety0f.proton.modules.reports.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.modules.reports.domain.configuration.AccessControl;
import com.foxety0f.proton.modules.reports.domain.configuration.AccessProvider;
import com.foxety0f.proton.modules.reports.domain.configuration.AccessType;
import com.foxety0f.proton.modules.reports.domain.configuration.Column;
import com.foxety0f.proton.modules.reports.domain.configuration.Condition;
import com.foxety0f.proton.modules.reports.domain.configuration.ConditionSeparator;
import com.foxety0f.proton.modules.reports.domain.configuration.ReportConfiguration;
import com.foxety0f.proton.modules.reports.domain.configuration.ReportLoadtime;

/**
 * proton_reporting_provide_access select proton_reporting_access select
 * proton_reporting_config select proton_reporting_loadtime select
 */
public class ReportsConfigurationDao extends AbstractDAO {

	public ReportsConfigurationDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<ReportConfiguration> getReports() {
		return querySource.query("select * from proton_reporting_config", new ReportConfigurationRowMapper());
	}

	public ReportConfiguration getReport(Integer id) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", id);

		List<ReportConfiguration> result = querySource.query("select * from proton_reporting_config where id = :id",
				map, new ReportConfigurationRowMapper());

		if (result.size() > 0)
			return result.get(0);

		return null;
	}

	public ReportConfiguration getReport(String link) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("link", link);

		List<ReportConfiguration> result = querySource.query("select * from proton_reporting_config where link = :link",
				map, new ReportConfigurationRowMapper());

		if (result.size() > 0)
			return result.get(0);

		return null;
	}

	public List<ReportConfiguration> getReports(Boolean isActive) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("isActive", isActive == false ? 0 : 1);

		return querySource.query("select * from proton_reporting_config where is_active = :isActive", map,
				new ReportConfigurationRowMapper());
	}

	private static class ReportConfigurationRowMapper implements RowMapper<ReportConfiguration> {

		@Override
		public ReportConfiguration mapRow(ResultSet rs, int rowNum) throws SQLException {

			ReportConfiguration item = new ReportConfiguration();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setOwner(rs.getLong("owner"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			item.setIsActive(rs.getInt("is_active") == 0 ? false : true);
			item.setThreadId(rs.getInt("thread_id"));
			item.setLink(rs.getString("link"));
			return item;
		}

	}

	public List<AccessControl> getAccess() {
		return querySource.query("select * from proton_reporting_access", new AccessControlRowMapper());
	}

	public List<AccessControl> getAccessByUser(Long userId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("userId", userId);

		return querySource.query("select * from proton_reporting_access where user_id = :userId", map,
				new AccessControlRowMapper());
	}

	public List<AccessControl> getAccessByLink(String link) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("link", link);

		return querySource.query("select * from proton_reporting_access where link = :link", map,
				new AccessControlRowMapper());
	}

	public List<AccessControl> getAccessByRole(Integer roleId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("roleId", roleId);

		return querySource.query("select * from proton_reporting_access where role_id = :roleId", map,
				new AccessControlRowMapper());
	}

	public List<AccessControl> getAccessByReport(Integer reportId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("reportId", reportId);

		return querySource.query("select * from proton_reporting_access where report_id = :reportId", map,
				new AccessControlRowMapper());
	}

	public Boolean checkAccessByReport(Long userId, Integer reportId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("userId", userId);
		map.put("reportId", reportId);

		return querySource.queryForObject(
				"select count(*) from proton_reporting_access where user_id = :userId and report_id = :reportId", map,
				Integer.class) == 0 ? false : true;
	}

	public Boolean checkAccessByLink(Long userId, String link) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("userId", userId);
		map.put("link", link);

		return querySource.queryForObject(
				"select count(*) from proton_reporting_access where user_id = :userId and link = :link", map,
				Integer.class) == 0 ? false : true;
	}

	private static class AccessControlRowMapper implements RowMapper<AccessControl> {

		@Override
		public AccessControl mapRow(ResultSet rs, int rowNum) throws SQLException {

			AccessControl item = new AccessControl();
			item.setId(rs.getInt("id"));
			item.setReportId(rs.getInt("report_id"));
			item.setUserId(rs.getInt("user_id"));
			item.setLinkId(rs.getInt("link_id"));
			item.setRoleId(rs.getInt("role_id"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			return item;
		}

	}

	public List<AccessProvider> getAccessProvider() {
		return querySource.query("select * from proton_reporting_provide_access", new AccessProviderRowMapper());
	}

	public List<AccessProvider> getAccessProviderByReportId(Integer reportId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("reportId", reportId);

		return querySource.query("select * from proton_reporting_provide_access where report_id = :reportId", map,
				new AccessProviderRowMapper());
	}

	public List<AccessProvider> getAccessProviderByAccessType(Integer accessType) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("accessType", accessType);

		return querySource.query("select * from proton_reporting_provide_access where access_type = :accessType", map,
				new AccessProviderRowMapper());
	}

	private static class AccessProviderRowMapper implements RowMapper<AccessProvider> {

		@Override
		public AccessProvider mapRow(ResultSet rs, int rowNum) throws SQLException {

			AccessProvider item = new AccessProvider();
			item.setId(rs.getInt("id"));
			item.setReportId(rs.getInt("reporting_id"));
			item.setAccessType(rs.getInt("access_type"));
			item.setCreator(rs.getInt("creator"));
			return item;
		}

	}

	public List<ReportLoadtime> getReportLoadTime() {
		return querySource.query("select * from proton_reporting_loadtime", new ReportLoadtimeRowMapper());
	}

	public List<ReportLoadtime> getReportLoadTimeByReport(Integer reportId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("reportId", reportId);

		return querySource.query("select * from proton_reporting_loadtime where report_id = :reportId",
				new ReportLoadtimeRowMapper());
	}

	public List<ReportLoadtime> getReportLoadTimeByFinish(Boolean isFinish) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("isFinish", isFinish == false ? 0 : true);

		return querySource.query("select * from proton_reporting_loadtime where is_finish = :isFinish",
				new ReportLoadtimeRowMapper());
	}

	public List<ReportLoadtime> getReportLoadTimeByUser(Long userId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("userId", userId);

		return querySource.query("select * from proton_reporting_loadtime where user_id = :userId",
				new ReportLoadtimeRowMapper());
	}

	public List<ReportLoadtime> getReportLoadTimeByStartDate(Date date) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("date", date);

		return querySource.query("select * from proton_reporting_loadtime where start_date = cast(:date as date)",
				new ReportLoadtimeRowMapper());
	}

	private static class ReportLoadtimeRowMapper implements RowMapper<ReportLoadtime> {

		@Override
		public ReportLoadtime mapRow(ResultSet rs, int rowNum) throws SQLException {

			ReportLoadtime item = new ReportLoadtime();
			item.setId(rs.getInt("id"));
			item.setStartTime(rs.getDate("start_time"));
			item.setEndTime(rs.getDate("end_time"));
			item.setIsFinish(rs.getInt("is_finish") == 0 ? false : true);
			item.setReportId(rs.getInt("report_id"));
			item.setUserId(rs.getLong("user_id"));
			item.setUuid(rs.getString("uuid"));
			return item;
		}

	}

	public List<AccessType> getAccessTypes() {
		return querySource.query("select * from proton_reporting_access_type", new AccessTypeRowMapper());
	}

	public AccessType getAccessTypesById(Integer id) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("id", id);
		List<AccessType> result = querySource.query("select * from proton_reporting_access_type where id = :id", map,
				new AccessTypeRowMapper());

		if (result.size() > 0)
			return result.get(0);

		return null;
	}

	private static class AccessTypeRowMapper implements RowMapper<AccessType> {

		@Override
		public AccessType mapRow(ResultSet rs, int rowNum) throws SQLException {

			AccessType item = new AccessType();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			item.setUserId(rs.getLong("user_id"));
			item.setIsActive(rs.getInt("is_active") == 0 ? false : true);
			item.setProvideTitle(rs.getString("provide_title"));
			item.setProvideDescription(rs.getString("provide_description"));
			item.setSuccessTitle(rs.getString("success_titile"));
			item.setSuccessDescription(rs.getString("success_description"));
			return item;
		}

	}

	public List<Column> getColumns() {
		return querySource.query("select * from proton_reporting_columns", new ColumnRowMapper());
	}

	public List<Column> getColumnsByReport(Integer id) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", id);

		return querySource.query("select * from proton_reporting_columns where id = :id", map, new ColumnRowMapper());
	}

	private static class ColumnRowMapper implements RowMapper<Column> {

		@Override
		public Column mapRow(ResultSet rs, int rowNum) throws SQLException {

			Column item = new Column();
			item.setId(rs.getInt("id"));
			item.setReportId(rs.getInt("report_id"));
			item.setColumnId(rs.getInt("column_id"));
			item.setTypeId(rs.getInt("type_id"));
			item.setAltName(rs.getString("altName"));
			item.setValues(rs.getString("values"));
			item.setDescription(rs.getString("description"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			item.setIsActive(rs.getInt("is_active") == 0 ? false : true);
			return item;
		}

	}

	public List<Condition> getConditions(){ 
		return querySource.query("select * from proton_reporting_conditions", new ConditionRowMapper()); 
	 }
	
	
	public List<Condition> getConditions(Integer reportId){
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", reportId);
		
		return querySource.query("select * from proton_reporting_conditions where report_id = :id", new ConditionRowMapper());
	}
	
	private static class ConditionRowMapper implements RowMapper<Condition> {

		@Override
		public Condition mapRow(ResultSet rs, int rowNum) throws SQLException {

			Condition item = new Condition();
			item.setId(rs.getInt("id"));
			item.setColumnId(rs.getInt("column_id"));
			item.setDescription(rs.getString("description"));
			item.setConditionType(rs.getInt("cond_type"));
			item.setReportId(rs.getInt("report_id"));
			item.setValues(rs.getString("values"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			item.setPath(rs.getInt("path"));
			item.setIsActive(rs.getInt("is_active") == 0 ? false : true);
			return item;
		}

	}
	
	public List<ConditionSeparator> getSeparators(){
		return querySource.query("select * from proton_reporting_conditions_sep", new ConditionSeparatorRowMapper());
	}
	
	public List<ConditionSeparator> getSeparators(Integer idReport){
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", idReport);
		
		return querySource.query("select * from proton_reporting_conditions_sep where id_report = :id", new ConditionSeparatorRowMapper());
	}
	
	private static class ConditionSeparatorRowMapper implements RowMapper<ConditionSeparator> {

		@Override
		public ConditionSeparator mapRow(ResultSet rs, int rowNum) throws SQLException {

			ConditionSeparator item = new ConditionSeparator();
			item.setId(rs.getInt("id"));
			item.setReportId(rs.getInt("report_id"));
			item.setSeparationType(rs.getInt("sep_type"));
			item.setPath(rs.getInt("path"));
			item.setcDate(rs.getDate("c_date"));
			item.setuDate(rs.getDate("u_date"));
			item.setIsActive(rs.getInt("is_active") == 0 ? false : true);
			return item;
		}

	}

}
