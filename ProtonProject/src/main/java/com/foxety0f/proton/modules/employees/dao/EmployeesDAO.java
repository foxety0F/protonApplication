package com.foxety0f.proton.modules.employees.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.employees.domain.AlphaUserInformation;
import com.foxety0f.proton.modules.employees.domain.EmployeeTitle;
import com.foxety0f.proton.modules.employees.domain.EmployeesGroup;
import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;

/**
 * This DAO using for access to core-database from application.properties
 * <br> Employees Module can :
 * <br> Create new employeer from exists user into database
 * <br> Modify employeer active state (Mark active or inactive)
 * <br> Edit employeer information for exist employees
 * <br> Create new groups (internal hierarchy of employees)
 * <br> Create new title (the internal chain of command the hierarchy of the employees)
 */
public class EmployeesDAO extends AbstractDAO implements IEmployeesDAO {

	private Logger LOGGER = LoggerFactory.getLogger(EmployeesDAO.class);

	/**
	 * DAO constructor
	 * @param DataSource dataSource - connect to core-database from application.properties
	 */
	public EmployeesDAO(DataSource dataSource) {
		super(dataSource);

	}

	private ProtonModules thisModule = ProtonModules.EMPLOYEES;

	/**
	 * Create new Employeer from creating users
	 * @param Integer idUser - unique user Id from app_user
	 * @param String login - user login from app_user
	 * @param Integer idGroup - internal hierarchical identificator of employees
	 * @param Integer titleId - the internal chain of command the hierarchy of the employees
	 * @param String pcNumber - employeer PC number
	 * @param String placeNumber - employeer place number (like 3J39)
	 * @param String ipAddress - employeer ID address
	 * @param UserDetailsProton user - using for identify user modify help
	 * @param Date startDate - employeer hired date
	 */
	public void setNewEmployee(Integer idUser, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, UserDetailsProton user, Date startDate)
			throws UserAlreadyExistException {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("idUser", idUser);
		map.put("login", login);
		map.put("idGroup", idGroup);
		map.put("titleId", titleId);
		map.put("pcNumber", pcNumber);
		map.put("placeNumber", placeNumber);
		map.put("ipAddress", ipAddress);

		Integer cnt = querySource.queryForObject("select count(*) from proton_employees where login = :login", map,
				Integer.class);

		if (cnt > 0) {
			throw new UserAlreadyExistException(login);
		}

		querySource.update("insert into proton_employees(login, isActive, id_User, sys_add_tst)"
				+ "values(:login, 1, :idUser, CURRENT_TIMESTAMP)", map);

		Integer employeeId = querySource.queryForObject("select id_employee from proton_employees where login = :login",
				map, Integer.class);

		map.put("employeeId", employeeId);
		map.put("startDate", startDate == null ? "CURRENT_DATE" : startDate);

		querySource.update(
				"insert into proton_employees_attr(id_employee, beg_date, end_date, id_group, id_title, pc_number, ip_address, place, lastRow)"
						+ "values (:employeeId, :startDate, null, :idGroup, :titleId, :pcNumber, :ipAddress, :placeNumber, 1)",
				map);

		loggerWithUser(thisModule, map, ProtonEssences.INSERT, user);
	}

	/**
	 * Using for change actual status for employeer. Employeer marked as inactive
	 * @param Integer employeeId - internal employee identificator
	 * @param UserDetailsProton user - using for identify user modify help
	 */
	public void markEmployeeAsInactive(Integer employeeId, UserDetailsProton user) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("employeeId", employeeId);
		querySource.update("update proton_employees" + "set isActive = 0 " + " where employee_id = :employeeId", map);
		loggerWithUser(thisModule, map, ProtonEssences.MARK_INACTIVE, user);
	}

	/**
	 * Using for change actual status for employeer. Employeer marked as active
	 * @param Integer employeeId - internal employee identificator
	 * @param UserDetailsProton user - using for identify user modify help
	 */
	public void markEmployeeAsActive(Integer employeeId, UserDetailsProton user) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("employeeId", employeeId);
		querySource.update("update proton_employees" + "set isActive = 1" + "where employee_id = :employeeId", map);
		loggerWithUser(thisModule, map, ProtonEssences.MARK_ACTIVE, user);
	}

	/**
	 * Get all employees information (actial and nonactual rows)
	 * @return List<EmployeesInformation>
	 */
	public List<EmployeesInformation> getEmployeeInformation() {
		return querySource.query("select emp.id_employee" + " , emp.login" + " , emp.isActive" + " , emp.id_user"
				+ " , attr.id_group" + " , attr.id_title" + " , attr.pc_number" + " , attr.ip_address" + " , attr.place"
				+ " , attr.lastRow" + " , attr.id_row" + " , attr.beg_date" + " , attr.end_date" + " , tl.name_title"
				+ " , gp.name_group" + ", au.first_name, au.surname from proton_employees emp "
				+ " left join proton_employees_attr attr on emp.id_employee = attr.id_employee"
				+ " left join proton_employee_titles tl on tl.id_title = attr.id_title"
				+ " left join proton_employees_groups gp on gp.id_group = attr.id_group"
				+ " left join app_user au on au.user_id = emp.id_user" + " order by emp.login, attr.beg_date desc",
				new EmployeesInformationRowMapper());
	}

	private static class EmployeesInformationRowMapper implements RowMapper<EmployeesInformation> {

		@Override
		public EmployeesInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeesInformation item = new EmployeesInformation();

			item.setIdEmployee(rs.getInt("id_employee"));
			item.setLogin(rs.getString("login"));
			item.setIsActive(rs.getInt("isActive") == 1 ? true : false);
			item.setIdGroup(rs.getInt("id_group"));
			item.setTitelId(rs.getInt("id_title"));
			item.setPcNumber(rs.getString("pc_number"));
			item.setIpAddress(rs.getString("ip_address"));
			item.setPlaceNumber(rs.getString("place"));
			item.setIsActualRow(rs.getInt("lastRow") == 1 ? true : false);
			item.setStartDate(rs.getDate("beg_date"));
			item.setEndDate(rs.getDate("end_date"));
			item.setTitelName(rs.getString("name_title"));
			item.setGroupName(rs.getString("name_group"));
			item.setIdRow(rs.getInt("id_row"));
			item.setFirstName(rs.getString("first_name"));
			item.setSurname(rs.getString("surname"));

			return item;
		}

	}

	/**
	 * Get all employees internal hierarchy of employees
	 * @return List<EmployeesGroup>
	 */
	public List<EmployeesGroup> getEmployeeGroup() {
		return querySource.query("select * from proton_employees_groups", new EmployeesGroupRowMapper());
	}

	private static class EmployeesGroupRowMapper implements RowMapper<EmployeesGroup> {

		@Override
		public EmployeesGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeesGroup item = new EmployeesGroup();

			item.setIdGroup(rs.getInt("id_group"));
			item.setNameGroup(rs.getString("name_group"));
			item.setDescription(rs.getString("description"));
			return item;
		}

	}

	/**
	 * Get all employees internal chain of command the hierarchy of the employees
	 * @return List<EmployeesGroup>
	 */
	public List<EmployeeTitle> getEmployeeTitles() {
		return querySource.query("select * from proton_employee_titles", new EmployeeTitleRowMapper());
	}

	private static class EmployeeTitleRowMapper implements RowMapper<EmployeeTitle> {

		@Override
		public EmployeeTitle mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeTitle item = new EmployeeTitle();

			item.setTitleId(rs.getInt(1));
			item.setTitleName(rs.getString(2));
			item.setDescription(rs.getString(3));
			return item;
		}

	}

	/**
	 * Get last rows by employeer (actual information) or all rows by employeer by login
	 * @param String login - employee login
	 * @param Boolean lastRow - last row flag (true - only last row, false - all rows)
	 */
	public List<EmployeesInformation> getEmployeeInformation(String login, Boolean lastRow) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("login", login);

		String whereLR = " and attr.lastRow = 1";

		if (!lastRow) {
			whereLR = "";
		}

		return querySource.query("select emp.id_employee" + " , emp.login" + " , emp.isActive" + " , emp.id_user"
				+ " , attr.id_group" + " , attr.id_title" + " , attr.pc_number" + " , attr.ip_address" + " , attr.place"
				+ " , attr.lastRow" + " , attr.id_row" + " , attr.beg_date" + " , attr.end_date" + " , tl.name_title"
				+ " , gp.name_group" + ", au.first_name, au.surname from proton_employees emp "
				+ " left join proton_employees_attr attr on emp.id_employee = attr.id_employee"
				+ " left join proton_employee_titles tl on tl.id_title = attr.id_title"
				+ " left join proton_employees_groups gp on gp.id_group = attr.id_group"
				+ " left join app_user au on au.user_id = emp.id_user" + " where login = :login "
				+ whereLR + " order by emp.login, attr.beg_date desc", map, new EmployeesInformationRowMapper());
	}
	
	/**
	 * Get last rows by employeer (actual information) or all rows by employeer by id
	 * @param String login - employee login
	 * @param Boolean lastRow - last row flag (true - only last row, false - all rows)
	 */
	public List<EmployeesInformation> getEmployeeInformation(Integer employeeId, Boolean lastRow) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("employeeId", employeeId);

		String whereLR = " and attr.lastRow = 1";

		if (!lastRow) {
			whereLR = "";
		}

		return querySource.query("		select emp.id_employee  \r\n" + 
				"			 , emp.login \r\n" + 
				"			 , emp.isActive  \r\n" + 
				"			 , emp.id_user\r\n" + 
				"			 , attr.id_group  \r\n" + 
				"			 , attr.id_title  \r\n" + 
				"			 , attr.pc_number  \r\n" + 
				"			 , attr.ip_address  \r\n" + 
				"			 , attr.place\r\n" + 
				"			 , attr.lastRow  \r\n" + 
				"			 , attr.id_row  \r\n" + 
				"			 , attr.beg_date  \r\n" + 
				"			 , attr.end_date  \r\n" + 
				"			 , tl.name_title\r\n" + 
				"			 , gp.name_group \r\n" + 
				"			 , au.first_name\r\n" + 
				"			 , au.surname \r\n" + 
				"		  from proton_employees emp \r\n" + 
				"	 left join proton_employees_attr attr on emp.id_employee = attr.id_employee\r\n" + 
				"	 left join proton_employee_titles tl on tl.id_title = attr.id_title\r\n" + 
				"	 left join proton_employees_groups gp on gp.id_group = attr.id_group\r\n" + 
				"	 left join app_user au on au.user_id = emp.id_user  \r\n" + 
				"		 where emp.id_employee = :employeeId\r\n" + 
				whereLR + 
				"	  order by emp.login, attr.beg_date desc", map, new EmployeesInformationRowMapper());
	}

	/**
	 * Update employeer information
	 * @param Integer employeeId - internal unique employee Id
	 * @param String login - user login from app_user
	 * @param Integer idGroup - internal hierarchical identificator of employees
	 * @param Integer titleId - the internal chain of command the hierarchy of the employees
	 * @param String pcNumber - employeer PC number
	 * @param String placeNumber - employeer place number (like 3J39)
	 * @param String ipAddress - employeer ID address
	 * @param Date startDate - start actual information date
	 * @param Date endDate - end actual information date
	 * @param UserDetailsProton user - using for identify user
	 */
	public void updateInformation(Integer employeeId, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, Date startDate, Date endDate, UserDetailsProton user)
			throws UserNotFound {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", employeeId);
		List<EmployeesInformation> emp = getEmployeeInformation(employeeId, true);
		
		

		if (emp.size() == 0) {
			logException(thisModule, ProtonEssences.EXCEPTION_UPDATE, "User Not Found", login);
			throw new UserNotFound(login);
		}

		map.put("idGroup", idGroup);
		map.put("idTitle", titleId);
		map.put("pcNumber", pcNumber);
		map.put("placeNumber", placeNumber);
		map.put("ipAddress", ipAddress);
		map.put("startDate", startDate == null ? new Date() : startDate);
		map.put("endDate", endDate == null ? startDate : endDate);
		map.put("id_row", emp.get(0).getIdRow());

		querySource.update(
				"update proton_employees_attr " + " set end_Date = :endDate, lastRow = 0 " + " where id_row = :id_row", map);

		loggerWithUser(thisModule, map, ProtonEssences.USER_UPDATE, user);

		querySource.update(
				"insert into proton_employees_attr(id_employee, beg_date, end_date, id_group, id_title, pc_number, ip_address, place, lastRow)"
						+ "values (:id, :startDate, null, :idGroup, :idTitle, :pcNumber, :ipAddress, :placeNumber, 1)",
				map);

		loggerWithUser(thisModule, map, ProtonEssences.INSERT_USER_ATTR, user);
	}

	/**
	 * Get all users from database whos not employeer yet
	 * @return List<AlphaUserInformation>
	 */
	public List<AlphaUserInformation> getAlphaUsers() {
		return querySource.query(
				"select * from app_user where user_id not in(select distinct id_user from proton_employees)",
				new AlphaUserInformationRowMapper());
	}

	private static class AlphaUserInformationRowMapper implements RowMapper<AlphaUserInformation> {

		@Override
		public AlphaUserInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
			AlphaUserInformation item = new AlphaUserInformation();

			item.setLogin(rs.getString("user_name"));
			item.setFirstName(rs.getString("first_name"));
			item.setSurname(rs.getString("surname"));
			item.setUserId(rs.getLong("user_id"));

			return item;
		}

	}

	/**
	 * Using for create new internal hierarchy of employees
	 * @param String name - name of group
	 * @param String description - description of group
	 * @param UserDetailsProton user - using for identify user
	 */
	public void setNewGroup(String name, String description, UserDetailsProton user) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("name", name);
		map.put("description", description);

		querySource.update(
				"insert into proton_employees_groups(name_group, description) " + " values(:name, :description)", map);

		loggerWithUser(thisModule, map, ProtonEssences.INSERT, user);
	}

	/**
	 * Using for create new internal chain of command the hierarchy of the employees
	 * @param String name - name of group
	 * @param String description - description of group
	 * @param UserDetailsProton user - using for identify user
	 */
	public void setNewTitle(String name, String description, UserDetailsProton user) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("name", name);
		map.put("description", description);

		querySource.update(
				"insert into proton_employee_titles(name_title, description) " + " values(:name, :description)", map);

		loggerWithUser(thisModule, map, ProtonEssences.INSERT, user);

	}

}
