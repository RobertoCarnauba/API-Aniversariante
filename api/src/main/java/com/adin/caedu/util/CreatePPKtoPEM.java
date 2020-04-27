package com.adin.caedu.util;
 
import com.chilkatsoft.CkSshKey;

public class CreatePPKtoPEM {
	
	public static String library = "C:\\Users\\ADIN\\Desktop\\drogal\\drogal\\dll\\chilkat.dll";

	static {
		try {
			System.load(library);
		} 
		catch (Error e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

	/**Convert PPK File in PEM File*/
	public static String convert(String file_ppk, String file_pem, String password) {
		CkSshKey key = new CkSshKey();

		boolean success;

		if(password != null)
			key.put_Password(password);

		String keyStr = key.loadText(file_ppk);

		success = key.FromPuttyPrivateKey(keyStr);
		if (success != true) {
			System.out.println(key.lastErrorText());
			return "fail";
		}
		
		if(password == null) {
			String unencryptedKeyStr;
			unencryptedKeyStr = key.toOpenSshPrivateKey(false);
			success = key.SaveText(unencryptedKeyStr, file_pem);
			if (success != true) {
				System.out.println(key.lastErrorText());
				return "fail";
			}
		}

		else {
			String encryptedKeyStr;
			key.put_Password(password);
			encryptedKeyStr = key.toOpenSshPrivateKey(true);
			success = key.SaveText(encryptedKeyStr, file_pem);
			if (success != true) {
				System.out.println(key.lastErrorText());
				return "fail";
			}
		}

		return "OK";

	}

}
 
