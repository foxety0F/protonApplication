package com.foxety0f.proton.modules.employees.service;

import java.util.List;

import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.employees.dao.IEmployeesDAO;
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
	
	public List<EmployeesInformation> getEmployeeInformation(){
		return employeeDao.getEmployeeInformation();
	}
}
