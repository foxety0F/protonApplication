package com.foxety0f.proton.modules.constructor.service;

import java.util.List;

import com.foxety0f.proton.modules.constructor.domain.initial.CreateConstructorInitial;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorSystemInformation;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTables;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTablesRelations;

public interface IReportWebService {

    List<ConstructorSystemInformation> getSystems();

    List<ConstructorTablesRelations> getRelations(Integer threadId);
    
    List<ConstructorTables> getTablesForWeb(Integer threadId);

    String testSelect(CreateConstructorInitial init);
}