package com.adin.caedu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adin.caedu.model.VW_ContactList;
import com.adin.caedu.recipients.AuthResponsysRecipient;
import com.adin.caedu.recipients.ListRecipient;
import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.recipients.MergeRuleRecipient;
import com.adin.caedu.recipients.RecordDataRecipient;
import com.adin.caedu.recipients.ResponseResponsysRecipient;
import com.adin.caedu.repository.VWContactListRepository;
import com.adin.caedu.sett.ApplicationStatus;
import com.adin.caedu.sett.Settings;
import com.adin.caedu.util.GsonConverter;

@Component
public class SendContactListService {

	private static final LoginResponsysRecipient login = new LoginResponsysRecipient();
	private String responsys_key;
	private WebService webPost;

	static String uri = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.MAIN_LIST;
	static String uri_delet = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.MAIN_LIST;

	private List<VW_ContactList> contacts = new ArrayList<VW_ContactList>();

	private List<VW_ContactList> contacts_email = new ArrayList<VW_ContactList>();
	private List<VW_ContactList> contacts_mobile = new ArrayList<VW_ContactList>();
	private List<VW_ContactList> contacts_both = new ArrayList<VW_ContactList>();

	private List<String> riid_email = new ArrayList<String>();
	private List<String> riid_mobile = new ArrayList<String>();
	private List<String> riid_both = new ArrayList<String>();

	private static int LIMIT = 180;

	public String remove(String riid) {
		String uri_use = uri_delet + "/" + riid;
		System.out.println("URL -> " + uri_use);
//		ApplicationStatus.deleteStatus.add(uri_use);
		String response = webPost.delet(getResponsys_key(), "", uri_use);
		if (response.contains("TOKEN_EXPIRED")) {
			generateKey();
			response = webPost.delet(getResponsys_key(), "", uri_use);
		}
		System.out.println("response " + response);
//		ApplicationStatus.deleteStatus.add(response);
		return response;
	}

	public SendContactListService() {
		webPost = new WebService();
		generateKey();
	}

	private void generateKey() {
		setResponsys_key(GsonConverter.toObject(webPost.getKey(login), AuthResponsysRecipient.class).getAuthToken());
	}

	public void add(VW_ContactList contact) {
		contacts.add(contact);
		if (contacts.size() > LIMIT) {
			try {
				getUp();
			} catch (Exception e) {
				ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService getUp " + e.toString());
				System.out.println("SendContactListService -> getUp -> Erro ao executar getUp \n" + e);
			}
		}
	}

	public void getUp() {
		List<String> fieldNamesBoth = new ArrayList<String>();
		List<String> fieldNamesEmail = new ArrayList<String>();
		List<String> fieldNamesMobileNumber = new ArrayList<String>();

		List<List<String>> recordsMobileNumber = new ArrayList<List<String>>();
		List<List<String>> recordsEmail = new ArrayList<List<String>>();
		List<List<String>> recordsBoth = new ArrayList<List<String>>();

		fieldNamesBoth.add("CPF");
		fieldNamesBoth.add("NOME");
		fieldNamesBoth.add("GENDER");
		fieldNamesBoth.add("DATA_NASCIMENTO");
		fieldNamesBoth.add("FILIAL");
		fieldNamesBoth.add("ULTIMO_NOME");
		fieldNamesBoth.add("POSTAL_STREET_1_");
		fieldNamesBoth.add("NUMERO_RESIDENCIA");
		fieldNamesBoth.add("BAIRRO");
		fieldNamesBoth.add("CITY_");
		fieldNamesBoth.add("POSTAL_CODE_");
		fieldNamesBoth.add("DATA_ATUALIZACAO_REGISTRO");
		fieldNamesBoth.add("STATUSCONTADESCRICAO");
		fieldNamesBoth.add("MOBILE_NUMBER_");
		fieldNamesBoth.add("EMAIL_ADDRESS_");

		fieldNamesEmail.add("CPF");
		fieldNamesEmail.add("NOME");
		fieldNamesEmail.add("GENDER");
		fieldNamesEmail.add("DATA_NASCIMENTO");
		fieldNamesEmail.add("FILIAL");
		fieldNamesEmail.add("ULTIMO_NOME");
		fieldNamesEmail.add("POSTAL_STREET_1_");
		fieldNamesEmail.add("NUMERO_RESIDENCIA");
		fieldNamesEmail.add("BAIRRO");
		fieldNamesEmail.add("CITY_");
		fieldNamesEmail.add("POSTAL_CODE_");
		fieldNamesEmail.add("DATA_ATUALIZACAO_REGISTRO");
		fieldNamesEmail.add("STATUSCONTADESCRICAO");
		fieldNamesEmail.add("EMAIL_ADDRESS_");

		fieldNamesMobileNumber.add("CPF");
		fieldNamesMobileNumber.add("NOME");
		fieldNamesMobileNumber.add("GENDER");
		fieldNamesMobileNumber.add("DATA_NASCIMENTO");
		fieldNamesMobileNumber.add("FILIAL");
		fieldNamesMobileNumber.add("ULTIMO_NOME");
		fieldNamesMobileNumber.add("POSTAL_STREET_1_");
		fieldNamesMobileNumber.add("NUMERO_RESIDENCIA");
		fieldNamesMobileNumber.add("BAIRRO");
		fieldNamesMobileNumber.add("CITY_");
		fieldNamesMobileNumber.add("POSTAL_CODE_");
		fieldNamesMobileNumber.add("DATA_ATUALIZACAO_REGISTRO");
		fieldNamesMobileNumber.add("STATUSCONTADESCRICAO");
		fieldNamesMobileNumber.add("MOBILE_NUMBER_");

		contacts.forEach(cliente -> {
			List<String> record = new ArrayList<String>();
			record.add(cliente.getCPF());
			record.add(cliente.getPRIMEIRO_NOME());
			record.add(cliente.getGENERO());
			record.add(cliente.getDATA_NASCIMENTO());
			record.add(cliente.getFILIAL());
			record.add(cliente.getULTIMO_NOME());
			record.add(cliente.getLOGRADOURO());
			record.add(cliente.getNUMERO_RESIDENCIA());
			record.add(cliente.getBAIRRO());
			record.add(cliente.getCIDADE());
			record.add(cliente.getCEP());
			record.add(cliente.getDATA_ATUALIZACAO_REGISTRO());
			record.add(cliente.getSTATUS());

			if (cliente.getTELEFONE_CELULAR() != null) { // Telefone
				record.add("55" + cliente.getTELEFONE_CELULAR().replace("-", ""));
				if (cliente.getEMAIL() != null) { // Email e Telefone
					record.add(cliente.getEMAIL());
					recordsBoth.add(record);
					contacts_both.add(cliente);
				} else {
					recordsMobileNumber.add(record);
					contacts_mobile.add(cliente);
				}
			} else if (cliente.getEMAIL() != null) { // Email
				record.add(cliente.getEMAIL());
				recordsEmail.add(record);
				contacts_email.add(cliente);
			}

		});

		ResponseResponsysRecipient responseMobileObject = new ResponseResponsysRecipient();
		MergeRuleRecipient mergeRuleMobileNumber = new MergeRuleRecipient();
		mergeRuleMobileNumber.setMatchColumnName1("MOBILE_NUMBER_");

		ResponseResponsysRecipient responseEmailObject = new ResponseResponsysRecipient();
		MergeRuleRecipient mergeRuleEmail = new MergeRuleRecipient();
		mergeRuleEmail.setMatchColumnName1("EMAIL_ADDRESS_");

		ResponseResponsysRecipient responseBothObject = new ResponseResponsysRecipient();
		MergeRuleRecipient mergeRuleBoth = new MergeRuleRecipient();
		mergeRuleBoth.setMatchColumnName1("EMAIL_ADDRESS_");
		mergeRuleBoth.setMatchColumnName2("MOBILE_NUMBER_");

		RecordDataRecipient recordDataMobileNumber = new RecordDataRecipient();
		RecordDataRecipient recordDataEmail = new RecordDataRecipient();
		RecordDataRecipient recordDataBoth = new RecordDataRecipient();

		recordDataMobileNumber.setFieldNames(fieldNamesMobileNumber);
		recordDataMobileNumber.setRecords(recordsMobileNumber);

		recordDataEmail.setFieldNames(fieldNamesEmail);
		recordDataEmail.setRecords(recordsEmail);

		recordDataBoth.setFieldNames(fieldNamesBoth);
		recordDataBoth.setRecords(recordsBoth);

		ListRecipient recipientMobileNumber = new ListRecipient();
		ListRecipient recipientEmail = new ListRecipient();
		ListRecipient recipientBoth = new ListRecipient();

		recipientEmail.setRecordData(recordDataEmail);
		recipientEmail.setMergeRule(mergeRuleEmail);

		recipientMobileNumber.setRecordData(recordDataMobileNumber);
		recipientMobileNumber.setMergeRule(mergeRuleMobileNumber);

		recipientBoth.setRecordData(recordDataBoth);
		recipientBoth.setMergeRule(mergeRuleBoth);

		if (recipientBoth.getRecordData().getRecords().size() > 0) {
			String responseBothAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipientBoth), uri);
			if (responseBothAux.contains("TOKEN_EXPIRED")) {
				generateKey();
				responseBothAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipientBoth), uri);
			}
			responseBothObject = GsonConverter.toObject(responseBothAux, ResponseResponsysRecipient.class);
		}

		if (recipientEmail.getRecordData().getRecords().size() > 0) {
			String responseEmailAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipientEmail), uri);
			if (responseEmailAux.contains("TOKEN_EXPIRED")) {
				generateKey();
				responseEmailAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipientEmail), uri);
			}

			responseEmailObject = GsonConverter.toObject(responseEmailAux, ResponseResponsysRecipient.class);
		}

		if (recipientMobileNumber.getRecordData().getRecords().size() > 0) {
			String responseMobileNumberAux = webPost.send(getResponsys_key(),
					GsonConverter.toJson(recipientMobileNumber), uri);
			if (responseMobileNumberAux.contains("TOKEN_EXPIRED")) {
				generateKey();
				responseMobileNumberAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipientMobileNumber),
						uri);
			}

			responseMobileObject = GsonConverter.toObject(responseMobileNumberAux, ResponseResponsysRecipient.class);

		}

		if (responseEmailObject.getRecordData() != null) {
			responseEmailObject.getRecordData().getRecords().forEach(o -> {
				String result = o.get(0);
				if (!result.contains("FAILED")) {
					riid_email.add(result);
				} else {
					riid_email.add("0");
				}
			});
		}

		if (responseBothObject.getRecordData() != null) {
			responseBothObject.getRecordData().getRecords().forEach(o -> {
				String result = o.get(0);
				if (!result.contains("FAILED")) {
					riid_both.add(result);
				} else {
					riid_both.add(result);
				}
			});
		}

		if (responseMobileObject.getRecordData() != null) {
			responseMobileObject.getRecordData().getRecords().forEach(o -> {
				String result = o.get(0);
				if (!result.contains("FAILED")) {
					riid_mobile.add(result);
				} else {
					riid_mobile.add(result);
				}
			});
		}

		contacts.clear();

	}

	public void saveRiids(VWContactListRepository cli_repository) {
		try {
			for (int i = 0; i < contacts_mobile.size(); i++) {
				try {
					if (!riid_mobile.get(i).contains("MERGEFAILED")) {
						cli_repository.updateRiid(contacts_mobile.get(i).getCPF(),
								Integer.parseInt(riid_mobile.get(i)));
						cli_repository.updateRiidRfv(contacts_mobile.get(i).getCPF(),
								Integer.parseInt(riid_mobile.get(i)));
					}
				} catch (Exception e) {
					ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService saveRiids MOBILE, CPF "
							+ contacts_mobile.get(i).getCPF() + " RIID " + riid_mobile.get(i));
				}
			}
			for (int j = 0; j < contacts_email.size(); j++) {
				try {
					if (!riid_email.get(j).contains("MERGEFAILED")) {
						cli_repository.updateRiid(contacts_email.get(j).getCPF(), Integer.parseInt(riid_email.get(j)));
						cli_repository.updateRiidRfv(contacts_email.get(j).getCPF(),
								Integer.parseInt(riid_email.get(j)));

					}
				} catch (Exception e) {
					ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService saveRiids EMAIL, CPF "
							+ contacts_email.get(j).getCPF() + " RIID " + riid_email.get(j));
				}
			}
			for (int k = 0; k < contacts_both.size(); k++) {
				try {
					if (!riid_both.get(k).contains("MERGEFAILED")) {
						cli_repository.updateRiid(contacts_both.get(k).getCPF(), Integer.parseInt(riid_both.get(k)));
						cli_repository.updateRiidRfv(contacts_both.get(k).getCPF(), Integer.parseInt(riid_both.get(k)));

					}
				} catch (Exception e) {
					ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService saveRiids BOTH, CPF "
							+ contacts_both.get(k).getCPF() + " RIID " + riid_both.get(k));
				}
			}
			cli_repository.flush();
		} catch (Exception e) {
			ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService saveRiids " + e.toString());
			System.out.println("SendContactListService -> saveRiids -> Erro ao executar saveRiids \n" + e);
		}

		contacts_mobile.clear();
		riid_mobile.clear();
		contacts_email.clear();
		riid_email.clear();
		contacts_both.clear();
		riid_both.clear();
	}

	public String getResponsys_key() {
		return responsys_key;
	}

	public void setResponsys_key(String responsys_key) {
		this.responsys_key = responsys_key;
	}

	public List<VW_ContactList> getContacts() {
		return contacts;
	}

	public void setContacts(List<VW_ContactList> contacts) {
		this.contacts = contacts;
	}

}
