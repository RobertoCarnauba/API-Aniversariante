package com.adin.caedu.model;

import java.math.BigInteger;

import javax.persistence.Id;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;

public class TesteExcel {
	@Id
	@Expose
	@SerializedName("RIID")
	@CsvBindByName(column = "RIID")
	BigInteger RIID_BU;

	@Expose
	@SerializedName("EMAIL")
	@CsvBindByName(column = "EMAIL")
	String EMAIL_ADDRESS;

	public BigInteger getRIID_BU() {
		return RIID_BU;
	}

	public void setRIID_BU(BigInteger rIID_BU) {
		RIID_BU = rIID_BU;
	}

	public String getEMAIL_ADDRESS() {
		return EMAIL_ADDRESS;
	}

	public void setEMAIL_ADDRESS(String eMAIL_ADDRESS) {
		EMAIL_ADDRESS = eMAIL_ADDRESS;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
