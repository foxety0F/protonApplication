package com.foxety0f.proton.modules.employees.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.employees.domain.EmployeeTitle;
import com.foxety0f.proton.modules.employees.domain.EmployeesGroup;
import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;

public class EmployeesDAO extends AbstractDAO implements IEmployeesDAO {

	private Logger LOGGER = LoggerFactory.getLogger(EmployeesDAO.class);

	public EmployeesDAO(DataSource dataSource) {
		super(dataSource);

	}

	private ProtonModules thisModule = ProtonModules.EMPLOYEES;

	public void setNewEmployee(Integer idUser, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress) throws UserAlreadyExistException {
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

		querySource.update(
				"insert into proton_employees_attr(id_employee, beg_date, end_date, id_group, id_title, pc_number, ip_address, place, lastRow)"
						+ "values (:employeeId, CURRENT_DATE, null, :idGroup, :titleId, :pcNumber, :ipAddress, :placeNumber, 1)",
				map);

		logger(thisModule, map, ProtonEssences.INSERT, querySource);
	}

	public void markEmployeeAsInactive(Integer employeeId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("employeeId", employeeId);
		querySource.update("update proton_employees" + "set isActive = 0 " + " where employee_id = :employeeId", map);
		logger(thisModule, map, ProtonEssences.MARK_INACTIVE, querySource);
	}

	public void markEmployeeAsActive(Integer employeeId) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("employeeId", employeeId);
		querySource.update("update proton_employees" + "set isActive = 1" + "where employee_id = :employeeId", map);
		logger(thisModule, map, ProtonEssences.MARK_ACTIVE, querySource);
	}

	public List<EmployeesInformation> getEmployeeInformation() {
		return querySource.query(
				"select emp.id_employee" + " , emp.login" + " , emp.isActive" + " , emp.id_user" + " , attr.id_group"
						+ " , attr.id_title" + " , attr.pc_number" + " , attr.ip_address" + " , attr.place"
						+ " , attr.lastRow" + " , attr.id_row" + " , attr.beg_date" + " , attr.end_date"
						+ " , tl.name_title" + " , gp.name_group" + " from proton_employees emp "
						+ " left join proton_employees_attr attr on emp.id_employee = attr.id_employee"
						+ " left join proton_employee_titles tl on tl.id_title = attr.id_title"
						+ " left join proton_employees_groups gp on gp.id_group = attr.id_group",
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

			return item;
		}

	}

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

	public List<EmployeeTitle> getEmployeeTitles() {
		return querySource.query("select * from proton_employees_titles", new EmployeeTitleRowMapper());
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
				+ " , gr.name_group" + " from proton_employees emp "
				+ " left join proton_employees_attr attr on emp.id_employee = attr.id_employee"
				+ " left join proton_employees_titles tl on tl.id_title = attr.id_title"
				+ " left join proton_employees_groups gp on gp.id_group = attr.id_group" + " where login = :login "
				+ whereLR, map, new EmployeesInformationRowMapper());
	}

	public void updateInformation(Integer employeeId, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, Date startDate, Date endDate) throws UserNotFound {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", employeeId);
		List<EmployeesInformation> emp = getEmployeeInformation(login, true);

		if (emp.size() == 0) {
			logException(thisModule, ProtonEssences.EXCEPTION_UPDATE, "User Not Found", login);
			throw new UserNotFound(login);
		}

		map.put("idGroup", idGroup);
		map.put("idTitle", titleId);
		map.put("pcNumber", pcNumber == null ? emp.get(0).getPcNumber() : pcNumber);
		map.put("placeNumber", placeNumber == null ? emp.get(0).getPlaceNumber() : placeNumber);
		map.put("ipAddress", ipAddress == null ? emp.get(0).getIpAddress() : ipAddress);
		map.put("startDate", startDate == null ? new SimpleDateFormat().format(new Date()) : startDate);
		map.put("endDate", endDate == null ? startDate : endDate);
		map.put("id_row", emp.get(0).getIdRow());

		querySource.update(
				"update employees_attr " + " set endDate = :endDate, lastRow = 0 " + " where id_row = :id_row", map);

		logger(thisModule, map, ProtonEssences.UPDATE, querySource);

		querySource.update(
				"insert into proton_employees_attr(id_employee, beg_date, end_date, id_group, id_title, pc_number, ip_address, place, lastRow)"
						+ "values (:employeeId, :startDate, null, :idGroup, :titleId, :pcNumber, :ipAddress, :placeNumber, 1)",
				map);

		logger(thisModule, map, ProtonEssences.INSERT, querySource);
	}

}
