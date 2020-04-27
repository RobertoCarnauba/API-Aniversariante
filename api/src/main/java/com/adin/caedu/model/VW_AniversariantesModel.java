package com.adin.caedu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.comparator.LiteralComparator;

@Entity(name = "VW_ANIVERSARIO")
@Table(name = "VW_ANIVERSARIO", schema = "crm")
public class VW_AniversariantesModel {

	@Transient
	@CsvBindByName(column = "IdCampanha")
	@CsvBindByPosition(position = 0)
	private Integer ID_CAMPANHA;
	@Transient
	@CsvBindByName(column = "Data Inicio")
	@CsvBindByPosition(position = 1)
	private String DATA_INICIO;
	@Transient
	@CsvBindByName(column = "Data Fim")
	@CsvBindByPosition(position = 2)
	private String DATA_FIM;
	@Id
	@Expose
	@CsvBindByName(column = "Cpf")
	@CsvBindByPosition(position = 3)
	private String CPF;
	@Expose
	@CsvBindByName(column = "Nome")
	@CsvBindByPosition(position = 4)
	private String NOME_COMPLETO;
	@Expose
	@CsvBindByName(column = "Genero")
	@CsvBindByPosition(position = 5)
	private String GENERO;
	@Expose
	@CsvBindByName(column = "Data Nacimento")
	@CsvBindByPosition(position = 6)
	private String DATA_NASCIMENTO;
	@Transient
	@CsvBindByName(column = "DDD Telefone Fixo")
	@CsvBindByPosition(position = 7)
	private String ddd_Telefone_Fixo;
	@Expose
	@CsvBindByName(column = "Telefone Fixo")
	@CsvBindByPosition(position = 8)
	private String TELEFONE_RESIDENCIAL;
	@Expose
	@CsvBindByName(column = "DDD Telefone Celular")
	@CsvBindByPosition(position = 9)
	private String TELEFONE_CELULAR_DDD;
	@Expose
	@CsvBindByName(column = "Telefone Celular")
	@CsvBindByPosition(position = 10)
	private String TELEFONE_CELULAR;
	@Expose
	@CsvBindByName(column = "Email")
	@CsvBindByPosition(position = 11)
	private String EMAIL;
	@Expose
	@CsvBindByName(column = "Cep")
	@CsvBindByPosition(position = 12)
	private String CEP;
	@Transient
	@CsvBindByName(column = "Logradouro")
	@CsvBindByPosition(position = 13)
	private String logradouro;
	@Transient
	@CsvBindByName(column = "Numero")
	@CsvBindByPosition(position = 14)
	private String numero;
	@Transient
	@CsvBindByName(column = "Complemento")
	@CsvBindByPosition(position = 15)
	private String complemento;
	@Transient
	@CsvBindByName(column = "Bairro")
	@CsvBindByPosition(position = 16)
	private String bairro;
	@Transient
	@CsvBindByName(column = "Cidade")
	@CsvBindByPosition(position = 17)
	private String cidade;
	@Transient
	@CsvBindByName(column = "Uf")
	@CsvBindByPosition(position = 18)
	private String uf;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNOME_COMPLETO() {
		return NOME_COMPLETO;
	}

	public void setNOME_COMPLETO(String nOME_COMPLETO) {
		NOME_COMPLETO = nOME_COMPLETO;
	}

	public String getGENERO() {
		return GENERO;
	}

	public void setGENERO(String gENERO) {
		GENERO = gENERO;
	}

	public String getDATA_NASCIMENTO() {
		return DATA_NASCIMENTO;
	}

	public void setDATA_NASCIMENTO(String dATA_NASCIMENTO) {
		DATA_NASCIMENTO = dATA_NASCIMENTO;
	}

	public String getTELEFONE_RESIDENCIAL() {
		return TELEFONE_RESIDENCIAL;
	}

	public void setTELEFONE_RESIDENCIAL(String tELEFONE_RESIDENCIAL) {
		TELEFONE_RESIDENCIAL = tELEFONE_RESIDENCIAL;
	}

	public String getTELEFONE_CELULAR_DDD() {
		return TELEFONE_CELULAR_DDD;
	}

	public void setTELEFONE_CELULAR_DDD(String tELEFONE_CELULAR_DDD) {
		TELEFONE_CELULAR_DDD = tELEFONE_CELULAR_DDD;
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

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public Integer getID_CAMPANHA() {
		return ID_CAMPANHA;
	}

	public void setID_CAMPANHA(Integer iD_CAMPANHA) {
		ID_CAMPANHA = iD_CAMPANHA;
	}

	public String getDATA_INICIO() {
		return DATA_INICIO;
	}

	public void setDATA_INICIO(String dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public String getDATA_FIM() {
		return DATA_FIM;
	}

	public void setDATA_FIM(String dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public String getDdd_Telefone_Fixo() {
		return ddd_Telefone_Fixo;
	}

	public void setDdd_Telefone_Fixo(String ddd_Telefone_Fixo_) {
		ddd_Telefone_Fixo = ddd_Telefone_Fixo_;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro_) {
		logradouro = logradouro_;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero_) {
		numero = numero_;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento_) {
		complemento = complemento_;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro_) {
		bairro = bairro_;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade_) {
		cidade = cidade_;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf_) {
		uf = uf_;
	}

	public VW_AniversariantesModel() {

	}

	public VW_AniversariantesModel(String cPF, String nOME_COMPLETO, String gENERO, String dATA_NASCIMENTO,
			String tELEFONE_RESIDENCIAL, String tELEFONE_CELULAR_DDD, String eMAIL, String tELEFONE_CELULAR, String cEP,
			Integer iD_CAMPANHA, String dATA_INICIO, String dATA_FIM) {
		super();
		CPF = cPF;
		NOME_COMPLETO = nOME_COMPLETO;
		GENERO = gENERO;
		DATA_NASCIMENTO = dATA_NASCIMENTO;
		TELEFONE_RESIDENCIAL = tELEFONE_RESIDENCIAL;
		TELEFONE_CELULAR_DDD = tELEFONE_CELULAR_DDD;
		EMAIL = eMAIL;
		TELEFONE_CELULAR = tELEFONE_CELULAR;
		CEP = cEP;
		ID_CAMPANHA = iD_CAMPANHA;
		DATA_INICIO = dATA_INICIO;
		DATA_FIM = dATA_FIM;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}

}
