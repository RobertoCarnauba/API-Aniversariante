package com.adin.caedu.sett;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationStatus {

	@SerializedName("lastDBtoResponsys")
	@Expose
	public static List<String> lastDBtoResponsys = new ArrayList<String>();
	@SerializedName("lastDBtoSFTP")
	@Expose
	public static List<String> lastDBtoSFTP = new ArrayList<String>();
	@SerializedName("lastCEDtoDB")
	@Expose
	public static List<String> lastCEDtoDB = new ArrayList<String>();
	@SerializedName("lastUpdateErrorsSendVWtoRsys")
	@Expose
	public static List<String> lastUpdateErrorsSendVWtoRsys = new ArrayList<String>();

	public static String getToString() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("lastDBtoResponsys", lastDBtoResponsys);
			jsonObject.put("lastDBtoSFTP", lastDBtoSFTP);
			jsonObject.put("lastCEDtoDB", lastCEDtoDB);
			jsonObject.put("endpoints",
					"[\"SERVICES\": \"status, forceRetencaoClientes, forceAniversariantes, forceCED,"
							+ " forceRFV/{START}, forceInadimplentes/{START}, forceVUC/{START}, forceVucContacts/{START},"
							+ "forceJob\" ]");

			jsonObject.put("lastUpdateErrorsSendVWtoRsys", lastUpdateErrorsSendVWtoRsys);
		} catch (Exception e) {

		}
		return jsonObject.toString();
	}

	public static void resetDBtoResponsys() {
		lastDBtoResponsys.clear();
	}

	public static void resetDBtoSFTP() {
		lastDBtoSFTP.clear();
	}

	public static void resetCEDtoDB() {
		lastCEDtoDB.clear();
	}
}
