package com.foxety0f.proton.modules.hire.service;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.modules.hire.dao.IHiredDAO;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredContactConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredContacts;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.domain.HiredSkills;
import com.foxety0f.proton.modules.hire.exceptions.UpdateAboutAccessDenied;

public class HiredService implements IHiredService {

    private IHiredDAO hiredDao;

    public HiredService(IHiredDAO hiredDao) {
	this.hiredDao = hiredDao;
    }

    public List<EmployeeHiredConfig> getEmployeeHiredConfig() {
	return hiredDao.getEmployeeHiredConfig();
    }

    public String getAbout(Integer briefId) {
	return hiredDao.getAbout(briefId);
    }

    public String getPhone(Integer briefId) {
	return hiredDao.getPhone(briefId);
    }

    public List<EmployeeHiredExperience> getEmployeeExperience(Integer briefId) {
	return hiredDao.getEmployeeExperience(briefId);
    }

    public List<EmployeeHiredSkills> getEmployeeSkills(Integer briefId) {
	return hiredDao.getEmployeeSkills(briefId);
    }

    public List<HiredSkills> getHiredSkills() {
	return hiredDao.getHiredSkills();
    }

    public List<EmployeeHiredConfig> getEmployeeHiredConfig(Integer userId) {
	return hiredDao.getEmployeeHiredConfig(userId);
    }

    public Integer getUserId(Integer briefId) {
	return hiredDao.getUserId(briefId);
    }

    public void createNewBrief(Long userId, String userPhone, String about) {
	hiredDao.createNewBrief(userId, userPhone, about);
    }

    public List<EmployeeHiredContactConfig> getContactConfig() {
	return hiredDao.getContactConfig();
    }

    public List<EmployeeHiredContacts> getUserContacts(Integer briefId) {
	return hiredDao.getUserContacts(briefId);
    }

    public String updateAbout(Integer briefId, String about, Long requestorId) throws UpdateAboutAccessDenied {
	Integer userId = hiredDao.getUserId(briefId);
	if (userId.longValue() == requestorId) {
	    hiredDao.updateAbout(briefId, about);
	    return "About was updated!";
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }

    public void updateAboutAdmin(Integer briefId, String about, Long adminId) {
	hiredDao.updateAbout(briefId, about);
    }

    @Override
    public String updateSocialInfo(Integer socialId, String value, Integer briefId, Long requestorId, Integer contactId)
	    throws UpdateAboutAccessDenied {
	Integer userId = hiredDao.getUserId(briefId);
	if (userId.longValue() == requestorId) {
	    if (contactId != null) {
		hiredDao.updateSocialInfo(contactId, value);
		return "Update social info";
	    } else {
		hiredDao.updateSocialInfo(socialId, value, briefId);
		return "Create new social info";
	    }
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }

    @Override
    public String updateSocialInfoAdmin(Integer socialId, String value, Integer briefId, Long requestorId,
	    Integer contactId) {
	if (contactId != null) {
	    hiredDao.updateSocialInfo(contactId, value);
	    return "Update social info";
	} else {
	    hiredDao.updateSocialInfo(socialId, value, briefId);
	    return "Create new social info";
	}
    }
    
    public void updateExperienceDescription(Integer briefId, Integer expId, String description, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied{
	Integer userId = hiredDao.getUserId(briefId);
	Boolean access = isAdmin == false ? userId.longValue() == requestorId : isAdmin;
	if (access) {
	    hiredDao.updateExperienceDescription(briefId, expId, description);
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }
    
    public void updateExperienceTitleName(Integer briefId, Integer expId, String titleName, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied{
	Integer userId = hiredDao.getUserId(briefId);
	Boolean access = isAdmin == false ? userId.longValue() == requestorId : isAdmin;
	if (access) {
	    hiredDao.updateExperienceTitleName(briefId, expId, titleName);
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }
    
    public void updateExperienceCompanyName(Integer briefId, Integer expId, String companyName, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied {
	Integer userId = hiredDao.getUserId(briefId);
	Boolean access = isAdmin == false ? userId.longValue() == requestorId : isAdmin;
	if (access) {
	    hiredDao.updateExperienceCompanyName(briefId, expId, companyName);
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }
    
    public void updateExperienceDateTo(Integer briefId, Integer expId, Date dateTo, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied {
	Integer userId = hiredDao.getUserId(briefId);
	Boolean access = isAdmin == false ? userId.longValue() == requestorId : isAdmin;
	if (access) {
	    hiredDao.updateExperienceDateTo(briefId, expId, dateTo);
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }
    
    public void updateExperienceDateFrom(Integer briefId, Integer expId, Date dateFrom, Long requestorId, Boolean isAdmin) throws UpdateAboutAccessDenied {
	Integer userId = hiredDao.getUserId(briefId);
	Boolean access = isAdmin == false ? userId.longValue() == requestorId : isAdmin;
	if (access) {
	    hiredDao.updateExperienceDateFrom(briefId, expId, dateFrom);
	} else {
	    throw new UpdateAboutAccessDenied(briefId, userId, requestorId);
	}
    }

}
