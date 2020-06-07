package com.foxety0f.proton.modules.hire.service;

import java.util.List;

import com.foxety0f.proton.modules.hire.dao.IHiredDAO;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.domain.HiredSkills;

public class HiredService implements IHiredService{
	
	private IHiredDAO hiredDao;
	
	public HiredService(IHiredDAO hiredDao) {
		this.hiredDao = hiredDao;
	}
	
	public List<EmployeeHiredConfig> getEmployeeHiredConfig(){
		return hiredDao.getEmployeeHiredConfig();
	}

	public String getAbout(Integer briefId) {
		return hiredDao.getAbout(briefId);
	}

	public String getPhone(Integer briefId) {
		return hiredDao.getPhone(briefId);
	}

	public List<EmployeeHiredExperience> getEmployeeExperience(Integer briefId){
		return hiredDao.getEmployeeExperience(briefId);
	}

	public List<EmployeeHiredSkills> getEmployeeSkills(Integer briefId){
		return hiredDao.getEmployeeSkills(briefId);
	}
	
	public List<HiredSkills> getHiredSkills(){
		return hiredDao.getHiredSkills();
	}
	
	public List<EmployeeHiredConfig> getEmployeeHiredConfig(Integer userId){
		return hiredDao.getEmployeeHiredConfig(userId);
	}
	
	public Integer getUserId(Integer briefId) {
		return hiredDao.getUserId(briefId);
	}
	
	public List<EmployeeHiredExperience> getSelfEmployeeExperience(Integer userId){
		return hiredDao.getSelfEmployeeExperience(userId);
	}

	public List<EmployeeHiredSkills> getSelfEmployeeSkills(Integer userId){
		return hiredDao.getSelfEmployeeSkills(userId);
	}

}
