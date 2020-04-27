package com.adin.caedu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adin.caedu.model.DB_VucModel;
import com.adin.caedu.model.VW_ContactList;
import com.adin.caedu.recipients.AuthResponsysRecipient;
import com.adin.caedu.recipients.ListRecipient;
import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.recipients.MergeRuleRecipient;
import com.adin.caedu.recipients.PetRecordsRecipient;
import com.adin.caedu.recipients.RecordDataRecipient;
import com.adin.caedu.recipients.ResponseResponsysRecipient;
import com.adin.caedu.repository.DB_VucRepository;
import com.adin.caedu.repository.VWContactListRepository;
import com.adin.caedu.sett.ApplicationStatus;
import com.adin.caedu.sett.Settings;
import com.adin.caedu.util.GsonConverter;

@Component
public class SendVucContacts {
	private static final LoginResponsysRecipient login = new LoginResponsysRecipient();
	private String responsys_key;
	private WebService webPost;

	static String uri_cliente = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.MAIN_LIST;

	private List<DB_VucModel> contacts = new ArrayList<DB_VucModel>();

	private List<DB_VucModel> contacts_email = new ArrayList<DB_VucModel>();
	private List<DB_VucModel> contacts_mobile = new ArrayList<DB_VucModel>();
	private List<DB_VucModel> contacts_both = new ArrayList<DB_VucModel>();

	private List<String> riid_email = new ArrayList<String>();
	private List<String> riid_mobile = new ArrayList<String>();
	private List<String> riid_both = new ArrayList<String>();

	private static int LIMIT = 190;

	public SendVucContacts() {
		webPost = new WebService();
		generateKey();
	}

	private void generateKey() {
		setResponsys_key(GsonConverter.toObject(webPost.getKey(login), AuthResponsysRecipient.class).getAuthToken());
	}

	public void add(DB_VucModel vuc) {
		contacts.add(vuc);
		if (contacts.size() > LIMIT) {
			try {
				getUp();
			} catch (Exception e) {
				ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendInadimplentes getUp " + e.toString());
				System.out.println("SendInadimplentes -> getUp -> Erro ao executar getUp \n" + e);
			}
		}
	}

	public void getUp() {
		sendToRsys("email");
		contacts.clear();
	}

	public void sendToRsys(String contactColumn) {
		List<String> fieldNames = new ArrayList<String>();
		List<List<String>> records = new ArrayList<List<String>>();

		fieldNames.add("CUSTOMER_ID_");
		fieldNames.add("CPF");

//		fieldNames.add("NOME");
//		fieldNames.add("GENDER");
//		fieldNames.add("DATA_NASCIMENTO");
//		fieldNames.add("FILIAL");
//		fieldNames.add("ULTIMO_NOME");
//		fieldNames.add("POSTAL_STREET_1_");
//		fieldNames.add("NUMERO_RESIDENCIA");
//		fieldNames.add("BAIRRO");
//		fieldNames.add("CITY_");
//		fieldNames.add("POSTAL_CODE_");
//		fieldNames.add("DATA_ATUALIZACAO_REGISTRO");
//		fieldNames.add("STATUSCONTADESCRICAO");
//
//		if (contactColumn == "mobile") {
//			fieldNames.add("MOBILE_NUMBER_");
//		} else if (contactColumn == "email") {
//			fieldNames.add("EMAIL_ADDRESS_");
//		} else if (contactColumn == "both") {
//			fieldNames.add("MOBILE_NUMBER_");
//			fieldNames.add("EMAIL_ADDRESS_");
//		}

		contacts.forEach(cliente -> {
			List<String> record = new ArrayList<String>();
			record.add(cliente.getCPF());
			record.add(cliente.getCPF());
			records.add(record);

//			record.add(cliente.getPRIMEIRO_NOME());
//			record.add(cliente.getGENERO());
//			record.add(cliente.getDATA_NASCIMENTO());
//			record.add(cliente.getLOJA_CADASTRO());
//			record.add(cliente.getULTIMO_NOME());
//			record.add(cliente.getLOGRADOURO());
//			record.add(cliente.getNUMERO());
//			record.add(cliente.getBAIRRO());
//			record.add(cliente.getCIDADE());
//			record.add(cliente.getCEP());
//			record.add(cliente.getATUALIZACLI());
//			record.add("");
//
//			if (contactColumn == "mobile") {
//				if (cliente.getTELEFONE() != null) {
//					record.add("55" + cliente.getTELEFONE());
//					records.add(record);
//					contacts_mobile.add(cliente);
//				}
//			} else if (contactColumn == "email") {
//				if (cliente.getEMAIL() != null) {
//					record.add(cliente.getEMAIL());
//					records.add(record);
//					contacts_email.add(cliente);
//				}
//			} else if (contactColumn == "both") {
//				if (cliente.getTELEFONE() != null && cliente.getEMAIL() != null) {
//					record.add("55" + cliente.getTELEFONE());
//					record.add(cliente.getEMAIL());
//					records.add(record);
//					contacts_both.add(cliente);
//				}
//			}
		});

		MergeRuleRecipient mergeRule = new MergeRuleRecipient();
		mergeRule.setMatchColumnName1("CPF");

		RecordDataRecipient recordData = new RecordDataRecipient();
		recordData.setFieldNames(fieldNames);
		recordData.setRecords(records);

		ListRecipient recipient = new ListRecipient();
		recipient.setRecordData(recordData);
		recipient.setMergeRule(mergeRule);

//		System.out.println("recipient " + recipient.toString());
		if (recipient.getRecordData().getRecords().size() > 0) {
			String responseAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uri_cliente);
			if (responseAux.contains("TOKEN_EXPIRED")) {
				generateKey();
				responseAux = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uri_cliente);
			}

			ResponseResponsysRecipient responseObject = GsonConverter.toObject(responseAux,
					ResponseResponsysRecipient.class);

		}
		contacts.clear();
	}

	public void saveRiids(DB_VucRepository cli_repository) {
		try {
			for (int i = 0; i < contacts_mobile.size(); i++) {
				try {
					if (!riid_mobile.get(i).contains("MERGEFAILED")) {
//						System.out.println("CPF: " + contacts_mobile.get(i).getCPF() + " RIID " + riid_mobile.get(i));
						cli_repository.updateRiid(contacts_mobile.get(i).getCPF(),
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
//						System.out.println("CPF: " + contacts_mobile.get(j).getCPF() + " RIID " + riid_mobile.get(j));
						cli_repository.updateRiid(contacts_email.get(j).getCPF(), Integer.parseInt(riid_email.get(j)));

					}
				} catch (Exception e) {
					ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendContactListService saveRiids EMAIL, CPF "
							+ contacts_email.get(j).getCPF() + " RIID " + riid_email.get(j));
				}
			}
			for (int k = 0; k < contacts_both.size(); k++) {
				try {
					if (!riid_both.get(k).contains("MERGEFAILED")) {
//						System.out.println("CPF: " + contacts_mobile.get(k).getCPF() + " RIID " + riid_mobile.get(k));
						cli_repository.updateRiid(contacts_both.get(k).getCPF(), Integer.parseInt(riid_both.get(k)));
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

	public List<DB_VucModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<DB_VucModel> contacts) {
		this.contacts = contacts;
	}

}
