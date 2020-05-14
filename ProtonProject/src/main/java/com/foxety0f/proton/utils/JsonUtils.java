package com.foxety0f.proton.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.internal.bind.DateTypeAdapter;
import java.lang.reflect.Type;

public class JsonUtils {
	public static String toJson(Object data) {
		return new GsonBuilder().registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter())
				.registerTypeAdapter(java.util.Date.class, new DateTypeAdapter())
				.registerTypeAdapter(Throwable.class, new ExceptionSerializer()).serializeNulls().create().toJson(data);
	}

	private static class ExceptionSerializer implements JsonSerializer<Exception> {
		@Override
		public JsonElement serialize(Exception src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("cause", new JsonPrimitive(String.valueOf(src.getCause())));
			jsonObject.add("message", new JsonPrimitive(src.getMessage()));
			return jsonObject;
		}
	}
}
