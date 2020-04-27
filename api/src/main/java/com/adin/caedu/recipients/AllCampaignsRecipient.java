package com.adin.caedu.recipients;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCampaignsRecipient {

	@Expose
	@SerializedName(value = "campaigns")
	private List<CampanhaDadosRecipient> campaigns;

	public List<CampanhaDadosRecipient> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<CampanhaDadosRecipient> campaigns) {
		this.campaigns = campaigns;
	}

	@Override
	public String toString() {
		return "AllCampaignsRecipient [campaigns=" + campaigns + "]";
	}
	
	
	
}
