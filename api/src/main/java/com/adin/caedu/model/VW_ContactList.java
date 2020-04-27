package com.adin.caedu.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(name = "VW_CLIENTE")
@Table(name = "VW_CLIENTE", schema = "crm")
public class VW_ContactList {

	@Id
	@SerializedName("CPF")
	@Expose
	private String CPF;
//	@SerializedName("STATUSCONTADESCRICAO")
//	@Expose
//	private String STATUSCONTADESCRICAO;
	@SerializedName("PRIMEIRO_NOME")
	@Expose
	private String PRIMEIRO_NOME;
	@SerializedName("ULTIMO_NOME")
	@Expose
	private String ULTIMO_NOME;
	@SerializedName("EMAIL")
	@Expose
	private String EMAIL;
	@SerializedName("TELEFONE_CELULAR")
	@Expose
	private String TELEFONE_CELULAR;
	@SerializedName("TELEFONE_CELULAR_DDD")
	@Expose
	private String TELEFONE_CELULAR_DDD;
	@SerializedName("DATA_NASCIMENTO")
	@Expose
	private String DATA_NASCIMENTO;
	@SerializedName("LOGRADOURO")
	@Expose
	private String LOGRADOURO;
	@SerializedName("NUMERO_RESIDENCIA")
	@Expose
	private String NUMERO_RESIDENCIA;
	@SerializedName("BAIRRO")
	@Expose
	private String BAIRRO;
	@SerializedName("CIDADE")
	@Expose
	private String CIDADE;
	@SerializedName("CEP")
	@Expose
	private String CEP;
	@SerializedName("GENERO")
	@Expose
	private String GENERO;
	@SerializedName("FILIAL")
	@Expose
	private String FILIAL;
	@SerializedName("DATA_ATUALIZACAO_REGISTRO")
	@Expose
	private String DATA_ATUALIZACAO_REGISTRO;
	@SerializedName("MAX_DATE")
	@Expose
	private String MAX_DATE;
	@SerializedName("CODIGO_CLIENTE_AVANTSYS")
	@Expose
	private String CODIGO_CLIENTE_AVANTSYS;
	@SerializedName("STATUS")
	@Expose
	private String STATUS;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

//	public String getSTATUSCONTADESCRICAO() {
//		return STATUSCONTADESCRICAO;
//	}
//
//	public void setSTATUSCONTADESCRICAO(String sTATUSCONTADESCRICAO) {
//		STATUSCONTADESCRICAO = sTATUSCONTADESCRICAO;
//	}

	public String getPRIMEIRO_NOME() {
		return PRIMEIRO_NOME;
	}

	public void setPRIMEIRO_NOME(String pRIMEIRO_NOME) {
		PRIMEIRO_NOME = pRIMEIRO_NOME;
	}

	public String getULTIMO_NOME() {
		return ULTIMO_NOME;
	}

	public void setULTIMO_NOME(String uLTIMO_NOME) {
		ULTIMO_NOME = uLTIMO_NOME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getTELEFONE_CELULAR() {
		return TELEFONE_CELULAR;
	}

	public void setTELEFONE_CELULAR(String tELEFONE_CELULAR) {
		TELEFONE_CELULAR = tELEFONE_CELULAR;
	}

	public String getTELEFONE_CELULAR_DDD() {
		return TELEFONE_CELULAR_DDD;
	}

	public void setTELEFONE_CELULAR_DDD(String tELEFONE_CELULAR_DDD) {
		TELEFONE_CELULAR_DDD = tELEFONE_CELULAR_DDD;
	}

	public String getDATA_NASCIMENTO() {
		return DATA_NASCIMENTO;
	}

	public void setDATA_NASCIMENTO(String dATA_NASCIMENTO) {
		DATA_NASCIMENTO = dATA_NASCIMENTO;
	}

	public String getLOGRADOURO() {
		return LOGRADOURO;
	}

	public void setLOGRADOURO(String lOGRADOURO) {
		LOGRADOURO = lOGRADOURO;
	}

	public String getNUMERO_RESIDENCIA() {
		return NUMERO_RESIDENCIA;
	}

	public void setNUMERO_RESIDENCIA(String nUMERO_RESIDENCIA) {
		NUMERO_RESIDENCIA = nUMERO_RESIDENCIA;
	}

	public String getBAIRRO() {
		return BAIRRO;
	}

	public void setBAIRRO(String bAIRRO) {
		BAIRRO = bAIRRO;
	}

	public String getCIDADE() {
		return CIDADE;
	}

	public void setCIDADE(String cIDADE) {
		CIDADE = cIDADE;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getGENERO() {
		return GENERO;
	}

	public void setGENERO(String gENERO) {
		GENERO = gENERO;
	}

	public String getFILIAL() {
		return FILIAL;
	}

	public void setFILIAL(String fILIAL) {
		FILIAL = fILIAL;
	}

	public String getDATA_ATUALIZACAO_REGISTRO() {
		return DATA_ATUALIZACAO_REGISTRO;
	}

	public void setDATA_ATUALIZACAO_REGISTRO(String dATA_ATUALIZACAO_REGISTRO) {
		DATA_ATUALIZACAO_REGISTRO = dATA_ATUALIZACAO_REGISTRO;
	}

	public String getMAX_DATE() {
		return MAX_DATE;
	}

	public void setMAX_DATE(String mAX_DATE) {
		MAX_DATE = mAX_DATE;
	}

	public String getCODIGO_CLIENTE_AVANTSYS() {
		return CODIGO_CLIENTE_AVANTSYS;
	}

	public void setCODIGO_CLIENTE_AVANTSYS(String cODIGO_CLIENTE_AVANTSYS) {
		CODIGO_CLIENTE_AVANTSYS = cODIGO_CLIENTE_AVANTSYS;
	}

	public VW_ContactList() {
		CODIGO_CLIENTE_AVANTSYS = "0";
		DATA_ATUALIZACAO_REGISTRO = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public VW_ContactList(String cPF, /* String sTATUSCONTADESCRICAO, */ String pRIMEIRO_NOME, String uLTIMO_NOME,
			String eMAIL, String tELEFONE_CELULAR, String tELEFONE_CELULAR_DDD, String dATA_NASCIMENTO,
			String lOGRADOURO, String nUMERO_RESIDENCIA, String bAIRRO, String cIDADE, String cEP, String gENERO,
			String fILIAL, String dATA_ATUALIZACAO_REGISTRO, String mAX_DATE, String cODIGO_CLIENTE_AVANTSYS,
			String STATUS) {
		super();
		CPF = cPF;
		// STATUSCONTADESCRICAO = sTATUSCONTADESCRICAO;
		PRIMEIRO_NOME = pRIMEIRO_NOME;
		ULTIMO_NOME = uLTIMO_NOME;
		EMAIL = eMAIL;
		TELEFONE_CELULAR = tELEFONE_CELULAR;
		TELEFONE_CELULAR_DDD = tELEFONE_CELULAR_DDD;
		DATA_NASCIMENTO = dATA_NASCIMENTO;
		LOGRADOURO = lOGRADOURO;
		NUMERO_RESIDENCIA = nUMERO_RESIDENCIA;
		BAIRRO = bAIRRO;
		CIDADE = cIDADE;
		CEP = cEP;
		GENERO = gENERO;
		FILIAL = fILIAL;
		DATA_ATUALIZACAO_REGISTRO = dATA_ATUALIZACAO_REGISTRO;
		MAX_DATE = mAX_DATE;
		CODIGO_CLIENTE_AVANTSYS = cODIGO_CLIENTE_AVANTSYS;
		STATUS = STATUS;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
