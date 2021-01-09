package com.foxety0f.proton.modules.constructor.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.foxety0f.proton.modules.constructor.dao.IReportWebDao;
import com.foxety0f.proton.modules.constructor.domain.ReportingColumnsForRelation;
import com.foxety0f.proton.modules.constructor.domain.initial.CreateConstructorInitial;
import com.foxety0f.proton.modules.constructor.domain.initial.CreateConstructorReportColumn;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorAvailColumnParams;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTables;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTablesRelations;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorThreadInformation;

public class ConstructorGenerator {

    private IReportWebDao dao;

    public ConstructorGenerator(IReportWebDao dao) {
	this.dao = dao;
    }

    public String initGenerate(CreateConstructorInitial init) {
	String finalQuery = "";
	finalQuery += generateColumns(init.getColumns());
	finalQuery += generateFrom(init);
	return finalQuery;
    }

    private String generateColumns(List<CreateConstructorReportColumn> columns) {
	String sql = " SELECT ";
	List<ConstructorAvailColumnParams> colParams = dao.getAvailColumnParamsForGenerator();
	for (int i = 0; i < columns.size(); i++) {
	    String col = generateColumnName(columns.get(i));
	    if (col != null) {
		if (i != 0) {
		    sql += "\r\n     , ";
		}
		String fullColumn = "";
		for (int j = 0; j < colParams.size(); j++) {
		    if (colParams.get(j).getId() == columns.get(i).getColumnAvailParam()) {
			for (int k = 0; k < colParams.get(j).getColumnReplacer().size(); k++) {
			    fullColumn += colParams.get(j).getColumnReplacer().get(k).getReplacementPattern();
			}
		    }
		}
		fullColumn = fullColumn.equals("") ? ":column_name" : fullColumn;
		if(fullColumn.matches("(\\|caseeach\\|)")) {
		    
		}else {
		    fullColumn = fullColumn.replaceAll(":column_name", col);
		}
		sql += fullColumn;
	    }
	}
	return sql;
    }

    private String generateFrom(CreateConstructorInitial init) {
	List<Integer> columnIds = new ArrayList<>();
	List<Integer> tableIds = new ArrayList<>();
	List<ConstructorTablesRelations> relations = dao.getRelations(init.getThread());
	List<ReportingColumnsForRelation> columnsForRelation = dao.getColumnsForRelationForGenerator();
	ConstructorThreadInformation thread = dao.getThread(init.getThread());
	String sql = "\r\n  FROM " + thread.getCoreTableTechnicalName();

	for (int i = 0; i < init.getColumns().size(); i++) {
	    if (init.getColumns().get(i).getId() != null) {
		columnIds.add(init.getColumns().get(i).getId());
	    }
	}

	columnIds = columnIds.stream().distinct().collect(Collectors.toList());

	for (int i = 0; i < columnIds.size(); i++) {
	    tableIds.add(findTableByColumn(columnIds.get(i)));
	}

	tableIds = tableIds.stream().distinct().collect(Collectors.toList());
	List<Integer> tablesLogicalJoin = new ArrayList<Integer>();
	for (int i = 0; i < tableIds.size(); i++) {
	    if (tableIds.get(i) != thread.getCoreId()) {
		List<Integer> tablesResult = findTableToCore(tableIds.get(i), thread.getCoreId(), null, relations);
		Collections.reverse(tablesResult);
		tablesLogicalJoin.addAll(tablesResult);
	    }
	}

	tablesLogicalJoin = tablesLogicalJoin.stream().distinct().collect(Collectors.toList());
	System.err.println(tablesLogicalJoin.toString());

	for (int i = 0; i < tablesLogicalJoin.size(); i++) {
	    if (tablesLogicalJoin.get(i) != thread.getCoreId()) {
		for (int j = 0; j < relations.size(); j++) {
		    if (relations.get(j).getRefId().equals(tablesLogicalJoin.get(i))) {
			sql += "\r\n";
			Boolean relExists = false;
			for (int k = 0; k < relations.get(j).getAvailJoins().size(); k++) {
			    if (relations.get(j).getAvailJoins().get(k).getIsDefault()) {
				sql += "  " + relations.get(j).getAvailJoins().get(k).getValue() + " "
					+ findTableNameById(relations.get(j).getRefId());
				relExists = true;
			    }
			}

			if (!relExists) {
			    sql += "  LEFT JOIN " + findTableNameById(relations.get(j).getRefId());
			}

			sql += "\r\n";

			for (int k = 0; k < relations.get(j).getColumnsJoin().size(); k++) {
			    if (k == 0) {
				sql += "    ON ";
			    } else {
				sql += "\r\n   AND ";
			    }
			    sql += findTableNameById(relations.get(j).getCoreId()) + "."
				    + findColumnNameById(relations.get(j).getColumnsJoin().get(k).getLeftSide());
			    sql += " = ";
			    sql += findTableNameById(relations.get(j).getRefId()) + "."
				    + findColumnNameById(relations.get(j).getColumnsJoin().get(k).getRightSide());
			}
		    }
		}
	    }
	}

	return sql;
    }

    @SuppressWarnings("unused")
    private String generateWhere() {
	return null;
    }

    @SuppressWarnings("unused")
    private String generateHaving() {
	return null;
    }

    private String findColumnNameById(Integer id) {
	for (int i = 0; i < dao.getColumnsForGenerator().size(); i++) {
	    if (dao.getColumnsForGenerator().get(i).getId().equals(id)) {
		return dao.getColumnsForGenerator().get(i).getColumnName();
	    }
	}

	return null;
    }

    private String findTableNameById(Integer id) {
	for (int i = 0; i < dao.getTablesForGenerator().size(); i++) {
	    if (dao.getTablesForGenerator().get(i).getId().equals(id)) {
		return dao.getTablesForGenerator().get(i).getTableName();
	    }
	}
	return null;
    }

    private List<Integer> findTableToCore(Integer currentTable, Integer coreTable, List<Integer> prevList,
	    List<ConstructorTablesRelations> relations) {
	List<Integer> tables = (prevList == null ? new ArrayList<>() : prevList);
	tables.add(currentTable);
	for (int i = 0; i < relations.size(); i++) {
	    if (relations.get(i).getRefId().equals(currentTable)) {
		if (!relations.get(i).getCoreId().equals(coreTable)) {
		    tables = findTableToCore(relations.get(i).getCoreId(), coreTable, tables, relations);
		}
	    }
	}

	return tables;
    }

    private Integer findTableByColumn(Integer id) {
	for (int i = 0; i < dao.getColumnsForGenerator().size(); i++) {
	    if (dao.getColumnsForGenerator().get(i).getId().equals(id)) {
		return dao.getColumnsForGenerator().get(i).getTableId();
	    }
	}

	return 0;
    }

    private String generateColumnName(CreateConstructorReportColumn column) {
	String columnString = null;
	if (column.getId() != null) {
	    for (int i = 0; i < dao.getColumnsForGenerator().size(); i++) {
		if (dao.getColumnsForGenerator().get(i).getId().equals(column.getId())) {
		    for (int j = 0; j < dao.getTablesForGenerator().size(); j++) {
			if (dao.getTablesForGenerator().get(j).getId()
				.equals(dao.getColumnsForGenerator().get(i).getTableId())) {
			    columnString = dao.getTablesForGenerator().get(j).getTableName() + ".";
			}
		    }
		    columnString += dao.getColumnsForGenerator().get(i).getColumnName();
		    columnString += (column.getCustomName().equals("") ? "" : " as \"" + column.getCustomName() + "\"");
		    return columnString;
		}
	    }
	}
	return null;
    }
}