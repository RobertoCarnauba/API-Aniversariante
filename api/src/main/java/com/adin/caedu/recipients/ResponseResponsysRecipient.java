package com.adin.caedu.recipients;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseResponsysRecipient {

	@Expose
	@SerializedName(value = "recordData")
	private RecordDataRecipient recordData;

	@Expose
	@SerializedName(value = "mergeRule")
	private MergeRuleRecipient mergeRule;

	public RecordDataRecipient getRecordData() {
		return recordData;
	}

	public void setRecordData(RecordDataRecipient recordData) {
		this.recordData = recordData;
	}

	public MergeRuleRecipient getMergeRule() {
		return mergeRule;
	}

	public void setMergeRule(MergeRuleRecipient mergeRule) {
		this.mergeRule = mergeRule;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}

}
