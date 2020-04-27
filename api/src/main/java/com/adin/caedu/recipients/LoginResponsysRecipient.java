package com.adin.caedu.recipients;

import com.adin.caedu.sett.Settings;

public class LoginResponsysRecipient {
	private String user_login, password, auth_type;

	public LoginResponsysRecipient() {
		setUser_login(Settings.USER_LOGIN);
		setPassword(Settings.USER_PASSWORD);
		setAuth_type("password");
	}

	public String getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
