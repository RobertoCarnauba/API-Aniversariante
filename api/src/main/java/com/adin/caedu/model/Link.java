
package com.adin.caedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("rel")
    @Expose
    public String rel;
    @SerializedName("href")
    @Expose
    public String href;
    @SerializedName("method")
    @Expose
    public String method;

}
