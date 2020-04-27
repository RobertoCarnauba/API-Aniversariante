package com.adin.caedu.recipients;

import java.util.List;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordDataRecipient {

	@Expose
	@SerializedName(value = "fieldNames")
	private List<String> fieldNames;

	@Expose
	@SerializedName(value = "records")
	private List<List<String>> records;

	@Expose
	@SerializedName(value = "mapTemplateName")
	private String mapTemplateName = null;

	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public List<List<String>> getRecords() {
		return records;
	}

	public void setRecords(List<List<String>> records) {
		this.records = records;
	}

	public String getMapTemplateName() {
		return mapTemplateName;
	}

	public void setMapTemplateName(String mapTemplateName) {
		this.mapTemplateName = mapTemplateName;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}

}
