package com.foxety0f.proton.modules.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.domain.LoadedFiles;
import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.user.AppUser;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.admin.domain.AdminModuleInformation;
import com.foxety0f.proton.modules.modules_config.Module;
import com.foxety0f.proton.modules.roles.service.IRoleService;
import com.foxety0f.proton.utils.EncrytedPasswordUtils;

public class AdminDAO extends AbstractDAO implements IAdminDAO {

	@Autowired
	private IRoleService roleService;

	public AdminDAO(DataSource dataSource) {
		super(dataSource);
		System.err.println(getDatabase());
		System.err.println(initialize());
		roleStruct();
	}

	private ProtonModules thisModule = ProtonModules.ADMIN;

	public Integer checkModules() {
		Integer rs = querySource.queryForObject("select count(*) from proton_modules", EMPTY_PARAMS, Integer.class);
		return rs;
	}

	public String initialize() {
		try {
			Integer md = checkModules();
			return "Init table proton_modules exist!";
		} catch (Exception e) {

			if (getDatabase().contentEquals("PostgreSQL")) {
				generateLogTables();
				Map<String, String> map = new HashedMap<String, String>();
				querySource.update("create table proton_modules(" + "id smallint," + "isActive smallint" + ")",
						EMPTY_PARAMS);
				querySource.update("insert into proton_modules(id, isActive)"
						+ "values (100, 0), (101, 0), (102,0), (103,0), (104,0), (105,0), (106, 0), (107, 0), (108, 0), (109,0), (999,1)",
						EMPTY_PARAMS);
				querySource.update(createPK("proton_modules", "id"), EMPTY_PARAMS);
				map.put("create table proton_modules", "OK");

				querySource.update("create table proton_modules_info("
						+ "id bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "id_module bigint, " + "url varchar(250)," + "road varchar(250),"
						+ "description varchar(3000)" + ")", EMPTY_PARAMS);
				map.put("create table proton_modules_info", "OK");

				querySource.update(createPK("proton_modules_info", "id"), EMPTY_PARAMS);
				map.put("create primary key proton_modules_info_pk(id)", "OK");
				querySource.update("alter table proton_modules_info "
						+ "add constraint PROTON_MODULES_INFO_FK foreign key(id_module) "
						+ "references proton_modules(id)", EMPTY_PARAMS);
				map.put("create foreign key proton_modules_info_fk(id_module)", "OK");

				querySource.update("insert into proton_modules_info(id_module, url, road, description)"
						+ " values(101, '/distribution', 'com.foxety0f.proton.modules.distribution.controllers.DistributionController.distributionInit', ''),"
						+ "(101, '/distribution/putNewDistribution', 'com.foxety0f.proton.modules.distribution.controllers.DistributionController.putNewDistribution', ''),"
						+ "(101, '/distribution/massUpdateDistribution', 'com.foxety0f.proton.modules.distribution.controllers.DistributionController.massUpdateDistribution', ''),"
						+ "(101, '/distribution/distributionStatuses', 'com.foxety0f.proton.modules.distribution.controllers.DistributionController.getStatuses', ''),"
						+ "(101, '/distribution/distributionTimes', 'com.foxety0f.proton.modules.distribution.controllers.DistributionController.getTime', '')",
						EMPTY_PARAMS);
				map.put("Firts fill table proton_modules_info", "OK");

				logger(thisModule, map, ProtonEssences.CREATE_STRUCT, querySource);

				return "Init table proton_modules is create!";
			}

		}

		return null;
	}

	public List<Module> getAllModules() {
		return querySource.query("select * from proton_modules order by id asc", new AllModulesRowMapper());
	}

	private static class AllModulesRowMapper implements RowMapper<Module> {

		@Override
		public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
			Module item = new Module();

			for (ProtonModules md : ProtonModules.values()) {
				if (rs.getInt("id") == md.moduleValue()) {
					item.setModule(md);
					item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
					item.setId(rs.getInt("id"));
				}
			}

			return item;
		}

	}

	public void changeModueStatus(Integer id, UserDetailsProton user) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("id", id);

		querySource.update(
				"update proton_modules set isActive = case when isActive = 0 then 1 else 0 end where id = :id", map);
		loggerWithUser(thisModule, map, ProtonEssences.MARK_INACTIVE_ACTIVE, user);

	}

	public Integer testSQL() {
		return querySource.queryForObject("select * from \"protonApplication\".proton_test limit 1", EMPTY_PARAMS,
				Integer.class);
	}

	public AppUser createNewUser(String username, String firstName, String surname, UserDetailsProton userDetails)
			throws UserAlreadyExistException {
		AppUser user = new AppUser();
		String password = RandomStringUtils.random(10, 0, 40, true, true,
				"qwertyuhjklzxcvmQWERTYFGHJKLZXCVBNM124567890".toCharArray());

		user.setEncryptedPassword(password);
		user.setFirstName(firstName);
		user.setSurname(surname);
		user.setEnabled(true);
		user.setUserName(username);

		Long id = querySource.queryForObject("select max(user_id) from app_user", EMPTY_PARAMS, Long.class);
		user.setUserId(id + 10);

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", user.getUserId());
		map.put("firstName", user.getFirstName());
		map.put("surname", user.getSurname());
		map.put("username", user.getUserName());
		map.put("encrypedPassword", EncrytedPasswordUtils.encrytePassword(password));

		Integer cntUsers = querySource.queryForObject("select count(*) from app_user where user_name = :username", map,
				Integer.class);

		if (cntUsers >= 1) {
			logException(thisModule, ProtonEssences.CREATE_USER_EXCEPTION, "UserAlreadyExistException",
					user.getUserName());
			throw new UserAlreadyExistException(username);
		}

		querySource.update(
				"insert into app_user " + "values(:id, :username, :encrypedPassword, 1, :firstName, :surname, 0)", map);

		querySource.update("insert into user_role(user_id, role_id)" + "values(:id, 2)", map);

		loggerWithUser(thisModule, map, ProtonEssences.CREATE_USER, userDetails);

		return user;

	}

	public void generateLogTables() {

		querySource.update("CREATE SEQUENCE public.\"LoggerNumSequence\"\r\n" + "    INCREMENT 1\r\n"
				+ "    START 100\r\n" + "    MINVALUE 100\r\n" + "    MAXVALUE 99999999999\r\n" + "    CACHE 1;",
				EMPTY_PARAMS);

		Map<ProtonModules, String> gen = loggerMapper();
		Set<ProtonModules> keys = gen.keySet();

		for (ProtonModules key : keys) {
			querySource.update("create table " + gen.get(key) + "(" + "id bigint not null," + "module varchar(70),"
					+ "essence varchar(70)," + "sys_mod_ts timestamp" + ")", EMPTY_PARAMS);
			querySource.update(createPK(gen.get(key), "id"), EMPTY_PARAMS);

			querySource.update("create table " + gen.get(key) + "_attr ("
					+ "id bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1)," + "id_core bigint, "
					+ "module varchar(70)," + "param varchar(250)," + "value varchar(8000)," + "sys_mod_ts timestamp"
					+ ")", EMPTY_PARAMS);
			querySource.update(createPK(gen.get(key) + "_attr", "id"), EMPTY_PARAMS);
		}

		querySource.update("create table z_logger_user(id bigint not null, username varchar(120))", EMPTY_PARAMS);
		querySource.update(createPK("z_logger_user", "id"), EMPTY_PARAMS);
	}

	public String generateDBModuleStruct(Module module) {

		Map<ProtonModules, String> checkMap = new HashedMap<ProtonModules, String>();
		checkMap.put(ProtonModules.ADMIN, "proton_modules");
		checkMap.put(ProtonModules.CONSTRUCTOR, "proton_constr_config");
		checkMap.put(ProtonModules.DISTRIBUTION, "proton_distribution");
		checkMap.put(ProtonModules.EMPLOYEES, "proton_employees");
		checkMap.put(ProtonModules.QUALITY, "proton_quality");
		checkMap.put(ProtonModules.REPORTS, "proton_report_config");
		checkMap.put(ProtonModules.RESFULL, "proton_rest_config");
		checkMap.put(ProtonModules.ROLES, "user_role");
		checkMap.put(ProtonModules.TESTS, "proton_test_config");
		checkMap.put(ProtonModules.HELP, "proton_help_config");

		String checkTableName = checkMap.get(module.getModule());
		System.err.println(checkTableName);

		try {
			Integer ch = checkTableExist(checkTableName);
			System.err.println(ch);
			return "Module " + module.getModule().moduleName() + " already exist!";
		} catch (Exception e) {
			e.getStackTrace();
			if (module.getModule() == ProtonModules.EMPLOYEES) {
				Map<String, String> map = new HashedMap<String, String>();
				querySource.update("create table proton_employees("
						+ "id_employee bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "login varchar(120)," + "isActive smallint," + "id_user bigint," + "sys_add_tst timestamp"
						+ ")", EMPTY_PARAMS);
				map.put("create table proton_employees", "OK");
				querySource.update(createPK("proton_employees", "id_employee"), EMPTY_PARAMS);
				map.put("create primary key proton_employees(id_employee)", "OK");

				querySource.update("create table proton_employees_groups("
						+ "id_group bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "name_group varchar(120)," + "description varchar(400)" + ")", EMPTY_PARAMS);
				map.put("create talbe proton_employees_groups", "OK");

				querySource.update(createPK("proton_employees_groups", "id_group"), EMPTY_PARAMS);
				map.put("create primary key proton_employees_groups(id_group)", "OK");

				querySource.update("create table proton_employee_titles("
						+ "id_title bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "name_title varchar(120)," + "description varchar(400)" + ")", EMPTY_PARAMS);
				map.put("create table proton_employee_titles", "OK");
				querySource.update(createPK("proton_employee_titles", "id_title"), EMPTY_PARAMS);
				map.put("create primary key proton_employee_titles(id_title)", "OK");
				querySource.update("create table proton_employees_attr("
						+ "id_row bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "id_employee bigint, " + "beg_date date, " + "end_date date, " + "id_group bigint,"
						+ "id_title bigint," + "pc_number varchar(120)," + "ip_address varchar(120),"
						+ "place varchar(120), " + "lastRow int" + ")", EMPTY_PARAMS);
				map.put("create table proton_employees_attr", "OK");
				querySource.update(createPK("proton_employees_attr", "id_row"), EMPTY_PARAMS);
				map.put("create primary key proton_employees_attr(id_row)", "OK");
				querySource.update("alter table proton_employees_attr"
						+ " add constraint PROTON_EMPLOYEES_ATTR_FK foreign key (id_employee)"
						+ " references proton_employees(id_employee)", EMPTY_PARAMS);
				map.put("create foreign key proton_employees_attr_fk (id_employee) -> proton_employees(id_employee)",
						"OK");
				querySource.update("alter table proton_employees_attr"
						+ " add constraint PROTON_EMPLOYEES_ATTR_FK2 foreign key (id_group)"
						+ " references proton_employees_groups(id_group)", EMPTY_PARAMS);
				map.put("create foreign key proton_employees_attr_fk2 (id_group) -> proton_employees_groups(id_group)",
						"OK");
				querySource.update("alter table proton_employees_attr"
						+ " add constraint PROTON_EMPLOYEES_ATTR_FK3 foreign key (id_title)"
						+ " references proton_employee_titles(id_title)", EMPTY_PARAMS);
				map.put("create foreign key proton_employees_attr_fk3 (id_title) -> proton_employee_titles(id_title)",
						"OK");
				logger(thisModule, map, ProtonEssences.CREATE_STRUCT, querySource);
				return "Create dictionaryes : proton_employee_groups, proton_employee_titles \n"
						+ "Create attribute table : proton_employees_attr \n" + "Create core table : proton_employees";
			} else if (module.getModule() == ProtonModules.DISTRIBUTION) {
				Map<String, String> map = new HashedMap<String, String>();
				querySource.update("create table proton_distr_status("
						+ "id_distr_status bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "name varchar(120)," + "description varchar(120)," + "color varchar(50)," + "isActive int,"
						+ "isWorkDay int" + ")", EMPTY_PARAMS);
				map.put("create table proton_distr_status", "OK");
				querySource.update(createPK("proton_distr_status", "id_distr_status"), EMPTY_PARAMS);
				map.put("create primary key proton_distr_status(id_distr_status)", "OK");

				querySource.update("create table proton_distr_time("
						+ "id_distr_time bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "time_value_min smallint," + "time_value_string varchar(30)," + "color varchar(30),"
						+ "isActive int" + ")", EMPTY_PARAMS);

				map.put("create table proton_distr_time", "OK");
				querySource.update(createPK("proton_distr_time", "id_distr_time"), EMPTY_PARAMS);
				map.put("create primary key proton_distr_time(id_distr_time)", "OK");
				querySource.update("create table proton_distr_auto("
						+ "id_row bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "id_employee bigint," + "monday int," + "mondayIsWorked int," + "tuesday int,"
						+ "tuesdayIsWorked int," + "wednesday int," + "wednesdayIsWorked int," + "thursday int,"
						+ "thursdayIsWorked int," + "friday int," + "fridayIsWorked int," + "saturday int,"
						+ "saturdayIsWorked int," + "sunday int," + "sundayIsWorked int" + ")", EMPTY_PARAMS);
				map.put("create table proton_distr_auto", "OK");
				querySource.update(createPK("proton_distr_auto", "id_row"), EMPTY_PARAMS);
				map.put("create primary key proton_distr_auto(id_row)", "OK");
				querySource.update("alter table proton_distr_auto"
						+ " add constraint PROTON_DISTR_AUTO_FK foreign key (id_employee)"
						+ " references proton_employees(id_employee)", EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK(id_employee) -> references proton_employees(id_employee)",
						"OK");
				querySource.update(
						"alter table proton_distr_auto" + " add constraint PROTON_DISTR_AUTO_FK2 foreign key (monday)"
								+ " references proton_distr_time(id_distr_time)",
						EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK2(monday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update(
						"alter table proton_distr_auto" + " add constraint PROTON_DISTR_AUTO_FK3 foreign key (tuesday)"
								+ " references proton_distr_time(id_distr_time)",
						EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK3(tuesday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update("alter table proton_distr_auto"
						+ " add constraint PROTON_DISTR_AUTO_FK4 foreign key (wednesday)"
						+ " references proton_distr_time(id_distr_time)", EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK4(wednesday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update(
						"alter table proton_distr_auto" + " add constraint PROTON_DISTR_AUTO_FK5 foreign key (thursday)"
								+ " references proton_distr_time(id_distr_time)",
						EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK5(thursday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update(
						"alter table proton_distr_auto" + " add constraint PROTON_DISTR_AUTO_FK6 foreign key (friday)"
								+ " references proton_distr_time(id_distr_time)",
						EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK6(friday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update(
						"alter table proton_distr_auto" + " add constraint PROTON_DISTR_AUTO_FK7 foreign key (saturday)"
								+ " references proton_distr_time(id_distr_time)",
						EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK7(saturday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update(
						"alter table proton_distr_auto" + " add constraint PROTON_DISTR_AUTO_FK8 foreign key (sunday)"
								+ " references proton_distr_time(id_distr_time)",
						EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTR_AUTO_FK8(sunday) -> references proton_distr_time(id_distr_time)",
						"OK");
				querySource.update("create table proton_distribution("
						+ "id_row bigint GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1),"
						+ "id_employee bigint, " + "rep_date date, " + "id_status bigint," + "id_time bigint, "
						+ "description varchar(300)" + ")", EMPTY_PARAMS);
				map.put("create table proton_distribution", "OK");
				querySource.update(createPK("proton_distribution", "id_row"), EMPTY_PARAMS);
				map.put("create primary key proton_distribution(id_row)", "OK");
				querySource.update("alter table proton_distribution"
						+ " add constraint PROTON_DISTRIBUTION_FK foreign key (id_employee)"
						+ " references proton_employees(id_employee)", EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTRIBUTION_FK(id_employee) -> references proton_employees(id_employee)",
						"OK");
				querySource.update("alter table proton_distribution"
						+ " add constraint PROTON_DISTRIBUTION_FK1 foreign key (id_status)"
						+ " references proton_distr_status(id_distr_status)", EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTRIBUTION_FK1(id_status) -> references proton_distr_status(id_distr_status)",
						"OK");
				querySource.update("alter table proton_distribution"
						+ " add constraint PROTON_DISTRIBUTION_FK2 foreign key (id_time)"
						+ " references proton_distr_time(id_distr_time)", EMPTY_PARAMS);
				map.put("create foreign key PROTON_DISTRIBUTION_FK2(id_time) -> references proton_distr_time(id_distr_time)",
						"OK");
				logger(thisModule, map, ProtonEssences.CREATE_STRUCT, querySource);
				return "Create dictionaryes : proton_distr_status, proton_distr_time, proton_distr_auto \n"
						+ "Create core table : proton_distribution";

			} else if (module.getModule() == ProtonModules.ROLES) {

				Integer cnt = querySource.queryForObject(
						"select count(*) from app_role where role_name = 'ROLE_MANAGER_ROLES'", EMPTY_PARAMS,
						Integer.class);

				if (cnt > 0) {
					return "Role already created";
				}

				Map<String, String> map = new HashedMap<String, String>();
				roleService.createNewRole("ROLE_MANAGER_ROLES");

				map.put("add new role", "ROLE_MANAGER_ROLES");

				logger(thisModule, map, ProtonEssences.CREATE_STRUCT, null);

				return "Create new role for MANAGER_ROLES";

			} else if (module.getModule() == ProtonModules.HELP) {
				Integer cnt = querySource.queryForObject(
						"select count (*) from app_role where role_name = 'ROLE_HELP_EDITOR'", EMPTY_PARAMS,
						Integer.class);
				Map<String, String> map = new HashedMap<String, String>();

				if (cnt > 0) {
					roleService.createNewRole("ROLE_HELP_EDITOR");
					map.put("add new role", "ROLE_HELP_EDITOR");
				}

				querySource.update("CREATE TABLE proton_help_types\r\n" + "(\r\n"
						+ "    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),\r\n"
						+ "    type_name character varying(120),\r\n"
						+ "    type_description character varying(250) ,\r\n"
						+ "    CONSTRAINT proton_help_types_pkey PRIMARY KEY (id)\r\n" + ")", EMPTY_PARAMS);
				map.put("Create table proton_help_types", "OK");
				map.put("Create PROTON_HELP_TYPES_PKEY primary key (id)", "OK");
				Map<String, String> mapIns = new HashedMap<String, String>();
				mapIns.put("typeName", "text-core-page");
				mapIns.put("typeDescription", "This type for core page descriptions");
				querySource.update("INSERT INTO public.proton_help_types(\r\n" + "	type_name, type_description)\r\n"
						+ "	VALUES ( :typeName, :typeDescription);", mapIns);
				map.put("Create row default type help", "OK");

				querySource.update("CREATE TABLE proton_help_config\r\n" + "(\r\n"
						+ "    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 101 MINVALUE 101 MAXVALUE 9999999999999 CACHE 1 ),\r\n"
						+ "    help_name character varying(120) NOT NULL,\r\n"
						+ "    help_description character varying(800) ,\r\n"
						+ "    request_url character varying(500) ,\r\n" + "    ref_type_id bigint,\r\n"
						+ "    help_text text ,\r\n" + "    CONSTRAINT proton_help_config_pkey PRIMARY KEY (id),\r\n"
						+ "    CONSTRAINT \"HELP_TYPE_FK\" FOREIGN KEY (ref_type_id)\r\n"
						+ "        REFERENCES proton_help_types (id) MATCH SIMPLE\r\n"
						+ "        ON UPDATE NO ACTION\r\n" + "        ON DELETE NO ACTION\r\n"
						+ "        NOT VALID\r\n" + ")", EMPTY_PARAMS);
				map.put("Create table proton_help_config", "OK");
				map.put("Create proton_help_config_pkey primary key (id)", "OK");
				map.put("Create HELP_TYPE_FK foreign key (ref_type_id) -> references to proton_help_types(id)", "OK");

				logger(thisModule, map, ProtonEssences.CREATE_STRUCT, null);

				return "Module Help was created";

			} else {
				return "Sorry, but this module not applicable now!";
			}
		}
	}

	public Integer checkTableExist(String tableName) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("tableName", tableName);

		return querySource.queryForObject("select count(*) from " + tableName, map, Integer.class);
	}

	public void roleStruct() {
		try {
			Integer ch = checkTableExist("APP_USER");
		} catch (Exception e) {
			querySource.update("create table APP_USER\r\n" + "(\r\n" + "  USER_ID           BIGINT not null,\r\n"
					+ "  USER_NAME         VARCHAR(36) not null,\r\n" + "  ENCRYTED_PASSWORD VARCHAR(128) not null,\r\n"
					+ "  ENABLED           Int not null, first_name varchar(120), surname varchar(120), is_first_time int \r\n"
					+ ") ;\r\n" + "--  \r\n" + "alter table APP_USER\r\n"
					+ "  add constraint APP_USER_PK primary key (USER_ID);\r\n" + " \r\n" + "alter table APP_USER\r\n"
					+ "  add constraint APP_USER_UK unique (USER_NAME);\r\n" + " \r\n" + " \r\n" + "-- Create table\r\n"
					+ "create table APP_ROLE\r\n" + "(\r\n" + "  ROLE_ID   BIGINT not null,\r\n"
					+ "  ROLE_NAME VARCHAR(30) not null\r\n" + ") ;\r\n" + "--  \r\n" + "alter table APP_ROLE\r\n"
					+ "  add constraint APP_ROLE_PK primary key (ROLE_ID);\r\n" + " \r\n" + "alter table APP_ROLE\r\n"
					+ "  add constraint APP_ROLE_UK unique (ROLE_NAME);\r\n" + " \r\n" + " \r\n" + "-- Create table\r\n"
					+ "create table USER_ROLE\r\n" + "(\r\n"
					+ "   id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 999999999 CACHE 1 ),\r\n"
					+ "  USER_ID BIGINT not null,\r\n" + "  ROLE_ID BIGINT not null\r\n" + ");\r\n" + "--  \r\n"
					+ "alter table USER_ROLE\r\n" + "  add constraint USER_ROLE_PK primary key (ID);\r\n" + " \r\n"
					+ "alter table USER_ROLE\r\n" + "  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);\r\n"
					+ " \r\n" + "alter table USER_ROLE\r\n" + "  add constraint USER_ROLE_FK1 foreign key (USER_ID)\r\n"
					+ "  references APP_USER (USER_ID);\r\n" + " \r\n" + "alter table USER_ROLE\r\n"
					+ "  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)\r\n"
					+ "  references APP_ROLE (ROLE_ID);\r\n" + " \r\n" + "  \r\n" + "  \r\n"
					+ "-- Used by Spring Remember Me API.  \r\n" + "CREATE TABLE Persistent_Logins (\r\n" + " \r\n"
					+ "    username varchar(64) not null,\r\n" + "    series varchar(64) not null,\r\n"
					+ "    token varchar(64) not null,\r\n" + "    last_used timestamp not null,\r\n"
					+ "    PRIMARY KEY (series)\r\n" + "     \r\n" + ");\r\n" + "  \r\n"
					+ "--------------------------------------\r\n" + " \r\n"
					+ "insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED, first_name, surname, is_first_time)\r\n"
					+ "values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1, null, null, 0);\r\n"
					+ " \r\n" + "insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED, first_name, surname, is_first_time)\r\n"
					+ "values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1, null, null, 0);\r\n"
					+ " \r\n" + "---\r\n" + " \r\n" + "insert into app_role (ROLE_ID, ROLE_NAME)\r\n"
					+ "values (1, 'ROLE_ADMIN');\r\n" + " \r\n" + "insert into app_role (ROLE_ID, ROLE_NAME)\r\n"
					+ "values (2, 'ROLE_USER');\r\n" + " \r\n" + "---\r\n" + " \r\n"
					+ "insert into user_role ( USER_ID, ROLE_ID)\r\n" + "values ( 1, 1);\r\n" + " \r\n"
					+ "insert into user_role ( USER_ID, ROLE_ID)\r\n" + "values ( 1, 2);\r\n" + " \r\n"
					+ "insert into user_role ( USER_ID, ROLE_ID)\r\n" + "values ( 2, 2);\r\n" + "---\r\n"
					+ "Commit;", EMPTY_PARAMS);
		}

	}

	public List<AdminModuleInformation> getModulesInformation() {
		return querySource.query("	select pm.id\r\n" + "		 , pm.isActive\r\n" + "		 , pmi.id id_row\r\n"
				+ "		 , pmi.url\r\n" + "		 , pmi.road\r\n" + "		 , pmi.description\r\n"
				+ "	  from proton_modules pm\r\n" + " left join proton_modules_info pmi on pm.id = pmi.id_module",
				new AdminModuleInformationRowMapper());
	}

	private static class AdminModuleInformationRowMapper implements RowMapper<AdminModuleInformation> {

		@Override
		public AdminModuleInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminModuleInformation item = new AdminModuleInformation();

			for (ProtonModules md : ProtonModules.values()) {
				if (rs.getInt("id") == md.moduleValue()) {
					item.setModule(md);
				}
			}

			item.setIsActive(rs.getInt("isActive") == 0 ? false : true);
			item.setRoad(rs.getString("road"));
			item.setUrl(rs.getString("url"));
			item.setDescription(rs.getString("description"));
			item.setIdRow(rs.getInt("id_row"));

			return item;
		}

	}

	public void loggerWithUserSuper(ProtonModules module, Map<String, ?> params, ProtonEssences essence,
			UserDetailsProton user) {
		loggerWithUser(module, params, essence, user);
	}

	public void uploadFiles(byte[] file, String fileName, Long userId, ProtonModules module) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("file", file);
		map.put("fileName", fileName);
		map.put("userId", userId);
		map.put("module_id", module.moduleValue());
		querySource.update("insert into proton_data_files(name, data, module_id, user_id)"
				+ " values (:fileName, :file, :module_id, :userId)", map);
	}

	public List<LoadedFiles> getFiles() {
		return querySource.query("select * from proton_data_files", new LoadedFilesRowMapper());
	}

	private static class LoadedFilesRowMapper implements RowMapper<LoadedFiles> {

		@Override
		public LoadedFiles mapRow(ResultSet rs, int rowNum) throws SQLException {
			LoadedFiles item = new LoadedFiles();

			item.setFileId(rs.getInt(1));
			item.setFileName(rs.getString("name"));
			item.setByteArray(rs.getBytes("data"));

			return item;
		}

	}
	
	public List<Map<String, Object>> getTempTestData(){
		return querySource.queryForList("select * from temp_test_data", EMPTY_PARAMS);
	}

}
