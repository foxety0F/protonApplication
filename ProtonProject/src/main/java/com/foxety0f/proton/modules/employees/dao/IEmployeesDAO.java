package com.foxety0f.proton.modules.employees.dao;

import java.util.List;

import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;

public interface IEmployeesDAO {
	
	List<EmployeesInformation> getEmployeeInformation();

}
