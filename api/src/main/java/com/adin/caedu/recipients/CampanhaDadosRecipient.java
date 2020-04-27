package com.adin.caedu.recipients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CampanhaDadosRecipient {

	public CampanhaDadosRecipient() {
		super();
	}

	@Expose
	@SerializedName(value = "id")
	private String id;

	@Expose
	@SerializedName(value = "name")
	private String name;
	
	@Expose
	@SerializedName(value = "type")
	private String type;
	
	@Expose
	@SerializedName(value = "subject")
	private String subject;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
