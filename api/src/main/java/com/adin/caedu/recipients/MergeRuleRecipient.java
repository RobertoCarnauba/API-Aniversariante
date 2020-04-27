package com.adin.caedu.recipients;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MergeRuleRecipient {

	@Expose
	@SerializedName(value = "htmlValue")
	private String htmlValue = "H";

	@Expose
	@SerializedName(value = "optinValue")
	private String optinValue = "I";

	@Expose
	@SerializedName(value = "textValue")
	private String textValue = "T";

	@Expose
	@SerializedName(value = "insertOnNoMatch")
	private boolean insertOnNoMatch = true;

	@Expose
	@SerializedName(value = "updateOnMatch")
	private String updateOnMatch = "REPLACE_ALL";

	@Expose
	@SerializedName(value = "matchColumnName1")
	private String matchColumnName1 = null;

	@Expose
	@SerializedName(value = "matchColumnName2")
	private String matchColumnName2 = null;

	@Expose
	@SerializedName(value = "matchOperator")
	private String matchOperator = "NONE";

	@Expose
	@SerializedName(value = "optoutValue")
	private String optoutValue = "O";

	@Expose
	@SerializedName(value = "rejectRecordIfChannelEmpty")
	private String rejectRecordIfChannelEmpty = null;

	@Expose
	@SerializedName(value = "defaultPermissionStatus")
	private String defaultPermissionStatus = "OPTIN";

	public String getHtmlValue() {
		return htmlValue;
	}

	public void setHtmlValue(String htmlValue) {
		this.htmlValue = htmlValue;
	}

	public String getOptinValue() {
		return optinValue;
	}

	public void setOptinValue(String optinValue) {
		this.optinValue = optinValue;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
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

	public String getMatchOperator() {
		return matchOperator;
	}

	public void setMatchOperator(String matchOperator) {
		this.matchOperator = matchOperator;
	}

	public String getOptoutValue() {
		return optoutValue;
	}

	public void setOptoutValue(String optoutValue) {
		this.optoutValue = optoutValue;
	}

	public String getRejectRecordIfChannelEmpty() {
		return rejectRecordIfChannelEmpty;
	}

	public void setRejectRecordIfChannelEmpty(String rejectRecordIfChannelEmpty) {
		this.rejectRecordIfChannelEmpty = rejectRecordIfChannelEmpty;
	}

	public String getDefaultPermissionStatus() {
		return defaultPermissionStatus;
	}

	public void setDefaultPermissionStatus(String defaultPermissionStatus) {
		this.defaultPermissionStatus = defaultPermissionStatus;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}

}
