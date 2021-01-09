package com.foxety0f.proton.modules.constructor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.modules.constructor.domain.ReportingColumnsForRelation;
import com.foxety0f.proton.modules.constructor.domain.ReportingMetaRelations;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorAvailColumnParameterReplacer;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorAvailColumnParams;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorAvailConditionParameterReplacer;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorAvailConditionParams;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorColumn;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorRelationJoinDefine;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorSupportMapper;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorSystemInformation;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTables;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTablesRelations;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorThreadInformation;
import com.foxety0f.proton.modules.reporting.dao.IReportingAdminDAO;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumns;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemInformation;
import com.foxety0f.proton.modules.reporting.domain.ReportingTables;

public class ReportWebDao extends AbstractDAO implements IReportWebDao {

    private IReportingAdminDAO admin;

    public ReportWebDao(DataSource dataSource, IReportingAdminDAO admin) {
	super(dataSource);
	fillThreads();
	this.admin = admin;
	loadRelations();
	loadColumsForRelation();
	loadJoinsForRelation();
	loadColumnReplacers();
	loadConditionReplacers();
	loadAvailColumnParams();
	loadAvailConditionParams();
	loadColumns();
	loadTables();
    }

    private static List<ConstructorThreadInformation> threads;
    private static List<ReportingMetaRelations> relations;
    private static List<ReportingColumnsForRelation> columnsForRelation;
    private static List<ConstructorRelationJoinDefine> joinsForRelation;
    private static List<ConstructorAvailColumnParameterReplacer> columnReplacers;
    private static List<ConstructorAvailConditionParameterReplacer> conditionReplacers;
    private static List<ConstructorAvailColumnParams> availColumnParams;
    private static List<ConstructorAvailConditionParams> availConditionParams;
    private static List<ConstructorColumn> columns;
    private static List<ConstructorTables> tables;

    private void fillThreads() {
	threads = getThreads();
    }
    
    private void loadColumns() {
	columns = getColumns();
    }
    
    private void loadTables() {
	tables = getTables();
    }

    private void loadRelations() {
	relations = querySource.query("select * from proton_meta_relations", new ReportingMetaRelationsRowMapper());
    }

    private void loadColumsForRelation() {
	columnsForRelation = getColumnsForRelation();
    }

    private void loadJoinsForRelation() {
	joinsForRelation = getJoinsForRelations();
    }

    private void loadColumnReplacers() {
	columnReplacers = getColumnReplacers();
    }

    private void loadConditionReplacers() {
	conditionReplacers = getConditionReplacers();
    }

    private void loadAvailColumnParams() {
	availColumnParams = getAvailColumnParams();
    }

    private void loadAvailConditionParams() {
	availConditionParams = getAvailConditionParams();
    }
    
    public List<ConstructorColumn> getColumnsForGenerator(){
	return columns;
    }
    
    public List<ConstructorTables> getTablesForGenerator(){
	return tables;
    }
    
    public List<ReportingMetaRelations> getRelationsForGenerator(){
	return relations;
    }
    
    public List<ConstructorAvailColumnParams> getAvailColumnParamsForGenerator(){
	return availColumnParams;
    }

    public List<ReportingColumnsForRelation> getColumnsForRelationForGenerator(){
	return columnsForRelation;
    }
    
    @Override
    public List<ConstructorSystemInformation> getSystems() {
	String sql = "select sys.id\r\n" + "	 , sys.system_type\r\n" + "	 , sys.title\r\n"
		+ "	 , sys.description\r\n" + "	 , mst.title sys_t_title\r\n"
		+ "  from public.proton_reporting_meta_systems sys\r\n"
		+ "  join public.proton_meta_system_type mst on mst.id = sys.system_type";
	return querySource.query(sql, new ConstructorSystemInformationRowMapper());
    }

    @Override
    public List<ConstructorThreadInformation> getThreads() {
	String sql = "select th.id\r\n" + "	 , th.title\r\n" + "	 , th.description\r\n"
		+ "	 , th.core_table_id\r\n" + "	 , tb.table_name\r\n" + "	 , tb.business_name\r\n"
		+ "	 , th.is_native_lang\r\n" + "	 , th.system_id\r\n"
		+ "from proton_reporting_meta_threads th\r\n"
		+ "join proton_reporting_meta_tables tb on th.core_table_id = tb.id";
	return querySource.query(sql, new ConstructorThreadInformationRowMapper());
    }
    
    @Override
    public ConstructorThreadInformation getThread(Integer threadId) {
	Map<String, Integer> map = new HashedMap<>();
	map.put("threadId", threadId);
	String sql = "select th.id\r\n" + "	 , th.title\r\n" + "	 , th.description\r\n"
		+ "	 , th.core_table_id\r\n" + "	 , tb.table_name\r\n" + "	 , tb.business_name\r\n"
		+ "	 , th.is_native_lang\r\n" + "	 , th.system_id\r\n"
		+ "from proton_reporting_meta_threads th\r\n"
		+ "join proton_reporting_meta_tables tb on th.core_table_id = tb.id"
		+ " where th.id = :threadId";
	return querySource.query(sql, map, new ResultSetExtractor<ConstructorThreadInformation>() {

	    @Override
	    public ConstructorThreadInformation extractData(ResultSet rs) throws SQLException, DataAccessException {
		while(rs.next()) {
		    ConstructorThreadInformation item = new ConstructorThreadInformation();
		    item.setId(rs.getInt("id"));
		    item.setTitle(rs.getString("title"));
		    item.setDescription(rs.getString("description"));
		    item.setCoreId(rs.getInt("core_table_id"));
		    item.setCoreTableName(rs.getString("business_name"));
		    item.setCoreTableTechnicalName(rs.getString("table_name"));
		    item.setIsNativeLang(rs.getInt("is_native_lang") == 1 ? true : false);
		    item.setSystemId(rs.getInt("system_id"));
		    return item;
		}
		return null;
	    }
	    
	});
    }

    private static class ConstructorThreadInformationRowMapper implements RowMapper<ConstructorThreadInformation> {

	@Override
	public ConstructorThreadInformation mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorThreadInformation item = new ConstructorThreadInformation();
	    item.setId(rs.getInt("id"));
	    item.setTitle(rs.getString("title"));
	    item.setDescription(rs.getString("description"));
	    item.setCoreId(rs.getInt("core_table_id"));
	    item.setCoreTableName(rs.getString("business_name"));
	    item.setCoreTableTechnicalName(rs.getString("table_name"));
	    item.setIsNativeLang(rs.getInt("is_native_lang") == 1 ? true : false);
	    item.setSystemId(rs.getInt("system_id"));
	    return item;
	}

    }

    private static class ConstructorSystemInformationRowMapper implements RowMapper<ConstructorSystemInformation> {

	@Override
	public ConstructorSystemInformation mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorSystemInformation item = new ConstructorSystemInformation();
	    item.setId(rs.getInt("id"));
	    item.setSystemTypeId(rs.getInt("system_type"));
	    item.setSystemName(rs.getString("title"));
	    item.setSystemType(rs.getString("sys_t_title"));
	    item.setDescription(rs.getString("description"));

	    List<ConstructorThreadInformation> currThreads = new ArrayList<ConstructorThreadInformation>();
	    for (int i = 0; i < threads.size(); i++) {
		if (threads.get(i).getSystemId() == rs.getInt("id")) {
		    currThreads.add(threads.get(i));
		}
	    }

	    item.setThreads(currThreads);

	    return item;
	}

    }

    private static class ReportingMetaRelationsRowMapper implements RowMapper<ReportingMetaRelations> {

	@Override
	public ReportingMetaRelations mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingMetaRelations item = new ReportingMetaRelations();
	    item.setId(rs.getInt("id"));
	    item.setTitle(rs.getString("title"));
	    item.setDescription(rs.getString("description"));
	    item.setCoreTableId(rs.getInt("core_table_id"));
	    item.setRefTableId(rs.getInt("ref_table_id"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    item.setcDate(rs.getDate("c_date"));
	    item.setuDate(rs.getDate("u_date"));
	    return item;
	}

    }

    @Override
    public List<ConstructorTablesRelations> getRelations() {
	return null;
    }

    @Override
    public List<ConstructorTablesRelations> getRelations(Integer threadId) {
	Map<String, Integer> map = new HashedMap<>();
	map.put("threadId", threadId);

	String sql = "select relation_id from proton_meta_threads_relations_ref"
		+ " where thread_id = :threadId and is_active = 1";

	List<Integer> relations = querySource.query(sql, map, new IntegerRowMapper());

	List<ConstructorTablesRelations> result = new ArrayList<>();

	for (int i = 0; i < ReportWebDao.relations.size(); i++) {
	    for (int j = 0; j < relations.size(); j++) {
		if (ReportWebDao.relations.get(i).getId() == relations.get(j)) {
		    ConstructorTablesRelations rel = new ConstructorTablesRelations();
		    rel.setCoreId(ReportWebDao.relations.get(i).getCoreTableId());
		    rel.setRefId(ReportWebDao.relations.get(i).getRefTableId());
		    List<ConstructorSupportMapper> columns = new ArrayList<>();
		    for (int ii = 0; ii < columnsForRelation.size(); ii++) {
			if (columnsForRelation.get(ii).getRelationId() == ReportWebDao.relations.get(i).getId()) {
			    ConstructorSupportMapper column = new ConstructorSupportMapper();
			    column.setLeftSide(columnsForRelation.get(ii).getCoreColumnId());
			    column.setRightSide(columnsForRelation.get(ii).getRefColumnId());
			    columns.add(column);
			}
		    }

		    rel.setColumnsJoin(columns);

		    List<ConstructorRelationJoinDefine> availJoins = new ArrayList<>();

		    for (int ii = 0; ii < joinsForRelation.size(); ii++) {
			if (joinsForRelation.get(ii).getId() == ReportWebDao.relations.get(i).getId()) {
			    availJoins.add(joinsForRelation.get(ii));
			}
		    }
		    rel.setAvailJoins(availJoins);
		    result.add(rel);
		}
	    }
	}

	return result;
    }

    private static class ConstructorSupportMapperRowMapper implements RowMapper<ConstructorSupportMapper> {

	@Override
	public ConstructorSupportMapper mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorSupportMapper item = new ConstructorSupportMapper();
	    item.setLeftSide(rs.getInt(1));
	    item.setRightSide(rs.getInt(2));
	    return item;
	}

    }

    private List<ReportingColumnsForRelation> getColumnsForRelation() {
	String sql = "select relation_id, core_column_id, ref_column_id from proton_meta_relations_columns order by id asc";

	return querySource.query(sql, new ReportingColumnsForRelationRowMapper());
    }

    private static class ReportingColumnsForRelationRowMapper implements RowMapper<ReportingColumnsForRelation> {

	@Override
	public ReportingColumnsForRelation mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ReportingColumnsForRelation item = new ReportingColumnsForRelation();
	    item.setRelationId(rs.getInt("relation_id"));
	    item.setCoreColumnId(rs.getInt("core_column_id"));
	    item.setRefColumnId(rs.getInt("ref_column_id"));
	    return item;
	}

    }

    private List<ConstructorRelationJoinDefine> getJoinsForRelations() {
	String sql = "SELECT j.relation_id\r\n" + "	 , t.title\r\n" + "	 , t.description\r\n"
		+ "	 , t.value\r\n" + "	 , t.is_active\r\n" + "	 , j.is_default\r\n"
		+ "  FROM public.proton_meta_relations_joins j\r\n"
		+ "  JOIN proton_meta_relations_join_types t on t.id = j.join_type_id";
	return querySource.query(sql, new ConstructorRelationJoinDefineRowMapper());
    }

    private static class ConstructorRelationJoinDefineRowMapper implements RowMapper<ConstructorRelationJoinDefine> {

	@Override
	public ConstructorRelationJoinDefine mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorRelationJoinDefine item = new ConstructorRelationJoinDefine();
	    item.setId(rs.getInt("relation_id"));
	    item.setTitle(rs.getString("title"));
	    item.setDescription(rs.getString("description"));
	    item.setValue(rs.getString("value"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    item.setIsDefault(rs.getInt("is_default") == 1 ? true : false);
	    return item;
	}

    }

    public List<ConstructorAvailColumnParameterReplacer> getColumnReplacers() {
	String sql = "SELECT * FROM public.proton_constructor_columns_mppg\r\n" + "ORDER BY id ASC ";

	return querySource.query(sql, new ConstructorAvailColumnParameterReplacerRowMapper());
    }

    public List<ConstructorAvailConditionParameterReplacer> getConditionReplacers() {
	String sql = "SELECT * FROM public.proton_constructor_condition_mppg\r\n" + "ORDER BY id ASC ";

	return querySource.query(sql, new ConstructorAvailConditionParameterReplacerRowMapper());
    }

    private static class ConstructorAvailColumnParameterReplacerRowMapper
	    implements RowMapper<ConstructorAvailColumnParameterReplacer> {

	@Override
	public ConstructorAvailColumnParameterReplacer mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorAvailColumnParameterReplacer item = new ConstructorAvailColumnParameterReplacer();
	    item.setId(rs.getInt("id"));
	    item.setOperationId(rs.getInt("operation_id"));
	    item.setSystemId(rs.getInt("system_id"));
	    item.setReplacementPattern(rs.getString("replacement_pattern"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    return item;
	}

    }

    private static class ConstructorAvailConditionParameterReplacerRowMapper
	    implements RowMapper<ConstructorAvailConditionParameterReplacer> {

	@Override
	public ConstructorAvailConditionParameterReplacer mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorAvailConditionParameterReplacer item = new ConstructorAvailConditionParameterReplacer();
	    item.setId(rs.getInt("id"));
	    item.setOperationId(rs.getInt("operation_id"));
	    item.setSystemId(rs.getInt("system_id"));
	    item.setReplacementPattern(rs.getString("replacement_pattern"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    return item;
	}

    }

    public List<ConstructorAvailColumnParams> getAvailColumnParams() {
	String sql = "SELECT * FROM public.proton_constructor_columns_ops\r\n" + "ORDER BY id ASC ";
	return querySource.query(sql, new ConstructorAvailColumnParamsRowMapper());
    }

    private static class ConstructorAvailColumnParamsRowMapper implements RowMapper<ConstructorAvailColumnParams> {

	@Override
	public ConstructorAvailColumnParams mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorAvailColumnParams item = new ConstructorAvailColumnParams();
	    item.setId(rs.getInt("id"));
	    item.setColumnType(rs.getInt("column_type"));
	    item.setTitle(rs.getString("title"));
	    item.setDescription(rs.getString("description"));
	    item.setPseudoLogic(rs.getString("pseudo_logic"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    List<ConstructorAvailColumnParameterReplacer> replacer = new ArrayList<ConstructorAvailColumnParameterReplacer>();
	    for (int i = 0; i < columnReplacers.size(); i++) {
		if (columnReplacers.get(i).getOperationId() == rs.getInt("id")) {
		    replacer.add(columnReplacers.get(i));
		}
	    }
	    item.setColumnReplacer(replacer);
	    item.setIsGroup(rs.getInt("is_group") == 1 ? true : false);
	    return item;
	}

    }

    public List<ConstructorAvailConditionParams> getAvailConditionParams() {
	String sql = "SELECT * FROM public.proton_constructor_condition_ops\r\n" + "ORDER BY id ASC ";
	return querySource.query(sql, new ConstructorAvailConditionParamsRowMapper());
    }

    private static class ConstructorAvailConditionParamsRowMapper
	    implements RowMapper<ConstructorAvailConditionParams> {

	@Override
	public ConstructorAvailConditionParams mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorAvailConditionParams item = new ConstructorAvailConditionParams();
	    item.setId(rs.getInt("id"));
	    item.setColumnType(rs.getInt("column_type"));
	    item.setTitle(rs.getString("title"));
	    item.setDescription(rs.getString("description"));
	    item.setPseudoLogic(rs.getString("pseudo_logic"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    List<ConstructorAvailConditionParameterReplacer> replacer = new ArrayList<ConstructorAvailConditionParameterReplacer>();
	    for (int i = 0; i < conditionReplacers.size(); i++) {
		if (conditionReplacers.get(i).getOperationId() == rs.getInt("id")) {
		    replacer.add(conditionReplacers.get(i));
		}
	    }
	    item.setColumnReplacer(replacer);
	    return item;
	}

    }
    
    public List<ConstructorColumn> getColumns(){
	String sql = "SELECT * FROM public.proton_reporting_meta_columns\r\n"
		+ "ORDER BY id ASC ";
	
	return querySource.query(sql, new ConstructorColumnRowMapper());
    }
    
    private static class ConstructorColumnRowMapper implements RowMapper<ConstructorColumn> {

	@Override
	public ConstructorColumn mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorColumn item = new ConstructorColumn();
	    item.setId(rs.getInt("id"));
	    item.setTableId(rs.getInt("table_id"));
	    item.setColumnTypeId(rs.getInt("column_type_id"));
	    item.setColumnName(rs.getString("column_name"));
	    item.setBusinessName(rs.getString("business_column_name"));
	    item.setDescription(rs.getString("description"));
	    item.setBusinessDescription(rs.getString("business_description"));
	    item.setOrdinalPosition(rs.getInt("ordering"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    List<ConstructorAvailColumnParams> availColumns = new ArrayList<ConstructorAvailColumnParams>();
	    for(int i = 0; i < availColumnParams.size(); i++) {
		if(availColumnParams.get(i).getColumnType() == rs.getInt("column_type_id")) {
		    availColumns.add(availColumnParams.get(i));
		}
	    }
	    item.setAvailColumnParameters(availColumns);
	    
	    List<ConstructorAvailConditionParams> availCondition = new ArrayList<ConstructorAvailConditionParams>();
	    for(int i = 0; i < availConditionParams.size(); i++) {
		if(availConditionParams.get(i).getColumnType() == rs.getInt("column_type_id")) {
		    availCondition.add(availConditionParams.get(i));
		}
	    }
	    item.setAvailConditionParameters(availCondition);
	    return item;
	}

    }
    
    public List<ConstructorTables> getTables(){
	String sql = "SELECT * FROM public.proton_reporting_meta_tables\r\n"
		+ "ORDER BY id ASC ";
	return querySource.query(sql, new ConstructorTablesRowMapper());
    }
    
    private static class ConstructorTablesRowMapper implements RowMapper<ConstructorTables> {

	@Override
	public ConstructorTables mapRow(ResultSet rs, int rowNum) throws SQLException {

	    ConstructorTables item = new ConstructorTables();
	    item.setId(rs.getInt("id"));
	    item.setSystemId(rs.getInt("system_id"));
	    item.setTableName(rs.getString("table_name"));
	    item.setBusinessName(rs.getString("business_name"));
	    item.setDescription(rs.getString("description"));
	    item.setBusinessDescription(rs.getString("business_description"));
	    item.setIsActive(rs.getInt("is_active") == 1 ? true : false);
	    List<ConstructorColumn> columnList = new ArrayList<ConstructorColumn>();
	    for(int i = 0; i < columns.size(); i++) {
		if(columns.get(i).getTableId() == rs.getInt("id") && columns.get(i).getIsActive()) {
		    columnList.add(columns.get(i));
		}
	    }
	    item.setColumns(columnList);
	    
	    return item;
	}

    }
    
    public List<ConstructorTables> getTablesForWeb(Integer threadId){
	List<ConstructorTablesRelations> rels = getRelations(threadId);
	List<ConstructorTables> tabs = new ArrayList<>();
	
	for(int i = 0; i < tables.size(); i++) {
	    if(tables.get(i).getId().equals(rels.get(0).getCoreId())) {
		tabs.add(tables.get(i));
	    }
	}
	
	for(int i = 0; i < tables.size(); i++) {
	    for(int j = 0; j < rels.size(); j++) {
		if(tables.get(i).getId().equals(rels.get(j).getRefId())) {
		    tabs.add(tables.get(i));
		}
	    }
	}
	
	return tabs;
    }
}
