package com.foxety0f.proton.modules.education.service;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.modules.education.dao.IEducationDao;
import com.foxety0f.proton.modules.education.domain.TempTestData;
import com.foxety0f.proton.modules.education.domain.TestApi;

public class EducationService implements IEducationService{

    private IEducationDao educationDao;
    
    public EducationService(IEducationDao educationDao) {
	this.educationDao = educationDao;
    }
    
    public  List<TestApi> getTestApi(){
	return educationDao.getTestApi();
    }
    
    public void setTestApi(Integer id, String name, Date date, Boolean isActual) {
	educationDao.setTestApi(id, name, date, isActual);
    }
    
    public List<TempTestData> getTempTestData(){
	return educationDao.getTempTestData();
    }
    
    public void setCnt(Integer cnt) {
	educationDao.setCnt(cnt);
    }

    public Integer getCnt() {
	return educationDao.getCnt();
    }
}
