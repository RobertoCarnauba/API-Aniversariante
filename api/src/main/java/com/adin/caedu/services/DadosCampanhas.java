package com.adin.caedu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adin.caedu.model.CED_CampaingListModel;
import com.adin.caedu.model.Campaign;
import com.adin.caedu.model.RetriveCampaings;
import com.adin.caedu.recipients.AuthResponsysRecipient;
import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.sett.Settings;
import com.adin.caedu.util.GsonConverter;

@Component
public class DadosCampanhas {
	private LoginResponsysRecipient login = new LoginResponsysRecipient();
	private WebService webPost = new WebService();
	private String responsys_key;
	private String campaingsType[] = { "email", "sms", "push" };
	private String uri = Settings.POST_RSYS9 + Settings.URL_BASE + "/campaigns?type=";
	private List<RetriveCampaings> campaigs = new ArrayList<RetriveCampaings>();

	public void getDadosCampanhas() {
		try {
			for (int i = 0; i < campaingsType.length; i++) {
				// boolean continua = true;
				String auxUri = uri + campaingsType[0];
				// while (continua) {
				String responseRsys = webPost.receive(getResponsys_key(), auxUri);
				if (responseRsys.contains("TOKEN_EXPIRED")) {
					generateKey();
					responseRsys = webPost.receive(getResponsys_key(), auxUri);
				} 
				campaigs.add(GsonConverter.toObject(responseRsys, RetriveCampaings.class));
				/*
				 * if (campaigs.getCampaigns().size() >= 200) {
				 * 
				 * } else { continua = false; }
				 */
				// }
			}
		} catch (Exception e) {
			System.out.println("DadosCampanhas -> getDadosCampanhas -> Erro buscar dados de campanhas \n" + e);
		}
	}

	public void setCampaingsSubject(List<CED_CampaingListModel> campaingsResult) {
		for (int i = 0; i < campaigs.size(); i++) {
			List<Campaign> listCampaings = campaigs.get(i).getCampaigns();
			for (int j = 0; j < listCampaings.size(); j++) {
				for (int k = 0; k < campaingsResult.size(); k++) {
					int resultId = campaingsResult.get(k).getCAMPAIGN_ID();
					int campaingId = listCampaings.get(j).getId(); 
					if (resultId == campaingId) {
						campaingsResult.get(k).setSUBJECT(listCampaings.get(j).getSubject());
					}
				}
			}
		}
	}

	private void generateKey() {
		setResponsys_key(GsonConverter.toObject(webPost.getKey(login), AuthResponsysRecipient.class).getAuthToken());
	}

	public String getResponsys_key() {
		generateKey();
		return responsys_key;
	}

	public void setResponsys_key(String responsys_key_) {
		responsys_key = responsys_key_;
	}

	public LoginResponsysRecipient getLogin() {
		return login;
	}

	public void setLogin(LoginResponsysRecipient login) {
		this.login = login;
	}

	public WebService getWebPost() {
		return webPost;
	}

	public void setWebPost(WebService webPost) {
		this.webPost = webPost;
	}

	public String[] getCampaingsType() {
		return campaingsType;
	}

	public void setCampaingsType(String[] campaingsType) {
		this.campaingsType = campaingsType;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<RetriveCampaings> getCampaigs() {
		return campaigs;
	}

	public void setCampaigs(List<RetriveCampaings> campaigs) {
		this.campaigs = campaigs;
	}
	
	
}
