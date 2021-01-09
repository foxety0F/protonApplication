package com.foxety0f.proton.modules.constructor.dao;

import java.util.List;

import com.foxety0f.proton.modules.constructor.domain.ReportingColumnsForRelation;
import com.foxety0f.proton.modules.constructor.domain.ReportingMetaRelations;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorAvailColumnParams;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorColumn;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorSystemInformation;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTables;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTablesRelations;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorThreadInformation;

public interface IReportWebDao {

    List<ConstructorSystemInformation> getSystems();

    List<ConstructorThreadInformation> getThreads();

    List<ConstructorTablesRelations> getRelations();

    List<ConstructorTablesRelations> getRelations(Integer threadId);

    List<ConstructorTables> getTablesForWeb(Integer threadId);
    
    List<ConstructorColumn> getColumnsForGenerator();
    
    List<ConstructorTables> getTablesForGenerator();
    
    List<ReportingMetaRelations> getRelationsForGenerator();
    
    List<ConstructorAvailColumnParams> getAvailColumnParamsForGenerator();

    ConstructorThreadInformation getThread(Integer threadId);
    
    List<ReportingColumnsForRelation> getColumnsForRelationForGenerator();
}