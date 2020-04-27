package com.adin.caedu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(name = "VW_INADIMPLENTES")
@Table(name = "VW_INADIMPLENTES", schema = "crm")
public class VW_InadimplentesModel {
	@Id
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("TIPO_INADIMPLENCIA")
	@Expose
	private String TIPO_INADIMPLENCIA;
	@SerializedName("DIAS_ATRASO")
	@Expose
	private String DIAS_ATRASO;
	@SerializedName("codigo_cliente_responsys")
	@Expose
	private String codigo_cliente_responsys;
	@SerializedName("CPF")
	@Expose
	private String CPF;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTIPO_INADIMPLENCIA() {
		return TIPO_INADIMPLENCIA;
	}

	public void setTIPO_INADIMPLENCIA(String tIPO_INADIMPLENCIA) {
		TIPO_INADIMPLENCIA = tIPO_INADIMPLENCIA;
	}

	public String getDIAS_ATRASO() {
		return DIAS_ATRASO;
	}

	public void setDIAS_ATRASO(String dIAS_ATRASO) {
		DIAS_ATRASO = dIAS_ATRASO;
	}

	public String getCodigo_cliente_responsys() {
		return codigo_cliente_responsys;
	}

	public void setCodigo_cliente_responsys(String codigo_cliente_responsys) {
		this.codigo_cliente_responsys = codigo_cliente_responsys;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public VW_InadimplentesModel(String email, String tIPO_INADIMPLENCIA, String dIAS_ATRASO,
			String codigo_cliente_responsys, String cPF) {
		super();
		this.email = email;
		TIPO_INADIMPLENCIA = tIPO_INADIMPLENCIA;
		DIAS_ATRASO = dIAS_ATRASO;
		this.codigo_cliente_responsys = codigo_cliente_responsys;
		CPF = cPF;
	}

	public VW_InadimplentesModel() {
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
