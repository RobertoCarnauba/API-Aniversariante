package com.adin.caedu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adin.caedu.model.VW_InadimplentesModel;
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

@Component
public class SendInadimplentes {
	private static final LoginResponsysRecipient login = new LoginResponsysRecipient();
	private String responsys_key;
	private WebService webPost;

	static String uri = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET + "/PET_TBINADIMPLENTES_BU/members";
	static String uriContacts = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.MAIN_LIST;

	private List<VW_InadimplentesModel> inadimplentes = new ArrayList<VW_InadimplentesModel>();

	private static int LIMIT = 180;

	public SendInadimplentes() {
		webPost = new WebService();
		generateKey();
	}

	private void generateKey() {
		setResponsys_key(GsonConverter.toObject(webPost.getKey(login), AuthResponsysRecipient.class).getAuthToken());
	}

	public void add(VW_InadimplentesModel inadimplente) {
		inadimplentes.add(inadimplente);
		if (inadimplentes.size() > LIMIT) {
			try {
				getUp();
			} catch (Exception e) {
				ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendInadimplentes getUp " + e.toString());
				System.out.println("SendInadimplentes -> getUp -> Erro ao executar getUp \n" + e);
			}
		}
	}

	public void getUp() {
		try {
			List<String> fieldNames = new ArrayList<String>();
			List<List<String>> records = new ArrayList<List<String>>();

			fieldNames.add("CUSTOMER_ID_");
			fieldNames.add("TIPO_INADIMPLENCIA");
			fieldNames.add("DIAS_ATRASO");

			inadimplentes.forEach(ina -> {
				List<String> record = new ArrayList<String>();
				record.add(ina.getCPF());
				record.add(ina.getTIPO_INADIMPLENCIA());
				record.add(ina.getDIAS_ATRASO());
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
		} catch (Exception e) {
			System.out.println("SendInadimplentes -> getUp INSIDE -> Erro ao executar getUp \n" + e.getMessage());
		}
		inadimplentes.clear();

	}

	public List<String> sentContacts() {
		List<String> listRiids = new ArrayList<String>();
		List<String> fieldNames = new ArrayList<String>();
		List<List<String>> records = new ArrayList<List<String>>();

		fieldNames.add("EMAIL_ADDRESS_");

		inadimplentes.forEach(i -> {
			List<String> record = new ArrayList<String>();
			record.add(i.getEmail());
			records.add(record);
		});

		ResponseResponsysRecipient responseBothObject = new ResponseResponsysRecipient();
		MergeRuleRecipient mergeRuleBoth = new MergeRuleRecipient();
		mergeRuleBoth.setMatchColumnName1("EMAIL_ADDRESS_");
		mergeRuleBoth.setUpdateOnMatch("REPLACE_ALL");

		RecordDataRecipient recordDataBoth = new RecordDataRecipient();

		recordDataBoth.setFieldNames(fieldNames);
		recordDataBoth.setRecords(records);

		ListRecipient recipient = new ListRecipient();

		recipient.setRecordData(recordDataBoth);
		recipient.setMergeRule(mergeRuleBoth);

		if (recipient.getRecordData().getRecords().size() > 0) {
			String responseAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uriContacts);
			if (responseAux.contains("TOKEN_EXPIRED")) {
				generateKey();
				responseAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uriContacts);
			}
			responseBothObject = GsonConverter.toObject(responseAux, ResponseResponsysRecipient.class);
		}
		responseBothObject.getRecordData().getRecords().forEach(o -> {
			if (o.get(0).contains("MERGEFAILED")) {
				listRiids.add("0");
			} else {
				listRiids.add(o.get(0));
			}
		});

		return listRiids;

	}

	public String getResponsys_key() {
		return responsys_key;
	}

	public void setResponsys_key(String responsys_key) {
		this.responsys_key = responsys_key;
	}

	public List<VW_InadimplentesModel> getInadimplentes() {
		return inadimplentes;
	}

	public void setInadimplentes(List<VW_InadimplentesModel> inadimplentes) {
		this.inadimplentes = inadimplentes;
	}

}
