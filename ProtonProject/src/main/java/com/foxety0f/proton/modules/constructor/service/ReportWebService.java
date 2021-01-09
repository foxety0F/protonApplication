package com.foxety0f.proton.modules.constructor.service;

import java.util.List;
import java.util.Map;

import com.foxety0f.proton.modules.constructor.dao.IReportWebDao;
import com.foxety0f.proton.modules.constructor.domain.initial.CreateConstructorInitial;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorSystemInformation;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTables;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTablesRelations;
import com.foxety0f.proton.modules.constructor.utils.ConstructorGenerator;

public class ReportWebService implements IReportWebService {

    private IReportWebDao web;
    private ConstructorGenerator generator;
    
    public ReportWebService(IReportWebDao web) {
	this.web = web;
	generator = new ConstructorGenerator(this.web);
    }
    
    @Override
    public List<ConstructorSystemInformation> getSystems(){
	return web.getSystems();
    }
    
    @Override
    public List<ConstructorTablesRelations> getRelations(Integer threadId){
	return web.getRelations(threadId);
    }
    
    public List<ConstructorTables> getTablesForWeb(Integer threadId){
	return web.getTablesForWeb(threadId);
    }
    
    public List<Map<String, Object>> processedQuery(){
	return null;
    }
    
    public String testSelect(CreateConstructorInitial init) {
	return generator.initGenerate(init);
    }
}
