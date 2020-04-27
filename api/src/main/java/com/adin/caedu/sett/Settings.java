package com.adin.caedu.sett;

public class Settings {

	/*-------------------------------------- Dados de Acesso-----------------------------------------------------------------*/
	public static final String USER_LOGIN = "adin_api";
	public static final String USER_PASSWORD = "Adin@2019_!";

	/*-------------------------------------- Endereços do Responsys ---------------------------------------------------------*/
	public static final String URL_BASE = "/rest/api/v1.3";

	/*-------------------------------------- Endereços de acesso API ---------------------------------------------------------*/
	public static final String POST_RSYS9 = "https://rest001.rsys9.net"; // Responsys versão 8

	/*-------------------------------------- Endereços de autenticação ---------------------------------------------------------*/
	public static final String URL_LOGIN9 = "https://login.rsys9.net" + URL_BASE + "/auth/token"; // Responsys versão 9

	/*-------------------------------------- Dados do Responsys--------------------------------------------------------------*/
	public static final String FOLDER_NAME = "Caedu";
	public static final String OBJECT_NAME = "CAEDU_BASE_UNICA";

	/*--------------------------------------- Dados das listas--------------------------------------------------------------*/
	public static final String MAIN_LIST = "/lists/" + OBJECT_NAME + "/members"; // lista principal
	public static final String PET = "/lists/" + OBJECT_NAME + "/listExtensions"; // listas suplementares 
}
