package com.foxety0f.proton.modules.employees.service;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.employees.dao.IEmployeesDAO;
import com.foxety0f.proton.modules.employees.domain.AlphaUserInformation;
import com.foxety0f.proton.modules.employees.domain.EmployeeTitle;
import com.foxety0f.proton.modules.employees.domain.EmployeesGroup;
import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;

public class EmployeeService implements IEmployeeService {

	private IEmployeesDAO employeeDao;

	private ProtonModules thisModule = ProtonModules.EMPLOYEES;

	public ProtonModules getThisModule() {
		return thisModule;
	}

	public EmployeeService(IEmployeesDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	public List<EmployeesInformation> getEmployeeInformation() {
		return employeeDao.getEmployeeInformation();
	}

	public List<EmployeesGroup> getEmployeeGroup() {
		return employeeDao.getEmployeeGroup();
	}

	public List<EmployeeTitle> getEmployeeTitles() {
		return employeeDao.getEmployeeTitles();
	}

	public void setNewEmployee(Integer idUser, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, Date startDate, UserDetailsProton user) throws UserAlreadyExistException {
		employeeDao.setNewEmployee(idUser, login, idGroup, titleId, pcNumber, placeNumber, ipAddress, user, startDate);
	}

	public void markEmployeeAsActive(Integer employeeId, UserDetailsProton user) {
		employeeDao.markEmployeeAsActive(employeeId, user);
	}

	public void markEmployeeAsInactive(Integer employeeId, UserDetailsProton user) {
		employeeDao.markEmployeeAsInactive(employeeId, user);
	}

	public List<EmployeesInformation> getEmployeeInformation(String login, Boolean lastRow) {
		return employeeDao.getEmployeeInformation(login, lastRow);
	}

	public void updateInformation(Integer employeeId, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, Date startDate, Date endDate, UserDetailsProton user)
			throws UserNotFound {
		employeeDao.updateInformation(employeeId, login, idGroup, titleId, pcNumber, placeNumber, ipAddress, startDate,
				endDate, user);

	}

	public List<AlphaUserInformation> getAlphaUsers() {
		return employeeDao.getAlphaUsers();
	}
	
	public void setNewGroup(String name, String description, UserDetailsProton user) {
		employeeDao.setNewGroup(name, description, user);
	}

	public void setNewTitle(String name, String description, UserDetailsProton user) {
		employeeDao.setNewTitle(name, description, user);
	}
	
	public List<EmployeesInformation> getEmployeeInformation(Integer employeeId, Boolean lastRow){
		return employeeDao.getEmployeeInformation(employeeId, lastRow);
	}
}
