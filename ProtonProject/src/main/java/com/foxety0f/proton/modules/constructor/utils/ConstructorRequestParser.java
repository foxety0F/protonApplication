package com.foxety0f.proton.modules.constructor.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.foxety0f.proton.modules.constructor.domain.request.ConstructRequestColumns;
import com.foxety0f.proton.modules.constructor.domain.request.ConstructRequestConditions;
import com.foxety0f.proton.modules.reports.domain.MetaColumns;

public class ConstructorRequestParser {

	public static List<ConstructRequestColumns> parseColumns(String json) throws JSONException {
		List<ConstructRequestColumns> columns = new ArrayList<ConstructRequestColumns>();
		JSONObject jsonObject = new JSONObject(json);
		JSONArray jsonArrays = jsonObject.getJSONArray("columns");

		for (int i = 0; i < jsonArrays.length(); i++) {
			ConstructRequestColumns column = new ConstructRequestColumns();
			JSONObject obj = jsonArrays.getJSONObject(i);

			column.setColumnId(obj.getInt("columnId"));
			column.setColTypeId(obj.getInt("colTypeId"));
			column.setAltName(obj.getString("altName"));
			column.setValues(obj.getString("values"));

			columns.add(column);
		}

		return columns;
	}

	public static List<ConstructRequestConditions> parseConditions(String json) throws JSONException {
		List<ConstructRequestConditions> conditions = new ArrayList<ConstructRequestConditions>();
		JSONObject jsonObject = new JSONObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("conditions");

		for (int i = 0; i < jsonArray.length(); i++) {
			ConstructRequestConditions condition = new ConstructRequestConditions();
			JSONObject obj = jsonArray.getJSONObject(i);

			condition.setColumnId(obj.getInt("columnId"));
			condition.setPartId(obj.getInt("partId"));
			condition.setSepId(obj.getInt("sepId"));
			condition.setValues(obj.getString("values"));
			condition.setCondType(obj.getInt("contType"));

			conditions.add(condition);
		}

		return conditions;
	}

	public static Map<String, Object> prepareValues(String values, String parseFormat, Integer typeId,
			Map<Integer, MetaColumns> columns) throws JSONException {
		String value = "";
		Map<String, Object> map = new HashedMap<String, Object>();

		if (typeId == 100) {
			JSONObject object = new JSONObject(values);
			JSONArray array = object.getJSONArray("values");
			for (int i = 0; i < array.length(); i++) {
				value += array.getString(i);
				if (i != array.length() - 1) {
					value += ",";
				}
			}
			map.put("values", value);
		} else if (typeId == 101) {
			JSONObject object = new JSONObject(values);
			map.put("values", object.getInt("values"));
		} else if (typeId == 102) {
			JSONObject object = new JSONObject(values);
			map.put("values", object.getString("values"));
		}else if(typeId == 103) {
			JSONObject object = new JSONObject(values);
			map.put("values", object.getString("values"));
		}
		return map;
	}
}
