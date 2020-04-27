package com.adin.caedu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;

@Entity(name = "CED")
@Table(name = "CED")
public class CED_CampaingListModel {

	@SerializedName("Id")
	@Expose
	@Id
	int Id;
	@SerializedName("EVENT_TYPE_ID")
	@CsvBindByName(column = "EVENT_TYPE_ID")
	@Expose
	Integer EVENT_TYPE_ID = null;
	@SerializedName("ACCOUNT_ID")
	@CsvBindByName(column = "ACCOUNT_ID")
	@Expose
	Integer ACCOUNT_ID = null;
	@SerializedName("LIST_ID")
	@CsvBindByName(column = "LIST_ID")
	@Expose
	Integer LIST_ID = null;
	@SerializedName("RIID")
	@CsvBindByName(column = "RIID")
	@Expose
	Integer RIID = null;
	@SerializedName("CUSTOMER_ID")
	@CsvBindByName(column = "CUSTOMER_ID")
	@Expose
	String CUSTOMER_ID = null;
	@SerializedName("EVENT_CAPTURED_DT")
	@CsvBindByName(column = "EVENT_CAPTURED_DT")
	@Expose
	String EVENT_CAPTURED_DT = null;
	@SerializedName("EVENT_STORED_DT")
	@CsvBindByName(column = "EVENT_STORED_DT")
	@Expose
	String EVENT_STORED_DT = null;
	@SerializedName("CAMPAIGN_ID")
	@CsvBindByName(column = "CAMPAIGN_ID")
	@Expose
	Integer CAMPAIGN_ID = null;
	@SerializedName("LAUNCH_ID")
	@CsvBindByName(column = "LAUNCH_ID")
	@Expose
	Integer LAUNCH_ID = null;
	@SerializedName("EMAIL")
	@CsvBindByName(column = "EMAIL")
	@Expose
	String EMAIL = null;
	@SerializedName("EMAIL_ISP")
	@CsvBindByName(column = "EMAIL_ISP")
	@Expose
	String EMAIL_ISP = null;
	@SerializedName("EMAIL_FORMAT")
	@CsvBindByName(column = "EMAIL_FORMAT")
	@Expose
	Character EMAIL_FORMAT = null;
	@SerializedName("OFFER_SIGNATURE_ID")
	@CsvBindByName(column = "OFFER_SIGNATURE_ID")
	@Expose
	String OFFER_SIGNATURE_ID = null;
	@SerializedName("OFFER_NAME")
	@CsvBindByName(column = "OFFER_NAME")
	@Expose
	String OFFER_NAME = null;
	@SerializedName("OFFER_CATEGORY")
	@CsvBindByName(column = "OFFER_CATEGORY")
	@Expose
	String OFFER_CATEGORY = null;
	@SerializedName("OFFER_URL")
	@CsvBindByName(column = "OFFER_URL")
	@Expose
	String OFFER_URL = null;
	@SerializedName("OFFER_NUMBER")
	@CsvBindByName(column = "OFFER_NUMBER")
	@Expose
	Integer OFFER_NUMBER = null;
	@SerializedName("BOUNCE_TYPE")
	@CsvBindByName(column = "BOUNCE_TYPE")
	@Expose
	Character BOUNCE_TYPE = null;
	@SerializedName("REASON")
	@CsvBindByName(column = "REASON")
	@Expose
	String REASON = null;
	@SerializedName("REASON_CODE")
	@CsvBindByName(column = "REASON_CODE")
	@Expose
	String REASON_CODE = null;
	@SerializedName("SUBJECT")
	@CsvBindByName(column = "SUBJECT")
	@Expose
	String SUBJECT = null;
	@SerializedName("CONTACT_INFO")
	@CsvBindByName(column = "CONTACT_INFO")
	@Expose
	String CONTACT_INFO = null;
	@SerializedName("EXTERNAL_CAMPAIGN_ID")
	@CsvBindByName(column = "EXTERNAL_CAMPAIGN_ID")
	@Expose
	String EXTERNAL_CAMPAIGN_ID = null;
	@SerializedName("SF_CAMPAIGN_ID")
	@CsvBindByName(column = "SF_CAMPAIGN_ID")
	@Expose
	String SF_CAMPAIGN_ID = null;
	@SerializedName("COMPLAINER_EMAIL")
	@CsvBindByName(column = "COMPLAINER_EMAIL")
	@Expose
	String COMPLAINER_EMAIL = null;
	@SerializedName("SPAM_TYPE")
	@CsvBindByName(column = "SPAM_TYPE")
	@Expose
	Integer SPAM_TYPE = null;
	@SerializedName("COMPLAINT_DT")
	@CsvBindByName(column = "COMPLAINT_DT")
	@Expose
	String COMPLAINT_DT = null;
	@SerializedName("CAMPAIGN_NAME")
	@CsvBindByName(column = "CAMPAIGN_NAME")
	@Expose
	String CAMPAIGN_NAME = null;
	@SerializedName("LAUNCH_NAME")
	@CsvBindByName(column = "LAUNCH_NAME")
	@Expose
	String LAUNCH_NAME = null;
	@SerializedName("LAUNCH_STATUS")
	@CsvBindByName(column = "LAUNCH_STATUS")
	@Expose
	Character LAUNCH_STATUS = null;
	@SerializedName("LAUNCH_TYPE")
	@CsvBindByName(column = "LAUNCH_TYPE")
	@Expose
	Character LAUNCH_TYPE = null;
	@SerializedName("LAUNCH_CHARSET")
	@CsvBindByName(column = "LAUNCH_CHARSET")
	@Expose
	String LAUNCH_CHARSET = null;
	@SerializedName("PURPOSE")
	@CsvBindByName(column = "PURPOSE")
	@Expose
	Character PURPOSE = null;
	@SerializedName("DESCRIPTION")
	@CsvBindByName(column = "DESCRIPTION")
	@Expose
	String DESCRIPTION = null;
	@SerializedName("PRODUCT_CATEGORY")
	@CsvBindByName(column = "PRODUCT_CATEGORY")
	@Expose
	String PRODUCT_CATEGORY = null;
	@SerializedName("PRODUCT_TYPE")
	@CsvBindByName(column = "PRODUCT_TYPE")
	@Expose
	String PRODUCT_TYPE = null;
	@SerializedName("MARKETING_STRATEGY")
	@CsvBindByName(column = "MARKETING_STRATEGY")
	@Expose
	String MARKETING_STRATEGY = null;
	@SerializedName("MARKETING_PROGRAM")
	@CsvBindByName(column = "MARKETING_PROGRAM")
	@Expose
	String MARKETING_PROGRAM = null;
	@SerializedName("LAUNCH_ERROR_CODE")
	@CsvBindByName(column = "LAUNCH_ERROR_CODE")
	@Expose
	String LAUNCH_ERROR_CODE = null;
	@SerializedName("LAUNCH_STARTED_DT")
	@CsvBindByName(column = "LAUNCH_STARTED_DT")
	@Expose
	String LAUNCH_STARTED_DT = null;

//	@SerializedName("DYNAMIC_CONTENT_SIGNATURE_ID")
//	@CsvBindByName(column = "DYNAMIC_CONTENT_SIGNATURE_ID")
//	@Expose
//	String DYNAMIC_CONTENT_SIGNATURE_ID = null;
//	@SerializedName("MESSAGE_SIZE")
//	@CsvBindByName(column = "MESSAGE_SIZE")
//	@Expose
//	String MESSAGE_SIZE = null;
//	@SerializedName("SEGMENT_INFO")
//	@CsvBindByName(column = "SEGMENT_INFO")
//	@Expose
//	String SEGMENT_INFO = null;
	@SerializedName("LAUNCH_COMPLETED_DT")
	@CsvBindByName(column = "LAUNCH_COMPLETED_DT")
	@Expose
	String LAUNCH_COMPLETED_DT = null;
	@SerializedName("PROGRAM_ID")
	@CsvBindByName(column = "PROGRAM_ID")
	@Expose
	String PROGRAM_ID = null;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Integer getEVENT_TYPE_ID() {
		return EVENT_TYPE_ID;
	}

	public void setEVENT_TYPE_ID(Integer eVENT_TYPE_ID) {
		EVENT_TYPE_ID = eVENT_TYPE_ID;
	}

	public Integer getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public void setACCOUNT_ID(Integer aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}

	public Integer getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(Integer lIST_ID) {
		LIST_ID = lIST_ID;
	}

	public Integer getRIID() {
		return RIID;
	}

	public void setRIID(Integer rIID) {
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

	public Integer getCAMPAIGN_ID() {
		return CAMPAIGN_ID;
	}

	public void setCAMPAIGN_ID(Integer cAMPAIGN_ID) {
		CAMPAIGN_ID = cAMPAIGN_ID;
	}

	public Integer getLAUNCH_ID() {
		return LAUNCH_ID;
	}

	public void setLAUNCH_ID(Integer lAUNCH_ID) {
		LAUNCH_ID = lAUNCH_ID;
	}

	public Character getEMAIL_FORMAT() {
		return EMAIL_FORMAT;
	}

	public void setEMAIL_FORMAT(Character eMAIL_FORMAT) {
		EMAIL_FORMAT = eMAIL_FORMAT;
	}

	public String getOFFER_NAME() {
		return OFFER_NAME;
	}

	public void setOFFER_NAME(String oFFER_NAME) {
		OFFER_NAME = oFFER_NAME;
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

	public Integer getOFFER_NUMBER() {
		return OFFER_NUMBER;
	}

	public void setOFFER_NUMBER(Integer oFFER_NUMBER) {
		OFFER_NUMBER = oFFER_NUMBER;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Character getBOUNCE_TYPE() {
		return BOUNCE_TYPE;
	}

	public void setBOUNCE_TYPE(Character bOUNCE_TYPE) {
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

	public String getEMAIL_ISP() {
		return EMAIL_ISP;
	}

	public void setEMAIL_ISP(String eMAIL_ISP) {
		EMAIL_ISP = eMAIL_ISP;
	}

	public String getCOMPLAINER_EMAIL() {
		return COMPLAINER_EMAIL;
	}

	public void setCOMPLAINER_EMAIL(String cOMPLAINER_EMAIL) {
		COMPLAINER_EMAIL = cOMPLAINER_EMAIL;
	}

	public Integer getSPAM_TYPE() {
		return SPAM_TYPE;
	}

	public void setSPAM_TYPE(Integer sPAM_TYPE) {
		SPAM_TYPE = sPAM_TYPE;
	}

	public String getCOMPLAINT_DT() {
		return COMPLAINT_DT;
	}

	public void setCOMPLAINT_DT(String cOMPLAINT_DT) {
		COMPLAINT_DT = cOMPLAINT_DT;
	}

	public String getOFFER_SIGNATURE_ID() {
		return OFFER_SIGNATURE_ID;
	}

	public void setOFFER_SIGNATURE_ID(String oFFER_SIGNATURE_ID) {
		OFFER_SIGNATURE_ID = oFFER_SIGNATURE_ID;
	}

//	public String getDYNAMIC_CONTENT_SIGNATURE_ID() {
//		return DYNAMIC_CONTENT_SIGNATURE_ID;
//	}
//
//	public void setDYNAMIC_CONTENT_SIGNATURE_ID(String dYNAMIC_CONTENT_SIGNATURE_ID) {
//		DYNAMIC_CONTENT_SIGNATURE_ID = dYNAMIC_CONTENT_SIGNATURE_ID;
//	}
//
//	public String getMESSAGE_SIZE() {
//		return MESSAGE_SIZE;
//	}
//
//	public void setMESSAGE_SIZE(String mESSAGE_SIZE) {
//		MESSAGE_SIZE = mESSAGE_SIZE;
//	}
//
//	public String getSEGMENT_INFO() {
//		return SEGMENT_INFO;
//	}
//
//	public void setSEGMENT_INFO(String sEGMENT_INFO) {
//		SEGMENT_INFO = sEGMENT_INFO;
//	}

	public String getEXTERNAL_CAMPAIGN_ID() {
		return EXTERNAL_CAMPAIGN_ID;
	}

	public void setEXTERNAL_CAMPAIGN_ID(String eXTERNAL_CAMPAIGN_ID) {
		EXTERNAL_CAMPAIGN_ID = eXTERNAL_CAMPAIGN_ID;
	}

	public String getSF_CAMPAIGN_ID() {
		return SF_CAMPAIGN_ID;
	}

	public void setSF_CAMPAIGN_ID(String sF_CAMPAIGN_ID) {
		SF_CAMPAIGN_ID = sF_CAMPAIGN_ID;
	}

	public String getCAMPAIGN_NAME() {
		return CAMPAIGN_NAME;
	}

	public void setCAMPAIGN_NAME(String cAMPAIGN_NAME) {
		CAMPAIGN_NAME = cAMPAIGN_NAME;
	}

	public String getLAUNCH_NAME() {
		return LAUNCH_NAME;
	}

	public void setLAUNCH_NAME(String lAUNCH_NAME) {
		LAUNCH_NAME = lAUNCH_NAME;
	}

	public Character getLAUNCH_STATUS() {
		return LAUNCH_STATUS;
	}

	public void setLAUNCH_STATUS(Character lAUNCH_STATUS) {
		LAUNCH_STATUS = lAUNCH_STATUS;
	}

	public Character getLAUNCH_TYPE() {
		return LAUNCH_TYPE;
	}

	public void setLAUNCH_TYPE(Character lAUNCH_TYPE) {
		LAUNCH_TYPE = lAUNCH_TYPE;
	}

	public String getLAUNCH_CHARSET() {
		return LAUNCH_CHARSET;
	}

	public void setLAUNCH_CHARSET(String lAUNCH_CHARSET) {
		LAUNCH_CHARSET = lAUNCH_CHARSET;
	}

	public Character getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(Character pURPOSE) {
		PURPOSE = pURPOSE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public String getPRODUCT_CATEGORY() {
		return PRODUCT_CATEGORY;
	}

	public void setPRODUCT_CATEGORY(String pRODUCT_CATEGORY) {
		PRODUCT_CATEGORY = pRODUCT_CATEGORY;
	}

	public String getPRODUCT_TYPE() {
		return PRODUCT_TYPE;
	}

	public void setPRODUCT_TYPE(String pRODUCT_TYPE) {
		PRODUCT_TYPE = pRODUCT_TYPE;
	}

	public String getMARKETING_STRATEGY() {
		return MARKETING_STRATEGY;
	}

	public void setMARKETING_STRATEGY(String mARKETING_STRATEGY) {
		MARKETING_STRATEGY = mARKETING_STRATEGY;
	}

	public String getMARKETING_PROGRAM() {
		return MARKETING_PROGRAM;
	}

	public void setMARKETING_PROGRAM(String mARKETING_PROGRAM) {
		MARKETING_PROGRAM = mARKETING_PROGRAM;
	}

	public String getLAUNCH_ERROR_CODE() {
		return LAUNCH_ERROR_CODE;
	}

	public void setLAUNCH_ERROR_CODE(String lAUNCH_ERROR_CODE) {
		LAUNCH_ERROR_CODE = lAUNCH_ERROR_CODE;
	}

	public String getLAUNCH_STARTED_DT() {
		return LAUNCH_STARTED_DT;
	}

	public void setLAUNCH_STARTED_DT(String lAUNCH_STARTED_DT) {
		LAUNCH_STARTED_DT = lAUNCH_STARTED_DT;
	}

	public String getLAUNCH_COMPLETED_DT() {
		return LAUNCH_COMPLETED_DT;
	}

	public void setLAUNCH_COMPLETED_DT(String lAUNCH_COMPLETED_DT) {
		LAUNCH_COMPLETED_DT = lAUNCH_COMPLETED_DT;
	}

	public String getPROGRAM_ID() {
		return PROGRAM_ID;
	}

	public void setPROGRAM_ID(String pROGRAM_ID) {
		PROGRAM_ID = pROGRAM_ID;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
