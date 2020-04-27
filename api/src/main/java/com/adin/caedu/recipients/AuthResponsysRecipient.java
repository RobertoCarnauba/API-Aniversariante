package com.adin.caedu.recipients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponsysRecipient {
	
	@Expose
	@SerializedName(value = "authToken")
	private String authToken;
	
	@Expose
	@SerializedName(value = "issuedAt")
	private String issuedAt;
	
	@Expose
	@SerializedName(value = "endPoint")
	private String endPoint;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

}
