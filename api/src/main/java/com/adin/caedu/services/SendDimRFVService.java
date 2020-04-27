package com.adin.caedu.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.adin.caedu.model.DB_DimRFVModel;
import com.adin.caedu.model.VW_ContactList;
import com.adin.caedu.recipients.AuthResponsysRecipient;
import com.adin.caedu.recipients.ListRecipient;
import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.recipients.MergeRuleRecipient;
import com.adin.caedu.recipients.PetRecordsRecipient;
import com.adin.caedu.recipients.RecordDataRecipient;
import com.adin.caedu.recipients.ResponseResponsysRecipient;
import com.adin.caedu.sett.ApplicationStatus;
import com.adin.caedu.sett.Settings;
import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
public class SendDimRFVService {

	private static final LoginResponsysRecipient login = new LoginResponsysRecipient();
	private String responsys_key;
	private WebService webPost;

	static String uri = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET + "/PET_TRANSACIONAL/members";
	static String uri_contact = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.MAIN_LIST;

	private List<DB_DimRFVModel> dimRFV = new ArrayList<DB_DimRFVModel>();

	private static int LIMIT = 190;

	public SendDimRFVService() {
		webPost = new WebService();
		generateKey();
	}

	private void generateKey() {
		setResponsys_key(GsonConverter.toObject(webPost.getKey(login), AuthResponsysRecipient.class).getAuthToken());
	}

	public void add(DB_DimRFVModel dim_rfv) {
		dimRFV.add(dim_rfv);
		if (dimRFV.size() > LIMIT) {
			try {
				getUp();
			} catch (Exception e) {
				ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService getUp " + e.toString());
				System.out.println("SendDimRFVService -> getUp -> Erro ao executar getUp \n" + e);
			}
		}
	}

	public void getUp() {
		List<String> fieldNames = new ArrayList<String>();
		List<List<String>> records = new ArrayList<List<String>>();

		fieldNames.add("CUSTOMER_ID_");
		fieldNames.add("R_Transacao");
		fieldNames.add("F_Transacao");
		fieldNames.add("V_Transacao");
		fieldNames.add("Cla_RFV_Tran");

		dimRFV.forEach(rfv -> {
			List<String> record = new ArrayList<String>();
			record.add(rfv.getCPF());
			record.add(rfv.getRECENCIA());
			record.add(rfv.getFREQUENCIA());
			record.add(rfv.getSEGMENTO());
			record.add(rfv.getFAIXA());
			records.add(record);
		});

		RecordDataRecipient recordData = new RecordDataRecipient();

		recordData.setFieldNames(fieldNames);
		recordData.setRecords(records);

		PetRecordsRecipient recipient = new PetRecordsRecipient();
		recipient.setRecordData(recordData);
		recipient.setInsertOnNoMatch(true);
		recipient.setUpdateOnMatch("REPLACE_ALL");
		recipient.setMatchColumnName1("CUSTOMER_ID");

		if (recipient.getRecordData().getRecords().size() > 0) {
			String response = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uri);
			if (response.contains("TOKEN_EXPIRED")) {
				generateKey();
				response = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uri);
			}
		}

		dimRFV.clear();

	}

	public String getResponsys_key() {
		return responsys_key;
	}

	public void setResponsys_key(String responsys_key) {
		this.responsys_key = responsys_key;
	}

	public List<DB_DimRFVModel> getDimRFV() {
		return dimRFV;
	}

	public void setDimRFV(List<DB_DimRFVModel> dimRFV) {
		this.dimRFV = dimRFV;
	}

}
