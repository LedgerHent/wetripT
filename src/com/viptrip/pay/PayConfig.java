package com.viptrip.pay;

import java.io.IOException;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;

public class PayConfig {
	
	public static final String isTest;
	public static final Integer maxCount;
	public static final String desKey;
	public static final String desIV;
	public static final String charset;
	public static final String url;

	private static final String default_isTest = "true";

	private static final Integer default_maxCount = 6;
	private static final String default_desKey = "支付密钥";
	private static final String default_desIV = "支付向量";
	private static final String default_charset = "utf-8";
	private static final String default_url = "http://61.51.80.138:8099/wetrip/";

	
	static{
		String isTest1;
		Integer maxCount1;
		String desKey1;
		String desIV1;
		String charset1;
		String url1;
		try {
			isTest1 = PropertiesUtils.getProperty(Const.PRO_PAY_IS_TEST, Const.FILE_PAY_CONFIG);
			maxCount1 = Integer.parseInt(PropertiesUtils.getProperty(Const.PRO_PAY_UNI_MAXCOUNT, Const.FILE_PAY_CONFIG));
			desKey1 = PropertiesUtils.getProperty(Const.PRO_PAY_UNI_DESKEY, Const.FILE_PAY_CONFIG);
			desIV1 = PropertiesUtils.getProperty(Const.PRO_PAY_UNI_DESIV, Const.FILE_PAY_CONFIG);
			charset1 = PropertiesUtils.getProperty(Const.PRO_PAY_UNI_CHARSET, Const.FILE_PAY_CONFIG);
			url1 = PropertiesUtils.getProperty(Const.PRO_PAY_UNI_URL, Const.FILE_PAY_CONFIG);
		} catch (IOException e) {
			isTest1 = default_isTest;
			maxCount1 = default_maxCount;
			desKey1 = default_desKey;
			desIV1 = default_desIV;
			charset1 = default_charset;
			url1 = default_url;
			e.printStackTrace();
		}
		isTest = isTest1;
		maxCount = maxCount1;
		desKey = desKey1;
		desIV = desIV1;
		charset = charset1;
		url = url1;
	}



}
