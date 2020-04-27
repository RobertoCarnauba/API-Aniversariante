package com.adin.caedu.model;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.opencsv.bean.CsvBindByName;

public class CED_ClickListModel { 
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
	@CsvBindByName(column = "EMAIL_FORMAT")
	@Expose
	String EMAIL_FORMAT;
	@CsvBindByName(column = "OFFER_NAME")
	@Expose
	String OFFER_NAME;
	@CsvBindByName(column = "OFFER_NUMBER")
	@Expose
	String OFFER_NUMBER;
	@CsvBindByName(column = "OFFER_CATEGORY")
	@Expose
	String OFFER_CATEGORY;
	@CsvBindByName(column = "OFFER_URL")
	@Expose
	String OFFER_URL;

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

	public String getEMAIL_FORMAT() {
		return EMAIL_FORMAT;
	}

	public void setEMAIL_FORMAT(String eMAIL_FORMAT) {
		EMAIL_FORMAT = eMAIL_FORMAT;
	}

	public String getOFFER_NAME() {
		return OFFER_NAME;
	}

	public void setOFFER_NAME(String oFFER_NAME) {
		OFFER_NAME = oFFER_NAME;
	}

	public String getOFFER_NUMBER() {
		return OFFER_NUMBER;
	}

	public void setOFFER_NUMBER(String oFFER_NUMBER) {
		OFFER_NUMBER = oFFER_NUMBER;
	}

	public String getOFFER_CATEGORY() {
		return OFFER_CATEGORY;
	}

	public void setOFFER_CATEGORY(String oFFER_CATEGORY) {
		OFFER_CATEGORY = oFFER_CATEGORY;
	}

	public String getOFFER_URL() {
		return OFFER_URL;
	}

	public void setOFFER_URL(String oFFER_URL) {
		OFFER_URL = oFFER_URL;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
