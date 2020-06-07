package com.foxety0f.proton.modules.admin.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
		System.err.println(initialize());
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
				String details = "";
				File file = new File(getClass().getClassLoader()
						.getResource("sql-gen/modules/admin/admin-generate-struct.sql").getFile());
				try {
					Scanner scanner = new Scanner(file);

					while (scanner.hasNextLine()) {
						details += scanner.nextLine() + "\n";
					}
					scanner.close();

					querySource.update(details, EMPTY_PARAMS);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				details = "";
				File fileData = new File(
						getClass().getClassLoader().getResource("sql-gen/modules/admin/admin-data.sql").getFile());
				try {
					Scanner scanner = new Scanner(fileData);

					while (scanner.hasNextLine()) {
						details += scanner.nextLine() + "\n";
					}
					scanner.close();

					querySource.update(details, EMPTY_PARAMS);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				map.put("create table proton_modules", "OK");
				map.put("create table proton_modules_info", "OK");
				map.put("create primary key proton_modules_info_pk(id)", "OK");
				map.put("create foreign key proton_modules_info_fk(id_module)", "OK");
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
		String password = RandomStringUtils.random(10, 0, 54, true, true,
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

	public void generateSyntheticData() {
		try {
			Integer i = querySource.queryForObject("select count(*) from temp_test_data", EMPTY_PARAMS, Integer.class);
		} catch (Exception e) {
			String details = "";
			File file = new File(getClass().getClassLoader()
					.getResource("sql-gen/modules/synthetic-data/synthetic-data.sql").getFile());
			try {
				Scanner scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					details += scanner.nextLine() + "\n";
				}
				scanner.close();

				querySource.update(details, EMPTY_PARAMS);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
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
			String details = "";
			File file = new File(
					getClass().getClassLoader()
							.getResource("sql-gen/modules/" + module.getModule().moduleName().toLowerCase() + "/"
									+ module.getModule().moduleName().toLowerCase() + "-generate-struct.sql")
							.getFile());
			try {
				Scanner scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					details += scanner.nextLine() + "\n";
				}
				scanner.close();

				querySource.update(details, EMPTY_PARAMS);
			} catch (FileNotFoundException e1) {
				//e1.printStackTrace();
			}
			
			System.err.println(details);

			details = "";
			File fileData = new File(getClass().getClassLoader()
					.getResource("sql-gen/modules/" + module.getModule().moduleName().toLowerCase() + "/"
							+ module.getModule().moduleName().toLowerCase() + "-data.sql")
					.getFile());
			try {
				Scanner scanner = new Scanner(fileData);

				while (scanner.hasNextLine()) {
					details += scanner.nextLine() + "\n";
				}
				scanner.close();

				querySource.update(details, EMPTY_PARAMS);
			} catch (FileNotFoundException e1) {
				//e1.printStackTrace();
			}
			return "Module " + module.getModule().moduleName().toLowerCase() + " was generated";
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
					+ " \r\n"
					+ "insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED, first_name, surname, is_first_time)\r\n"
					+ "values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1, null, null, 0);\r\n"
					+ " \r\n" + "---\r\n" + " \r\n" + "insert into app_role (ROLE_ID, ROLE_NAME)\r\n"
					+ "values (1, 'ROLE_ADMIN');\r\n" + " \r\n" + "insert into app_role (ROLE_ID, ROLE_NAME)\r\n"
					+ "values (2, 'ROLE_USER');\r\n" + " \r\n" + "---\r\n" + " \r\n"
					+ "insert into user_role ( USER_ID, ROLE_ID)\r\n" + "values ( 1, 1);\r\n" + " \r\n"
					+ "insert into user_role ( USER_ID, ROLE_ID)\r\n" + "values ( 1, 2);\r\n" + " \r\n"
					+ "insert into user_role ( USER_ID, ROLE_ID)\r\n" + "values ( 2, 2);\r\n" + "---\r\n" + "Commit;",
					EMPTY_PARAMS);
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

	public List<Map<String, Object>> getTempTestData() {
		return querySource.queryForList("select * from temp_test_data", EMPTY_PARAMS);
	}

}
