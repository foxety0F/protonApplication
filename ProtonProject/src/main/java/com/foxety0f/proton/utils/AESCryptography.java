package com.foxety0f.proton.utils;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptography {

	private static final String ALGORITHM = "AES";
	private static final String KEY = "34Z8QEF112B7R5ZY";

	public static String encrypt(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(AESCryptography.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		String encryptedValue64 = Base64.getEncoder().encodeToString(encryptedByteValue);
		return encryptedValue64;

	}

	public static String decrypt(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(AESCryptography.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = Base64.getDecoder().decode(value);
		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue, "utf-8");
		return decryptedValue;

	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(AESCryptography.KEY.getBytes(), AESCryptography.ALGORITHM);
		return key;
	}
}
