package com.foxety0f.proton.modules.hire.service;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredContactConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredContacts;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.domain.HiredSkills;
import com.foxety0f.proton.modules.hire.exceptions.UpdateAboutAccessDenied;

public interface IHiredService {

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

    void updateAboutAdmin(Integer briefId, String about, Long adminId); 

    String updateAbout(Integer briefId, String about, Long requestorId) throws UpdateAboutAccessDenied;

    String updateSocialInfo(Integer socialId, String value, Integer briefId, Long requestorId, Integer contactId)
	    throws UpdateAboutAccessDenied;

    String updateSocialInfoAdmin(Integer socialId, String value, Integer briefId, Long requestorId, Integer contactId);
    
    void updateExperienceDescription(Integer briefId, Integer expId, String description, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied;

    void updateExperienceTitleName(Integer briefId, Integer expId, String titleName, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied;

    void updateExperienceCompanyName(Integer briefId, Integer expId, String companyName, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied;

    void updateExperienceDateTo(Integer briefId, Integer expId, Date dateTo, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied;

    void updateExperienceDateFrom(Integer briefId, Integer expId, Date dateFrom, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied;

}
