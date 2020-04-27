
package com.adin.caedu.model;

import java.util.List;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Campaign {

	@SerializedName("id")
	@Expose
	public Integer id;
	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("folderName")
	@Expose
	public String folderName;
	@SerializedName("type")
	@Expose
	public String type;
	@SerializedName("purpose")
	@Expose
	public String purpose;
	@SerializedName("listName")
	@Expose
	public String listName;
	@SerializedName("filterPaths")
	@Expose
	public List<String> filterPaths = null;
	@SerializedName("proofListPath")
	@Expose
	public String proofListPath;
	@SerializedName("htmlMessagePath")
	@Expose
	public String htmlMessagePath;
	@SerializedName("enableLinkTracking")
	@Expose
	public Boolean enableLinkTracking;
	@SerializedName("linkTablePath")
	@Expose
	public String linkTablePath;
	@SerializedName("enableExternalTracking")
	@Expose
	public Boolean enableExternalTracking;
	@SerializedName("subject")
	@Expose
	public String subject;
	@SerializedName("fromName")
	@Expose
	public String fromName;
	@SerializedName("fromEmail")
	@Expose
	public String fromEmail;
	@SerializedName("replyToEmail")
	@Expose
	public String replyToEmail;
	@SerializedName("useUTF8")
	@Expose
	public Boolean useUTF8;
	@SerializedName("locale")
	@Expose
	public String locale;
	@SerializedName("trackHTMLOpens")
	@Expose
	public Boolean trackHTMLOpens;
	@SerializedName("trackConversions")
	@Expose
	public Boolean trackConversions;
	@SerializedName("sendTextIfHTMLUnknown")
	@Expose
	public Boolean sendTextIfHTMLUnknown;
	@SerializedName("unsubscribeOption")
	@Expose
	public String unsubscribeOption;
	@SerializedName("autoCloseOption")
	@Expose
	public String autoCloseOption;
	@SerializedName("autoCloseValue")
	@Expose
	public String autoCloseValue;
	@SerializedName("links")
	@Expose
	public List<Link> links = null;
	@SerializedName("seedListPath")
	@Expose
	public String seedListPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<String> getFilterPaths() {
		return filterPaths;
	}

	public void setFilterPaths(List<String> filterPaths) {
		this.filterPaths = filterPaths;
	}

	public String getProofListPath() {
		return proofListPath;
	}

	public void setProofListPath(String proofListPath) {
		this.proofListPath = proofListPath;
	}

	public String getHtmlMessagePath() {
		return htmlMessagePath;
	}

	public void setHtmlMessagePath(String htmlMessagePath) {
		this.htmlMessagePath = htmlMessagePath;
	}

	public Boolean getEnableLinkTracking() {
		return enableLinkTracking;
	}

	public void setEnableLinkTracking(Boolean enableLinkTracking) {
		this.enableLinkTracking = enableLinkTracking;
	}

	public String getLinkTablePath() {
		return linkTablePath;
	}

	public void setLinkTablePath(String linkTablePath) {
		this.linkTablePath = linkTablePath;
	}

	public Boolean getEnableExternalTracking() {
		return enableExternalTracking;
	}

	public void setEnableExternalTracking(Boolean enableExternalTracking) {
		this.enableExternalTracking = enableExternalTracking;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getReplyToEmail() {
		return replyToEmail;
	}

	public void setReplyToEmail(String replyToEmail) {
		this.replyToEmail = replyToEmail;
	}

	public Boolean getUseUTF8() {
		return useUTF8;
	}

	public void setUseUTF8(Boolean useUTF8) {
		this.useUTF8 = useUTF8;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Boolean getTrackHTMLOpens() {
		return trackHTMLOpens;
	}

	public void setTrackHTMLOpens(Boolean trackHTMLOpens) {
		this.trackHTMLOpens = trackHTMLOpens;
	}

	public Boolean getTrackConversions() {
		return trackConversions;
	}

	public void setTrackConversions(Boolean trackConversions) {
		this.trackConversions = trackConversions;
	}

	public Boolean getSendTextIfHTMLUnknown() {
		return sendTextIfHTMLUnknown;
	}

	public void setSendTextIfHTMLUnknown(Boolean sendTextIfHTMLUnknown) {
		this.sendTextIfHTMLUnknown = sendTextIfHTMLUnknown;
	}

	public String getUnsubscribeOption() {
		return unsubscribeOption;
	}

	public void setUnsubscribeOption(String unsubscribeOption) {
		this.unsubscribeOption = unsubscribeOption;
	}

	public String getAutoCloseOption() {
		return autoCloseOption;
	}

	public void setAutoCloseOption(String autoCloseOption) {
		this.autoCloseOption = autoCloseOption;
	}

	public String getAutoCloseValue() {
		return autoCloseValue;
	}

	public void setAutoCloseValue(String autoCloseValue) {
		this.autoCloseValue = autoCloseValue;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public String getSeedListPath() {
		return seedListPath;
	}

	public void setSeedListPath(String seedListPath) {
		this.seedListPath = seedListPath;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}

}
