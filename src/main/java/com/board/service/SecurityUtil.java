package com.board.service;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component("securityUtil")
public class SecurityUtil {

	private static String RSA_WEB_KEY = "_RSA_WEB_KEY_";

	private static String RSA_INSTANCE = "RSA";

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public String getRSA_WEB_KEY() {
		return RSA_WEB_KEY;
	}

	public void initRsa(HttpServletRequest request) {

		HttpSession session = request.getSession();
		KeyPairGenerator generator;

		try {

			generator = KeyPairGenerator.getInstance(RSA_INSTANCE);
			generator.initialize(1024);

			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance(RSA_INSTANCE);

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			session.setAttribute(RSA_WEB_KEY, privateKey);

			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

			request.setAttribute("RSAModulus", publicKeyModulus);
			request.setAttribute("RSAExponent", publicKeyExponent);

			System.out.println("생성한다. RSA KEY");
			System.out.println(publicKeyModulus + "........ \n" + publicKeyExponent);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	public String cipherSHA256(String password) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

		messageDigest.update(password.getBytes());

		byte[] bytes = messageDigest.digest();

		String pw = bytesToHex(bytes);

		return pw;

	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];

		}
		return new String(hexChars);
	}

	public String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {

		Cipher cipher = Cipher.getInstance(RSA_INSTANCE);

		byte[] encryptedBytes = hexToByteArray(securedValue);

		System.out.println(Arrays.toString(encryptedBytes) + "여기는 ARRAY.tostring입니다.");

		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

		String decryptedValue = new String(decryptedBytes, "utf-8");

		return decryptedValue;
	}

	public static byte[] hexToByteArray(String hex) {

		int len = hex.length();
		byte[] bytes = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
		}
		return bytes;
	}
}
