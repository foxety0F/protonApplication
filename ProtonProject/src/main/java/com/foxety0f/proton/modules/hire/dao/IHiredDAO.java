package com.foxety0f.proton.modules.hire.dao;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredContactConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredContacts;
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
	
	void createNewBrief(Long userId, String userPhone, String about);
	
	List<EmployeeHiredContactConfig> getContactConfig();
	
	List<EmployeeHiredContacts> getUserContacts(Integer briefId);

	void updateAbout(Integer briefId, String about);

	void updateExperienceDescription(Integer briefId, Integer expId, String description);

	void updateExperienceTitleName(Integer briefId, Integer expId, String titleName);

	void updateExperienceCompanyName(Integer briefId, Integer expId, String companyName);

	void updateExperienceDateTo(Integer briefId, Integer expId, Date dateTo);

	void updateExperienceDateFrom(Integer briefId, Integer expId, Date dateFrom);

}
