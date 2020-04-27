package com.adin.caedu.util;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter{

	public static Gson convert = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping()
			.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateSerializer())
			.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateDeserializer()).setPrettyPrinting().serializeNulls().create();
	
	public static String toJson(Object obj) {
		return convert.toJson(obj);
	}
	
	public static <T> T toObject(String json, Class<T> clazz) {
		return convert.fromJson(json, clazz);
	}
}
