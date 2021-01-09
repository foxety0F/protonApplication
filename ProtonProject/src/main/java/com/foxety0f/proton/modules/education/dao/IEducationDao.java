package com.foxety0f.proton.modules.education.dao;

import java.util.Date;
import java.util.List;

import com.foxety0f.proton.modules.education.domain.TempTestData;
import com.foxety0f.proton.modules.education.domain.TestApi;

public interface IEducationDao {

    List<TestApi> getTestApi();
    
    void setTestApi(Integer id, String name, Date date, Boolean isActual);
    
    List<TempTestData> getTempTestData();

    void setCnt(Integer cnt);

    Integer getCnt();

}
