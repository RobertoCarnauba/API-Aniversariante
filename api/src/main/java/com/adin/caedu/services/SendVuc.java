package com.adin.caedu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adin.caedu.model.DB_VucModel;
import com.adin.caedu.recipients.AuthResponsysRecipient;
import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.recipients.PetRecordsRecipient;
import com.adin.caedu.recipients.RecordDataRecipient;
import com.adin.caedu.sett.ApplicationStatus;
import com.adin.caedu.sett.Settings;
import com.adin.caedu.util.GsonConverter;

@Component
public class SendVuc {
	private static final LoginResponsysRecipient login = new LoginResponsysRecipient();
	private String responsys_key;
	private WebService webPost;

	static String uri = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET + "/PET_TBINADIMPLENTES_BU/members";
	static String uri_PET_PREFERENCIAS = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET
			+ "/PET_PREFERENCIAS/members";
	static String uri_PET_TRANSACIONAL = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET
			+ "/PET_TRANSACIONAL/members";
	static String uri_PET_SAC = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET + "/PET_SAC/members";
	static String uri_PET_CARTAO = Settings.POST_RSYS9 + Settings.URL_BASE + Settings.PET + "/PET_CARTAO/members";

	private List<DB_VucModel> vucs = new ArrayList<DB_VucModel>();

	private static int LIMIT = 10;

	public SendVuc() {
		webPost = new WebService();
		generateKey();
	}

	private void generateKey() {
		setResponsys_key(GsonConverter.toObject(webPost.getKey(login), AuthResponsysRecipient.class).getAuthToken());
	}

	public void add(DB_VucModel vuc) {
		vucs.add(vuc);
		if (vucs.size() > LIMIT) {
			try {
				getUp();
			} catch (Exception e) {
				ApplicationStatus.lastUpdateErrorsSendVWtoRsys.add("SendInadimplentes getUp " + e.toString());
				System.out.println("SendInadimplentes -> getUp -> Erro ao executar getUp \n" + e);
			}
		}
	}

	public void getUp() {
		List<String> fieldNames = new ArrayList<String>();
		List<List<String>> records = new ArrayList<List<String>>();
		// Enviar dados PET_PREFERENCIAS
		fieldNames.add("CUSTOMER_ID_");
		fieldNames.add("CATEGORIA_PREFERIDA_1");
		fieldNames.add("CATEGORIA_PREFERIDA_2");
		fieldNames.add("CATEGORIA_PREFERIDA_3");
		fieldNames.add("SUBCATEGORIA_PREFERIDA_1");
		fieldNames.add("SUBCATEGORIA_PREFERIDA_2");
		fieldNames.add("SUBCATEGORIA_PREFERIDA_3");
		fieldNames.add("GENERO_PREFERENCIAL_1");
		fieldNames.add("GENERO_PREFERENCIAL_2");
		fieldNames.add("GENERO_PREFERENCIAL_3");
		fieldNames.add("ESTILO_PREFERENCIAL_1");
		fieldNames.add("ESTILO_PREFERENCIAL_2");
		fieldNames.add("ESTILO_PREFERENCIAL_3");

		for (int i = 0; i < vucs.size(); i++) {
			List<String> record = new ArrayList<String>();
			record.add(vucs.get(i).getCPF());
			record.add(vucs.get(i).getCATEGORIA_PREFERIDA_1());
			record.add(vucs.get(i).getCATEGORIA_PREFERIDA_2());
			record.add(vucs.get(i).getCATEGORIA_PREFERIDA_3());
			record.add(vucs.get(i).getSUBCATEGORIA_PREFERIDA_1());
			record.add(vucs.get(i).getSUBCATEGORIA_PREFERIDA_2());
			record.add(vucs.get(i).getSUBCATEGORIA_PREFERIDA_3());
			record.add(vucs.get(i).getGENERO_PREFERENCIAL_1());
			record.add(vucs.get(i).getGENERO_PREFERENCIAL_2());
			record.add(vucs.get(i).getGENERO_PREFERENCIAL_3());
			record.add(vucs.get(i).getESTILO_PREFERENCIAL_1());
			record.add(vucs.get(i).getESTILO_PREFERENCIAL_2());
			record.add(vucs.get(i).getESTILO_PREFERENCIAL_3());
			records.add(record);
		}
		sendToResponsys(fieldNames, records, "CUSTOMER_ID", "REPLACE_ALL", uri_PET_PREFERENCIAS);
		fieldNames.clear();
		records.clear();

		// Enviar dados PET_TRANSACIONAL
		fieldNames.add("CUSTOMER_ID_");
		fieldNames.add("R_TRANSACAO");
		fieldNames.add("F_TRANSACAO");
		fieldNames.add("V_TRANSACAO");
		fieldNames.add("CLA_RFV_TRAN");
		fieldNames.add("TKM");
		fieldNames.add("PAGAMENTO_PREFERENCIAL");

		for (int i = 0; i < vucs.size(); i++) {
			List<String> record = new ArrayList<String>();
			record.add(vucs.get(i).getCPF());
			record.add(vucs.get(i).getR_TRANSACAO());
			record.add(vucs.get(i).getF_TRANSACAO());
			record.add(vucs.get(i).getV_TRANSACAO());
			record.add(vucs.get(i).getCLASSIFICACAO_RFV_TRANSACAO());
			record.add(vucs.get(i).getTKM());
			record.add(vucs.get(i).getPAGAMENTO_PREFERENCIAL());
			records.add(record);
		}
		//sendToResponsys(fieldNames, records, "CUSTOMER_ID", "REPLACE_ALL", uri_PET_TRANSACIONAL);
		fieldNames.clear();
		records.clear();

		// Enviar dados PET_SAC
		fieldNames.add("CUSTOMER_ID_");
		fieldNames.add("NUMERO_CHAMADO");
		fieldNames.add("TIPO_CHAMADO");
		fieldNames.add("STATUS");
		fieldNames.add("ATIVO");
		fieldNames.add("DATA_ATIVACAO");

		for (int i = 0; i < vucs.size(); i++) {
			List<String> record = new ArrayList<String>();
			record.add(vucs.get(i).getCPF());
			record.add(vucs.get(i).getNUMERO_CHAMADO());
			record.add(vucs.get(i).getTIPO_CHAMADO());
			record.add(vucs.get(i).getSTATUS_CHAMADO());
			record.add(vucs.get(i).getCARTAO_ATIVO());
			record.add(vucs.get(i).getDATA_ATIVACAO_CARTAO());
			records.add(record);
		}
		sendToResponsys(fieldNames, records, "CUSTOMER_ID", "REPLACE_ALL", uri_PET_SAC);
		fieldNames.clear();
		records.clear();
		// Enviar dados PET_CARTAO
		fieldNames.add("CUSTOMER_ID_");
		fieldNames.add("STATUS_1_COMPRA");
		fieldNames.add("DATA_1_COMPRA");
		fieldNames.add("DATA_VENCIMENTO_FATURA");
		fieldNames.add("FATURA_VENCIDA");
		fieldNames.add("DATA_ULTIMA_COMPRA");
		fieldNames.add("PARCELA_ATUAL");
		fieldNames.add("DATA_ULTIMA_PARCELA");
		fieldNames.add("SALDO_DISPONIVEL_ATUAL");
		fieldNames.add("COMPRAS_PARCELADAS");
		fieldNames.add("STATUS_FATURA_ATUAL");
		fieldNames.add("R_CARTAO");
		fieldNames.add("F_CARTAO");
		fieldNames.add("V_CARTAO");
		fieldNames.add("CLASSIFCACAO_RFV_CARTAO");
		fieldNames.add("CARTOES_ADICIONAIS_1");
		fieldNames.add("CARTOES_ADICIONAIS_2");

		for (int i = 0; i < vucs.size(); i++) {
			List<String> record = new ArrayList<String>();
			record.add(vucs.get(i).getCPF());
			record.add(vucs.get(i).getSTATUS_1_COMPRA());
			record.add(vucs.get(i).getDATA_1_COMPRA());
			record.add(vucs.get(i).getDATA_VENCIMENTO_FATURA());
			record.add(vucs.get(i).getFATURA_VENCIDA());
			record.add(vucs.get(i).getDATA_ULTIMA_COMPRA());
			record.add(vucs.get(i).getPARCELA_ATUAL());
			record.add(vucs.get(i).getDATA_ULTIMA_PARCELA());
			record.add(vucs.get(i).getSALDO_DISPONIVEL_ATUAL());
			record.add(vucs.get(i).getCOMPRAS_PARCELADAS());
			record.add(vucs.get(i).getSTATUS_FATURA_ATUAL());
			record.add(vucs.get(i).getR_CARTAO());
			record.add(vucs.get(i).getF_CARTAO());
			record.add(vucs.get(i).getV_CARTAO());
			record.add(vucs.get(i).getCLASSIFICACAO_RFV_CARTAO());
			record.add(vucs.get(i).getCARTOES_ADICIONAIS_1());
			record.add(vucs.get(i).getCARTOES_ADICIONAIS_2());
			records.add(record);
		}
		sendToResponsys(fieldNames, records, "CUSTOMER_ID", "REPLACE_ALL", uri_PET_CARTAO);
		fieldNames.clear();
		records.clear();

		vucs.clear();

	}

	public void sendToResponsys(List<String> field, List<List<String>> dados, String setMatchColumnName1,
			String updateOnMatch, String uri) {
		try {
			RecordDataRecipient recordData = new RecordDataRecipient();

			recordData.setFieldNames(field);
			recordData.setRecords(dados);

			PetRecordsRecipient recipient = new PetRecordsRecipient();
			recipient.setRecordData(recordData);
			recipient.setInsertOnNoMatch(true);
			recipient.setUpdateOnMatch(updateOnMatch);
			recipient.setMatchColumnName1(setMatchColumnName1);

			if (recipient.getRecordData().getRecords().size() > 0) {
				String response = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uri);
				if (response.contains("TOKEN_EXPIRED")) {
					generateKey();
					response = webPost.send(getResponsys_key(), GsonConverter.toJson(recipient), uri);
				}
			}
		} catch (Exception e) {
			System.out.println("SendVuc -> getUp INSIDE -> Erro ao executar getUp \n" + e.getMessage());
		}
	}

	public String getResponsys_key() {
		return responsys_key;
	}

	public void setResponsys_key(String responsys_key) {
		this.responsys_key = responsys_key;
	}

	public List<DB_VucModel> getVucs() {
		return vucs;
	}

	public void setVucs(List<DB_VucModel> vucs) {
		this.vucs = vucs;
	}

}
