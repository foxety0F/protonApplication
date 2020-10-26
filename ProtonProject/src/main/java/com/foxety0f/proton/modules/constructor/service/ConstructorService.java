package com.foxety0f.proton.modules.constructor.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONException;

import com.foxety0f.proton.modules.constructor.domain.ConstructColumnTableMap;
import com.foxety0f.proton.modules.constructor.domain.request.ConstructRequestColumns;
import com.foxety0f.proton.modules.constructor.domain.request.ConstructRequestConditions;
import com.foxety0f.proton.modules.constructor.utils.ConstructorRequestParser;
import com.foxety0f.proton.modules.reports.domain.MetaColumns;
import com.foxety0f.proton.modules.reports.domain.MetaTables;
import com.foxety0f.proton.modules.reports.domain.MetaThreads;
import com.foxety0f.proton.modules.reports.domain.configuration.ColumnType;
import com.foxety0f.proton.modules.reports.domain.configuration.ConditionType;
import com.foxety0f.proton.modules.reports.domain.configuration.CustomQueryConditionValue;
import com.foxety0f.proton.modules.reports.domain.configuration.CustomQueryConditions;
import com.foxety0f.proton.modules.reports.domain.configuration.TableRelation;
import com.foxety0f.proton.modules.reports.domain.configuration.TableRelationType;
import com.foxety0f.proton.modules.reports.domain.configuration.ValueType;

public class ConstructorService {

    Map<Integer, Integer> mapColumnTable;
    Map<Integer, MetaTables> metaTables;
    List<ColumnType> columnTypes;
    List<ConditionType> condType;
    Map<Integer, MetaColumns> metaColumns;
    List<ValueType> valueTypes;
    List<TableRelationType> tableRelationType;
    List<TableRelation> tableRelationDefault;

    public String prepareQuery(List<ConstructRequestColumns> columns, List<ConstructRequestConditions> conditions,
	    Map<Integer, Integer> relations, MetaThreads thread) {
	String sql = "";
	return sql;
    }

    public String prepareSelect(List<ConstructRequestColumns> columns) throws JSONException {
	String sql = " SELECT \r\n";

	for (int i = 0; i < columns.size(); i++) {
	    Map<String, Object> mapCol = new HashedMap<String, Object>();
	    String replacementFormat = "";
	    String defaultAltName;
	    String columnName;
	    if (i == 0) {
		sql += ",";
	    }

	    for (ColumnType type : columnTypes) {
		if (type.getId() == columns.get(i).getColTypeId()) {
		    replacementFormat = type.getReplacementString();
		    for (ValueType valType : valueTypes) {
			if (valType.getId() == type.getValueType()) {
			    mapCol = ConstructorRequestParser.prepareValues(columns.get(i).getValues(),
				    valType.getParseFormat(), type.getValueType(), metaColumns);
			}
		    }

		}
	    }

	    defaultAltName = metaColumns.get(columns.get(i).getColumnId()).getAltName();
	    mapCol.put("altName", columns.get(i).getAltName() == null ? defaultAltName : columns.get(i).getAltName());

	    MetaTables table = metaTables.get(mapColumnTable.get(columns.get(i).getColumnId()));

	    columnName = (table.getSchemaName() == null ? "" : table.getSchemaName() + ".")
		    + (table.getTableName() == null ? "" : table.getTableName() + ".")
		    + metaColumns.get(columns.get(i).getColumnId()).getColumnName();
	    mapCol.put("columnName", columnName);

	    Set<String> keys = mapCol.keySet();

	    for (String key : keys) {
		replacementFormat = replacementFormat.replaceAll(":" + key, mapCol.get(key).toString());
	    }

	    sql += replacementFormat + "\r\n";
	}

	return sql;
    }

    public String prepareFrom(List<ConstructRequestColumns> columns, List<ConstructRequestConditions> conditions,
	    Map<Integer, Integer> relations, MetaThreads thread) {
	String sql = " from ";
	sql += (metaTables.get(thread.getCoreTableId()).getSchemaName() == null ? ""
		: (metaTables.get(thread.getCoreTableId()).getSchemaName() + "."))
		+ metaTables.get(thread.getCoreTableId()).getTableName();

	Map<Integer, String> usingTables = new HashedMap<Integer, String>();

	for (ConstructRequestColumns column : columns) {
	    usingTables.put(mapColumnTable.get(column.getColumnId()), "");
	}

	for (ConstructRequestConditions condition : conditions) {
	    usingTables.put(mapColumnTable.get(condition.getColumnId()), "");
	}

	return null;
    }

    public String prepareWhere(List<ConstructRequestConditions> conditions) {
	String sql = " where 1 = 1 ";

	for (ConstructRequestConditions condition : conditions) {

	}

	return null;
    }

    public String prepareHaving(List<ConstructRequestConditions> conditions) {
	return null;
    }

    private String findRelation(Integer coreTable, Integer subTable) {

	String sql = "";

	for (TableRelation rel : tableRelationDefault) {
	    if (rel.getTableId() == coreTable && rel.getTableSub() == subTable) {
		if (rel.getLinkRow() != null) {
		    for (TableRelation relLink : tableRelationDefault) {
			if (rel.getLinkRow() == relLink.getId()) {
			    sql += findRelation(relLink.getTableId(), relLink.getTableSub());
			}
		    }
		}

	    }
	}

	return null;
    }

    public String prepareCustomQuery(String sql, List<CustomQueryConditionValue> conditions) {
	return null;
    }
}