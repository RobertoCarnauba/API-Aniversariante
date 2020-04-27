package com.adin.caedu.recipients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PETRecipient {
	
	@Expose
	@SerializedName(value = "recordData")
	private RecordDataRecipient recordData;
	
	@Expose
	@SerializedName(value = "insertOnNoMatch")
	private boolean insertOnNoMatch = true;
	
	@Expose
	@SerializedName(value = "updateOnMatch")
	private String updateOnMatch = "REPLACE_ALL";
	
	@Expose
	@SerializedName(value = "matchColumnName1")
	private String matchColumnName1 = "RIID";
	
	@Expose
	@SerializedName(value = "matchColumnName2")
	private String matchColumnName2 = null;
	
	public RecordDataRecipient getRecordData() {
		return recordData;
	}
	public void setRecordData(RecordDataRecipient recordData) {
		this.recordData = recordData;
	}
	public boolean isInsertOnNoMatch() {
		return insertOnNoMatch;
	}
	public void setInsertOnNoMatch(boolean insertOnNoMatch) {
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

}
