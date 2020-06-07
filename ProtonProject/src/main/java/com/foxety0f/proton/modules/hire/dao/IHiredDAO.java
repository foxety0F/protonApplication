package com.foxety0f.proton.modules.hire.dao;

import java.util.List;

import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.domain.HiredSkills;

public interface IHiredDAO {

	List<EmployeeHiredConfig> getEmployeeHiredConfig();

	String getAbout(Integer briefId);

	String getPhone(Integer briefId);

	List<EmployeeHiredExperience> getEmployeeExperience(Integer briefId);

	List<EmployeeHiredSkills> getEmployeeSkills(Integer briefId);

	List<HiredSkills> getHiredSkills();

	List<EmployeeHiredConfig> getEmployeeHiredConfig(Integer userId);

	Integer getUserId(Integer briefId);

	List<EmployeeHiredExperience> getSelfEmployeeExperience(Integer userId);

	List<EmployeeHiredSkills> getSelfEmployeeSkills(Integer userId);

}
