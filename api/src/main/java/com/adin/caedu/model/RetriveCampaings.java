
package com.adin.caedu.model;

import java.util.List;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetriveCampaings {

	@SerializedName("campaigns")
	@Expose
	public List<Campaign> campaigns = null;
	@SerializedName("links")
	@Expose
	public List<Link_> links = null;

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public List<Link_> getLinks() {
		return links;
	}

	public void setLinks(List<Link_> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
