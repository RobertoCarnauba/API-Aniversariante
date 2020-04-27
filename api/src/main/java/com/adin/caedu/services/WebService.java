package com.adin.caedu.services;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.sett.Settings;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebService {

	public String send(String key, Object obj, String uri) {
		WebResource resource = Client.create().
				resource(uri);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, key)
				.post(ClientResponse.class, obj);
		
		return response.getEntity(String.class);
	}
	

	public String delet(String key, Object obj, String uri) {
		WebResource resource = Client.create().
				resource(uri);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, key)
				.delete(ClientResponse.class, obj);
		
		return response.getEntity(String.class);
	}
	
	public String receive(String key, String uri) {
		WebResource resource = Client.create().
				resource(uri);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, key)
				.get(ClientResponse.class);
		return response.getEntity(String.class);
	}
	
	public String getKey(LoginResponsysRecipient login) {
		WebResource resource = Client.create().resource(Settings.URL_LOGIN9);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.header(HttpHeaders.ACCEPT_CHARSET, "UTF-8")
				.post(ClientResponse.class, "user_name="+login.getUser_login()+"&password="+
				login.getPassword()+"&auth_type="+login.getAuth_type());
		return response.getEntity(String.class);
	}
}
