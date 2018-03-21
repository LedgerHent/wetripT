package com.viptrip.util;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

import com.viptrip.wetrip.vo.SSOLoginVo;

import cn.sucng.util.Base64Utils;

public class RSAUtils {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	@SuppressWarnings("unused")
	private static final String PUBLIC_KEY = "RSAPublicKey";
	@SuppressWarnings("unused")
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	@SuppressWarnings("unused")
	private static final int MAX_ENCRYPT_BLOCK = 117;
	@SuppressWarnings("unused")
	private static final int MAX_DECRYPT_BLOCK = 128;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map keyMap = new HashMap(2);
		keyMap.put("RSAPublicKey", publicKey);
		keyMap.put("RSAPrivateKey", privateKey);
		return keyMap;
	}

	public static String sign(String encodedData, String privateKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateK);
		signature.update(Base64Utils.decode(encodedData));
		return Base64Utils.encode(signature.sign());
	}

	public static boolean verify(String encodedData, String publicKey,
			String sign) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(publicK);
		signature.update(Base64Utils.decode(encodedData));
		return signature.verify(Base64Utils.decode(sign));
	}

	public static String decryptByPrivateKey(String encodedData,
			String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(2, privateK);
		byte[] encryptedData = Base64Utils.decode(encodedData);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		int i = 0;

		while (inputLen - offSet > 0) {
			byte[] cache;
			if (inputLen - offSet > 128)
				cache = cipher.doFinal(encryptedData, offSet, 128);
			else {
				cache = cipher
						.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * 128;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "utf-8");
	}

	public static String decryptByPublicKey(String encodedData, String publicKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(2, publicK);
		byte[] encryptedData = Base64Utils.decode(encodedData);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		int i = 0;

		while (inputLen - offSet > 0) {
			byte[] cache;
			if (inputLen - offSet > 128)
				cache = cipher.doFinal(encryptedData, offSet, 128);
			else {
				cache = cipher
						.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * 128;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "utf-8");
	}

	public static String encryptByPublicKey(String str, String publicKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key publicK = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(1, publicK);
		byte[] data = str.getBytes("utf-8");
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		int i = 0;

		while (inputLen - offSet > 0) {
			byte[] cache;
			if (inputLen - offSet > 117)
				cache = cipher.doFinal(data, offSet, 117);
			else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * 117;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return Base64Utils.encode(encryptedData);
	}

	public static String encryptByPrivateKey(String str, String privateKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(1, privateK);
		byte[] data = str.getBytes("utf-8");
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		int i = 0;

		while (inputLen - offSet > 0) {
			byte[] cache;
			if (inputLen - offSet > 117)
				cache = cipher.doFinal(data, offSet, 117);
			else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * 117;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return Base64Utils.encode(encryptedData);
	}

	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get("RSAPrivateKey");
		return Base64Utils.encode(key.getEncoded());
	}

	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get("RSAPublicKey");
		return Base64Utils.encode(key.getEncoded());
	}

	/*
	 * public static void main(String[] args) throws Exception { String str =
	 * "viptrip365"; Map genKeyPair = genKeyPair(); String pub =
	 * getPublicKey(genKeyPair); String pri = getPrivateKey(genKeyPair);
	 * System.out.println("私钥：" + pri); System.out.println("公钥：" + pub); //
	 * String en = encryptByPrivateKey(str, pri); // System.out.println("加密前：" +
	 * str); // System.out.println("加密后：" + en); // System.out.println("解密为：" +
	 * decryptByPublicKey(en, pub));
	 * 
	 * System.out.println("RSAUtils.main()====" + encryptByPrivateKey(str,
	 * pri));
	 * System.out.println("------------------------------------------------------"
	 * ); System.out.println("RSAUtils.enclosing_method()"+ decryptByPublicKey(
	 * "tW2Xd+9gLL28fdnBJqk55gql53BO6AsJ42puXIyhkH0T4/a18t6zUzq0QupoPv02NzxLUYIKKH6183KB9R6xeR5YI+E9uIXgg5woPMhoDq0Hexu1zV4IxqQKBILFnt+7M60UxqTYSc2jPzN/t/Qr/Y/acKoF6kKgYDSrqnDhFEY="
	 * ,
	 * "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+hQcarzSGG+vvY6X5v9lykxuUNOS9chaYewviuO9SjMS4m8cBnAg0s/fQh1xcM/tRUHycai3Q0fn5GSdtHn8OCHtPbIqsBuSZnM+iPG5lNXbIsY2QLumoCUvOVBW3h9vE09JiF7lxVvtkguF/Ar13BaOxmaku+m3ZvY4L7LW7IwIDAQAB"
	 * )); }
	 */
	
	public static void main(String[] args) throws Exception {
		//e10adc3949ba59abbe56e057f20f883e
				//E10ADC3949BA59ABBE56E057F20F883E
		
		System.out.println("e10adc3949ba59abbe56e057f20f883e".equalsIgnoreCase("E10ADC3949BA59ABBE56E057F20F883E"));
		
		SSOLoginVo vo = new SSOLoginVo();
		vo.setPass("e10adc3949ba59abbe56e057f20f883e");
		vo.setUser("18817350871");
		vo.setSubmitDirection(true);
		String str = JSON.get().toJson(vo);
		String string = DESEncrypt.encrypt3DES(str);
		System.out.println("RSAUtils.main()==string==" + string);
		System.out.println("RSAUtils.main()==string encode==" + URLEncoder.encode(string));
		System.out.println("RSAUtils.main()==string==" + DESEncrypt.decrypt3DES(string));
		
		Boolean flag = true;
		System.out.println("RSAUtils.main()==flag==" + flag.toString());
	}
	
	
	
}