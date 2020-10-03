package com.foxety0f.proton.modules.constructor.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.foxety0f.proton.ansible.AnsibleControl;
import com.foxety0f.proton.ansible.AnsibleInformation;
import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.constructor.exceptions.ReportNotFoundException;
import com.foxety0f.proton.modules.reports.dao.ReportsConfigurationDao;
import com.foxety0f.proton.modules.reports.domain.configuration.Column;
import com.foxety0f.proton.modules.reports.domain.configuration.Condition;
import com.foxety0f.proton.modules.reports.domain.configuration.ConditionSeparator;
import com.foxety0f.proton.modules.reports.domain.configuration.ShareLink;

public class ConstructorDao extends AbstractDAO {

	@Autowired
	private AnsibleControl ansible;

	private ReportsConfigurationDao reportsConfig;

	ProtonModules thisModule = ProtonModules.CONSTRUCTOR;

	public ConstructorDao(DataSource dataSource) {
		super(dataSource);
		this.reportsConfig = new ReportsConfigurationDao(dataSource);
	}

	private static String SQL_COL = "insert into proton_reporting_columns (id, report_id, column_id, type_id, \"altName\", \"values\", description, c_date, u_date, is_active)"
			+ " values (:id, :reportId, :columnId, :typeId, :altName, :values, :description, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :isActive) ";

	private static String SQL_COND = "insert into proton_reporting_conditions"
			+ " values (:id, :columnId, :description, :condType, :reportId, :values, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :path, :isActive) ";

	private static String SQL_COL_HIST = "insert into proton_reporting_columns_hist(report_id, column_id, type_id, \"altName\", \"values\", description, c_date, u_date, original_id, version, is_active)"
			+ " select report_id, column_id, type_id, \"altName\", \"values\", description, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, id, :versionId, is_active "
			+ " from proton_reporting_columns " + " where id = :id";

	private static String SQL_COND_HIST = "insert into proton_reporting_conditions_hist(column_id, description, cond_type, report_id, \"values\", c_date, u_date, path, original_id, version, is_active) "
			+ " select column_id, description, cond_type, report_id, \"values\", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, path, id, :versionId, is_active "
			+ " from proton_reporting_conditions" + " where id = :id";

	private static String SQL_COND_SEP = "insert into proton_reporting_conditions_sep"
			+ " values (:id, :reportId, :sepType, :path, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :isActive)";

	private static String SQL_COND_SEP_HIST = "insert into proton_reporting_conditions_sep_hist (report_id, sep_type, path, c_date, u_date, is_active, original_id, version) "
			+ " select report_id, sep_type, path, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, is_active, id, :versionId "
			+ " from proton_reporting_conditions_sep" + " where id = :id ";

	public void createReport(final String name, final String description, final Boolean isActive,
			final Integer threadId, String link, UserDetailsProton user, List<Column> columns,
			List<Condition> conditions, List<ConditionSeparator> separators) {
		Integer reportId = querySource.queryForObject("select nextval('public.\"REPORTING_CONFIG_ID\"')", EMPTY_PARAMS,
				Integer.class);
		ProtonEssences essence = ProtonEssences.CONSTRUCTOR_CREATE_REPORT;
		String guid = ansible.geenrateGuid(thisModule);
		AnsibleInformation info = new AnsibleInformation();
		info.setDatetime(new Date());
		info.setDescription("Create report for " + threadId + " thread with '" + name + "' name");
		info.setEssence(ProtonEssences.CONSTRUCTOR_CREATE_REPORT);
		info.setIsActive(true);
		info.setMethod("Authenticate");
		info.setUsername(user.getUsername());
		info.setModule(thisModule);
		info.setValue(
				"Using constructor for create report. If report will be correct created, @id will be " + reportId);

		ansible.addInfo(guid, info);

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("name", name);
		map.put("description", description);
		map.put("isActive", isActive == false ? 0 : 1);
		map.put("threadId", threadId);
		map.put("owner", user.getUserId());
		map.put("guiForLog", guid);

		if (link == null || link == "") {
			UUID uuid = UUID.randomUUID();
			link = uuid.toString();
		}

		map.put("link", link);
		map.put("id", reportId);

		querySource.update("insert into proton_reporting_config"
				+ "values(:id, :name, :description, :owner, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :isActive, :threadId, :link)",
				map);

		Map<String, Integer> versions = generatedVersion("First Version", user, reportId, false);
		Integer versionId = versions.get("versionId");

		ansible.setValue(guid,
				"@id - " + reportId + "<br> @version - " + versionId + "<br> Columns count - " + columns.size());

		loggerWithUser(thisModule, map, essence, user);
		for (Column column : columns) {
			Integer colId = querySource.queryForObject("select nextval(public.\"REPORTING_COLUMNS_ID_SEQ\")",
					EMPTY_PARAMS, Integer.class);

			Map<String, Object> colMap = new HashedMap<String, Object>();
			colMap.put("id", colId);
			colMap.put("reportId", reportId);
			colMap.put("columnId", column.getColumnId());
			colMap.put("typeId", column.getTypeId());
			colMap.put("altName", column.getAltName());
			colMap.put("values", column.getValues());
			colMap.put("description", column.getDescription());
			colMap.put("isActive", column.getIsActive() == false ? 0 : 1);

			querySource.update(SQL_COL, colMap);

			colMap.put("versionId", versionId);

			querySource.update(SQL_COL_HIST, colMap);
			loggerWithUser(thisModule, colMap, ProtonEssences.CONSTRUCTOR_CREATE_REPORT_PART_COLUMN, user);
		}

		ansible.setValue(guid, "@id - " + reportId + "<br> @version - " + versionId + "<br> Columns count - "
				+ columns.size() + "<br> Conditions count - " + conditions.size());
		for (Condition condition : conditions) {
			Integer condId = querySource.queryForObject("select nextval(public.\"PROTON_REPORTING_CONDITION_ID_SEQ\")",
					EMPTY_PARAMS, Integer.class);

			Map<String, Object> condMap = new HashedMap<String, Object>();
			condMap.put("id", condId);
			condMap.put("columnId", condition.getColumnId());
			condMap.put("description", condition.getDescription());
			condMap.put("condType", condition.getConditionType());
			condMap.put("reportId", reportId);
			condMap.put("values", condition.getValues());
			condMap.put("path", condition.getPath());
			condMap.put("isActive", condition.getIsActive() == false ? 0 : 1);

			querySource.update(SQL_COND, condMap);

			condMap.put("versionId", versionId);

			querySource.update(SQL_COND_HIST, condMap);

			loggerWithUser(thisModule, condMap, ProtonEssences.CONSTRUCTOR_CREATE_REPORT_PART_CONDITION, user);
		}

		ansible.setValue(guid,
				"@id - " + reportId + "<br> @version - " + versionId + "<br> Columns count - " + columns.size()
						+ "<br> Conditions count - " + conditions.size() + "<br> Separations count - "
						+ separators.size());
		for (ConditionSeparator sep : separators) {
			Integer sepId = querySource.queryForObject("select nextval(public.\"REPORTING_SEPARATOR_ID_SEQ\")",
					EMPTY_PARAMS, Integer.class);
			Map<String, Object> mapSep = new HashedMap<String, Object>();
			mapSep.put("id", sepId);
			mapSep.put("reportId", reportId);
			mapSep.put("sepType", sep.getSeparationType());
			mapSep.put("path", sep.getPath());
			mapSep.put("isActive", sep.getIsActive() == false ? 0 : true);

			querySource.update(SQL_COND_SEP, mapSep);

			mapSep.put("versionId", versionId);

			querySource.update(SQL_COND_SEP_HIST, mapSep);

			loggerWithUser(thisModule, mapSep, ProtonEssences.CONSTRUCTOR_CREATE_REPORT_PART_SEPARATION, user);
		}

		ansible.kill(guid);

	}

	public void updateReport(Integer id, final String name, final String description, final Boolean isActive,
			final Integer threadId, UserDetailsProton user, List<Column> columns, List<Condition> conditions,
			List<ConditionSeparator> separators, Boolean isTech, String versionDescription)
			throws ReportNotFoundException {

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", id);

		if (querySource.queryForObject("select count(*) from proton_reporting_config where id = :id", map,
				Integer.class) == 0) {
			throw new ReportNotFoundException(id, user.getUsername());
		}
		Map<String, Integer> versions = generatedVersion(versionDescription, user, id, isTech);
		ProtonEssences essence = ProtonEssences.CONSTRUCTOR_CREATE_REPORT;
		String guid = ansible.geenrateGuid(thisModule);
		AnsibleInformation info = new AnsibleInformation();
		info.setDatetime(new Date());
		info.setDescription("Update report " + id + " @id to version " + versions.get("version") + " and " + isTech
				+ " technical version");
		info.setEssence(ProtonEssences.CONSTRUCTOR_CREATE_REPORT);
		info.setIsActive(true);
		info.setMethod("Authenticate");
		info.setUsername(user.getUsername());
		info.setModule(thisModule);
		info.setValue("@id - " + id + "<br> @version - " + versions.get("version") + "<br> @isTech - " + isTech);

		ansible.addInfo(guid, info);

		map.put("name", name);
		map.put("description", description);
		map.put("isActive", isActive == false ? 0 : true);
		map.put("threadId", threadId);

		querySource.update(" update proton_reporting_config " + " set name = :name, " + " description = :description,"
				+ " is_active = :isActive, " + " thread_id = :threadId " + " where id = :id ", map);

		List<Column> columnAct = reportsConfig.getColumnsByReport(id);

		for (Column ca : columnAct) {
			Boolean exist = false;
			for (Column column : columns) {
				if (column.getColumnId() == ca.getColumnId() && column.getTypeId() == column.getTypeId()) {
					exist = true;
					if (ca.getIsActive() == false) {
						Map<String, Object> mapRet = new HashedMap<String, Object>();
						mapRet.put("altName", column.getAltName());
						mapRet.put("values", column.getValues());
						mapRet.put("description", column.getDescription());
						mapRet.put("reportId", id);
						mapRet.put("id", ca.getId());
						mapRet.put("versionId", versions.get("versionId"));
						mapRet.put("columnId", ca.getColumnId());
						mapRet.put("typeId", ca.getTypeId());

						querySource.update("update proton_reporting_columns " + " set is_active = 1, "
								+ " altName = :altName, " + " values = :values, " + " description = :description,"
								+ " u_date = CURRENT_TIMESTAMP " + " where id = :id", mapRet);

						querySource.update(SQL_COL_HIST, mapRet);
						loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_COLUMN, user);

					} else {
						Map<String, Object> mapRet = new HashedMap<String, Object>();
						mapRet.put("altName", column.getAltName());
						mapRet.put("values", column.getValues());
						mapRet.put("description", column.getDescription());
						mapRet.put("reportId", id);
						mapRet.put("id", ca.getId());
						mapRet.put("versionId", versions.get("versionId"));
						mapRet.put("columnId", ca.getColumnId());
						mapRet.put("typeId", ca.getTypeId());
						mapRet.put("isActive", column.getIsActive());

						querySource.update("update proton_reporting_columns " + " set is_active = :isActive, "
								+ " altName = :altName, " + " values = :values, " + " description = :description,"
								+ " u_date = CURRENT_TIMESTAMP " + " where id = :id", mapRet);

						querySource.update(SQL_COL_HIST, mapRet);
						loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_COLUMN, user);
					}
					columns.remove(column);
				}
			}
			if (!exist) {
				Map<String, Object> mapRet = new HashedMap<String, Object>();
				mapRet.put("id", ca.getId());
				mapRet.put("versionId", versions.get("versionId"));
				querySource.update("update proton_reporting_columns " + " set is_active = 0 ,"
						+ " u_date = CURRENT_TIMESTAMP " + " where id = " + ca.getId(), EMPTY_PARAMS);
				querySource.update(SQL_COL_HIST, mapRet);
				loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_COLUMN, user);
			}
		}

		for (Column column : columns) {
			Integer colId = querySource.queryForObject("select nextval(public.\"REPORTING_COLUMNS_ID_SEQ\")",
					EMPTY_PARAMS, Integer.class);

			Map<String, Object> colMap = new HashedMap<String, Object>();
			colMap.put("id", colId);
			colMap.put("reportId", id);
			colMap.put("columnId", column.getColumnId());
			colMap.put("typeId", column.getTypeId());
			colMap.put("altName", column.getAltName());
			colMap.put("values", column.getValues());
			colMap.put("description", column.getDescription());
			colMap.put("isActive", column.getIsActive() == false ? 0 : 1);

			querySource.update(SQL_COL, colMap);

			colMap.put("versionId", versions.get("versionId"));

			querySource.update(SQL_COL_HIST, colMap);
			loggerWithUser(thisModule, colMap, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_COLUMN, user);
		}

		List<Condition> conditionsAct = reportsConfig.getConditions(id);

		for (Condition ca : conditionsAct) {
			Boolean exist = false;
			for (Condition condition : conditions) {
				if (ca.getColumnId() == condition.getColumnId()
						&& ca.getConditionType() == condition.getConditionType()) {
					exist = true;
					if (!ca.getIsActive()) {
						Map<String, Object> mapRet = new HashedMap<String, Object>();
						mapRet.put("description", condition.getDescription());
						mapRet.put("id", ca.getId());
						mapRet.put("values", condition.getValues());
						mapRet.put("path", condition.getPath());

						querySource.update(" update proton_reporting_conditions " + " set is_active = 1, "
								+ " path = :path, " + " description = :description, " + " \"values\" = :values,"
								+ " u_date = CURRENT_TIMESTAMP" + " where id = :id", mapRet);

						mapRet.put("versionId", versions.get("versionId"));

						querySource.update(SQL_COND_HIST, mapRet);
						loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION,
								user);
					} else {
						Map<String, Object> mapRet = new HashedMap<String, Object>();
						mapRet.put("description", condition.getDescription());
						mapRet.put("id", ca.getId());
						mapRet.put("values", condition.getValues());
						mapRet.put("path", condition.getPath());

						querySource.update(" update proton_reporting_conditions " + " set is_active = 1, "
								+ " path = :path, " + " description = :description, " + " \"values\" = :values,"
								+ " u_date = CURRENT_TIMESTAMP" + " where id = :id", mapRet);

						mapRet.put("versionId", versions.get("versionId"));

						querySource.update(SQL_COND_HIST, mapRet);
						loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION,
								user);
					}

					conditions.remove(condition);
				}
			}
			if (!exist) {
				Map<String, Object> mapRet = new HashedMap<String, Object>();
				mapRet.put("id", ca.getId());
				mapRet.put("versionId", versions.get("versionId"));

				querySource.update(" update proton_reporting_conditions " + " set is_active = 0 " + " where id = :id ",
						mapRet);
				querySource.update(SQL_COND_HIST, mapRet);
				loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION, user);
			}
		}

		for (Condition condition : conditions) {
			Integer condId = querySource.queryForObject("select nextval(public.\"PROTON_REPORTING_CONDITION_ID_SEQ\")",
					EMPTY_PARAMS, Integer.class);

			Map<String, Object> condMap = new HashedMap<String, Object>();
			condMap.put("id", condId);
			condMap.put("columnId", condition.getColumnId());
			condMap.put("description", condition.getDescription());
			condMap.put("condType", condition.getConditionType());
			condMap.put("reportId", id);
			condMap.put("values", condition.getValues());
			condMap.put("path", condition.getPath());
			condMap.put("isActive", condition.getIsActive() == false ? 0 : 1);

			querySource.update(SQL_COND, condMap);

			condMap.put("versionId", versions.get("versionId"));

			querySource.update(SQL_COND_HIST, condMap);
			loggerWithUser(thisModule, condMap, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION, user);

		}

		List<ConditionSeparator> sepCa = reportsConfig.getSeparators(id);

		for (ConditionSeparator cs : sepCa) {
			Boolean exist = false;
			for (ConditionSeparator sep : separators) {
				if (sep.getPath() == cs.getPath() && sep.getSeparationType() == cs.getSeparationType()) {
					exist = true;
					if (!cs.getIsActive()) {
						Map<String, Object> mapRet = new HashedMap<String, Object>();
						mapRet.put("isActive", 1);
						mapRet.put("id", cs.getId());

						querySource.update(" update proton_reporting_conditions_sep " + " set is_active = :isActive "
								+ " where id = :id ", mapRet);

						mapRet.put("versionId", versions.get("versionId"));
						querySource.update(SQL_COND_SEP_HIST, mapRet);
						loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION,
								user);
					}
					separators.remove(sep);
				}
			}

			if (!exist) {
				Map<String, Object> mapRet = new HashedMap<String, Object>();
				mapRet.put("isActive", 0);
				mapRet.put("id", cs.getId());

				querySource.update(
						" update proton_reporting_conditions_sep " + " set is_active = :isActive " + " where id = :id ",
						mapRet);
				mapRet.put("versionId", versions.get("versionId"));
				querySource.update(SQL_COND_SEP_HIST, mapRet);
				loggerWithUser(thisModule, mapRet, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION, user);
			}
		}

		for (ConditionSeparator sep : separators) {
			Integer sepId = querySource.queryForObject("select nextval(public.\"REPORTING_SEPARATOR_ID_SEQ\")",
					EMPTY_PARAMS, Integer.class);
			Map<String, Object> mapSep = new HashedMap<String, Object>();
			mapSep.put("id", sepId);
			mapSep.put("reportId", id);
			mapSep.put("sepType", sep.getSeparationType());
			mapSep.put("path", sep.getPath());
			mapSep.put("isActive", sep.getIsActive() == false ? 0 : true);

			querySource.update(SQL_COND_SEP, mapSep);

			mapSep.put("versionId", versions.get("versionId"));

			querySource.update(SQL_COND_SEP_HIST, mapSep);
			loggerWithUser(thisModule, mapSep, ProtonEssences.CONSTRUCTOR_UPDATE_REPORT_PART_CONDITION, user);
		}

		ansible.kill(guid);

	}

	private Map<String, Integer> generatedVersion(String name, UserDetailsProton user, Integer reportId,
			Boolean isTech) {
		Integer versionId = querySource.queryForObject("select nextval(public.\"PROTON_REPORTING_VERSION_ID_SEQ\")",
				EMPTY_PARAMS, Integer.class);

		Map<String, Object> versionMap = new HashedMap<String, Object>();
		versionMap.put("id", versionId);
		versionMap.put("name", name);
		versionMap.put("creator", user.getUserId());
		versionMap.put("reportId", reportId);
		Integer vs = querySource.queryForObject(
				"select count(*) from proton_reporting_versions where report_id = :reportId", versionMap,
				Integer.class);
		versionMap.put("version", vs + 1);
		versionMap.put("isTech", isTech == true ? 1 : 0);

		querySource.update(
				"insert into proton_reporting_versions"
						+ " values(:id, :name, null, :version, :creator, CURRENT_TIMESTAMP, :isTech, :reportId)",
				versionMap);

		Map<String, Integer> result = new HashedMap<String, Integer>();
		result.put("versionId", versionId);
		result.put("version", vs + 1);
		loggerWithUser(thisModule, versionMap, ProtonEssences.CONSTRUCTOR_CREATE_REPORT_PART_VERSION, user);
		return result;
	}

	public Boolean checkAccessToReport(Integer reportId, UserDetailsProton user) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("reportId", reportId);
		map.put("userId", user.getUserId());

		Integer personalLinkCheck = querySource.queryForObject(
				"select count(*) from proton_reporting_acess where user_id = :userId and report_id = :reportId", map,
				Integer.class);

		if (personalLinkCheck > 0)
			return true;

		return false;
	}

	public ShareLink generateLinkToShare(Integer reportId, Integer accessType, Date expiredDate, String name,
			String provideTitle, String provideDescription, Integer roleId, List<String> accessUsers, UserDetailsProton user) {
		Integer provideId = querySource.queryForObject("select nextval(public.\"REPORTING_PROVIDE_ID_SEQ\")",
				EMPTY_PARAMS, Integer.class);

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("reportId", reportId);
		map.put("userId", user.getUserId());
		map.put("provideId", provideId);
		map.put("accessType", accessType);
		map.put("expiredDate", expiredDate);
		map.put("name", name);
		map.put("provideTitle", provideTitle);
		map.put("provideDescription", provideDescription);
		map.put("roleId", roleId);
		map.put("createDate", new Date());
		map.put("additionalData", accessUsers.toString());
		querySource.update("insert into proton_reporting_provide_access "
				+ " values (:provideId, :reportId, :accessType, :userId) ", map);
		
		ShareLink shareLink = new ShareLink();
		shareLink.setCreateDate(new Date());
		shareLink.setExpiredDate(expiredDate);
		shareLink.setName(name);
		shareLink.setProvideDescription(provideDescription);
		shareLink.setProvideTitle(provideTitle);
		shareLink.setProvideId(provideId);
		shareLink.setRoleId(roleId);
		
		UUID uid = UUID.randomUUID();
		String link = uid.toString();
		shareLink.setLink(link);
		shareLink.setAdditionalInformation(accessUsers.toString());
		
		querySource.update("insert into proton_reporting_sharelinks (name, provide_title, provide_description, create_datetime, expired_datetime,"
				+ " link, provide_id, role_id, additional_data) "
				+ " values(:name, :provideTitle, :provideDescription, CURRENT_TIMESTAMP, :expiredDate, :link, :provideId, :roleId, :additionalData)", map);
		
		return shareLink;
	}
}
