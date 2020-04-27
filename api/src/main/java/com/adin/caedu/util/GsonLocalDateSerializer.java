package com.adin.caedu.util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonLocalDateSerializer implements JsonSerializer<LocalDateTime>{

	public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
		DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		return src == null ? null : new JsonPrimitive(src.format(formatador));
	}
}