package com.adin.caedu.model;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.opencsv.bean.CsvBindByName;

public class CED_BounceListModel {
	@CsvBindByName(column = "EVENT_TYPE_ID")
	@Expose
	String EVENT_TYPE_ID;
	@CsvBindByName(column = "ACCOUNT_ID")
	@Expose
	String ACCOUNT_ID;
	@CsvBindByName(column = "LIST_ID")
	@Expose
	String LIST_ID;
	@CsvBindByName(column = "RIID")
	@Expose
	String RIID;
	@CsvBindByName(column = "CUSTOMER_ID")
	@Expose
	String CUSTOMER_ID;
	@CsvBindByName(column = "EVENT_CAPTURED_DT")
	@Expose
	String EVENT_CAPTURED_DT;
	@CsvBindByName(column = "EVENT_STORED_DT")
	@Expose
	String EVENT_STORED_DT;
	@CsvBindByName(column = "CAMPAIGN_ID")
	@Expose
	String CAMPAIGN_ID;
	@CsvBindByName(column = "LAUNCH_ID")
	@Expose
	String LAUNCH_ID;
	@CsvBindByName(column = "EMAIL")
	@Expose
	String EMAIL;
	@CsvBindByName(column = "EMAIL_FORMAT")
	@Expose
	String EMAIL_FORMAT;
	@CsvBindByName(column = "BOUNCE_TYPE")
	@Expose
	String BOUNCE_TYPE;
	@CsvBindByName(column = "REASON")
	@Expose
	String REASON;
	@CsvBindByName(column = "REASON_CODE")
	@Expose
	String REASON_CODE;
	@CsvBindByName(column = "SUBJECT")
	@Expose
	String SUBJECT;
	@CsvBindByName(column = "CONTACT_INFO")
	@Expose
	String CONTACT_INFO;

	public String getEVENT_TYPE_ID() {
		return EVENT_TYPE_ID;
	}

	public void setEVENT_TYPE_ID(String eVENT_TYPE_ID) {
		EVENT_TYPE_ID = eVENT_TYPE_ID;
	}

	public String getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public void setACCOUNT_ID(String aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}

	public String getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(String lIST_ID) {
		LIST_ID = lIST_ID;
	}

	public String getRIID() {
		return RIID;
	}

	public void setRIID(String rIID) {
		RIID = rIID;
	}

	public String getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(String cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public String getEVENT_CAPTURED_DT() {
		return EVENT_CAPTURED_DT;
	}

	public void setEVENT_CAPTURED_DT(String eVENT_CAPTURED_DT) {
		EVENT_CAPTURED_DT = eVENT_CAPTURED_DT;
	}

	public String getEVENT_STORED_DT() {
		return EVENT_STORED_DT;
	}

	public void setEVENT_STORED_DT(String eVENT_STORED_DT) {
		EVENT_STORED_DT = eVENT_STORED_DT;
	}

	public String getCAMPAIGN_ID() {
		return CAMPAIGN_ID;
	}

	public void setCAMPAIGN_ID(String cAMPAIGN_ID) {
		CAMPAIGN_ID = cAMPAIGN_ID;
	}

	public String getLAUNCH_ID() {
		return LAUNCH_ID;
	}

	public void setLAUNCH_ID(String lAUNCH_ID) {
		LAUNCH_ID = lAUNCH_ID;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getEMAIL_FORMAT() {
		return EMAIL_FORMAT;
	}

	public void setEMAIL_FORMAT(String eMAIL_FORMAT) {
		EMAIL_FORMAT = eMAIL_FORMAT;
	}

	public String getBOUNCE_TYPE() {
		return BOUNCE_TYPE;
	}

	public void setBOUNCE_TYPE(String bOUNCE_TYPE) {
		BOUNCE_TYPE = bOUNCE_TYPE;
	}

	public String getREASON() {
		return REASON;
	}

	public void setREASON(String rEASON) {
		REASON = rEASON;
	}

	public String getREASON_CODE() {
		return REASON_CODE;
	}

	public void setREASON_CODE(String rEASON_CODE) {
		REASON_CODE = rEASON_CODE;
	}

	public String getSUBJECT() {
		return SUBJECT;
	}

	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}

	public String getCONTACT_INFO() {
		return CONTACT_INFO;
	}

	public void setCONTACT_INFO(String cONTACT_INFO) {
		CONTACT_INFO = cONTACT_INFO;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
