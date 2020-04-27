
package com.adin.caedu.model;

import com.adin.caedu.util.GsonConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link_ {

	@SerializedName("rel")
	@Expose
	public String rel;
	@SerializedName("href")
	@Expose
	public String href;
	@SerializedName("method")
	@Expose
	public String method;

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return GsonConverter.toJson(this);
	}
}
