package com.foxety0f.proton.modules.hire.service;

import java.util.List;

import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.domain.HiredSkills;

public interface IHiredService {

	List<EmployeeHiredConfig> getEmployeeHiredConfig();

	String getAbout(Integer briefId);

	String getPhone(Integer briefId);

	List<EmployeeHiredExperience> getEmployeeExperience(Integer briefId);

	List<EmployeeHiredSkills> getEmployeeSkills(Integer briefId);

	List<HiredSkills> getHiredSkills();

	List<EmployeeHiredConfig> getEmployeeHiredConfig(Integer userId);

	Integer getUserId(Integer briefId);

}
