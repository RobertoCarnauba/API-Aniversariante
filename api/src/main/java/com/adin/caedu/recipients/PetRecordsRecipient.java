package com.adin.caedu.recipients;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PetRecordsRecipient {
	@Expose
	@SerializedName(value = "recordData")
	private RecordDataRecipient recordData;

	@Expose
	@SerializedName(value = "insertOnNoMatch")
	private Boolean insertOnNoMatch;

	@Expose
	@SerializedName(value = "updateOnMatch")
	private String updateOnMatch;

	@Expose
	@SerializedName(value = "matchColumnName1")
	private String matchColumnName1;

	@Expose
	@SerializedName(value = "matchColumnName2")
	private String matchColumnName2 = "";

	public RecordDataRecipient getRecordData() {
		return recordData;
	}

	public void setRecordData(RecordDataRecipient recordData) {
		this.recordData = recordData;
	}

	public Boolean getInsertOnNoMatch() {
		return insertOnNoMatch;
	}

	public void setInsertOnNoMatch(Boolean insertOnNoMatch) {
		this.insertOnNoMatch = insertOnNoMatch;
	}

	public String getUpdateOnMatch() {
		return updateOnMatch;
	}

	public void setUpdateOnMatch(String updateOnMatch) {
		this.updateOnMatch = updateOnMatch;
	}

	public String getMatchColumnName1() {
		return matchColumnName1;
	}

	public void setMatchColumnName1(String matchColumnName1) {
		this.matchColumnName1 = matchColumnName1;
	}

	public String getMatchColumnName2() {
		return matchColumnName2;
	}

	public void setMatchColumnName2(String matchColumnName2) {
		this.matchColumnName2 = matchColumnName2;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
