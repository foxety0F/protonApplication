package com.foxety0f.proton.modules.employees.dao;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.employees.domain.AlphaUserInformation;
import com.foxety0f.proton.modules.employees.domain.EmployeeTitle;
import com.foxety0f.proton.modules.employees.domain.EmployeesGroup;
import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;

public interface IEmployeesDAO {

	List<EmployeesInformation> getEmployeeInformation();

	List<EmployeesGroup> getEmployeeGroup();

	List<EmployeeTitle> getEmployeeTitles();

	void setNewEmployee(Integer idUser, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, UserDetailsProton user) throws UserAlreadyExistException;

	void markEmployeeAsActive(Integer employeeId, UserDetailsProton user);

	void markEmployeeAsInactive(Integer employeeId, UserDetailsProton user);

	List<EmployeesInformation> getEmployeeInformation(String login, Boolean lastRow);

	void updateInformation(Integer employeeId, String login, Integer idGroup, Integer titleId, String pcNumber,
			String placeNumber, String ipAddress, Date startDate, Date endDate, UserDetailsProton user)
			throws UserNotFound;

	List<AlphaUserInformation> getAlphaUsers();
}
