package com.adin.caedu.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(name = "DIM_RFV")
@Table(name = "DIM_RFV", schema = "crm")
public class DB_DimRFVModel {
	@Id
	@SerializedName("CPF")
	@Expose
	private String CPF;
	@SerializedName("RECENCIA")
	@Expose
	private String RECENCIA;
	@SerializedName("FREQUENCIA")
	@Expose
	private String FREQUENCIA;
	@SerializedName("SEGMENTO")
	@Expose
	private String SEGMENTO;
	@SerializedName("DATA_ATUALIZA")
	@Expose
	private String DATA_ATUALIZA;
	@SerializedName("FAIXA")
	@Expose
	private String FAIXA;
	@SerializedName("codigo_cliente_responsys")
	@Expose
	private String codigo_cliente_responsys;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRECENCIA() {
		return RECENCIA;
	}

	public void setRECENCIA(String rECENCIA) {
		RECENCIA = rECENCIA;
	}

	public String getFREQUENCIA() {
		return FREQUENCIA;
	}

	public void setFREQUENCIA(String fREQUENCIA) {
		FREQUENCIA = fREQUENCIA;
	}

	public String getSEGMENTO() {
		return SEGMENTO;
	}

	public void setSEGMENTO(String sEGMENTO) {
		SEGMENTO = sEGMENTO;
	}

	public String getDATA_ATUALIZA() {
		return DATA_ATUALIZA;
	}

	public void setDATA_ATUALIZA(String dATA_ATUALIZA) {
		DATA_ATUALIZA = dATA_ATUALIZA;
	}

	public String getFAIXA() {
		return FAIXA;
	}

	public void setFAIXA(String fAIXA) {
		FAIXA = fAIXA;
	}

	public DB_DimRFVModel() {
	}

	public String getCodigo_cliente_responsys() {
		return codigo_cliente_responsys;
	}

	public void setCodigo_cliente_responsys(String codigo_cliente_responsys) {
		this.codigo_cliente_responsys = codigo_cliente_responsys;
	}

	public DB_DimRFVModel(String cPF, String rECENCIA, String fREQUENCIA, String sEGMENTO, String dATA_ATUALIZA,
			String fAIXA, String codigo_Cliente_responsys) {
		super();
		CPF = cPF;
		RECENCIA = rECENCIA;
		FREQUENCIA = fREQUENCIA;
		SEGMENTO = sEGMENTO;
		DATA_ATUALIZA = dATA_ATUALIZA;
		FAIXA = fAIXA;
		codigo_cliente_responsys = codigo_Cliente_responsys;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
