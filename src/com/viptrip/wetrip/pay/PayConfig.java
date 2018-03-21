package com.viptrip.wetrip.pay;

import java.io.IOException;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;

public class PayConfig {
	
	public static final String isTest;
	public static final String NOTIFY_URL;
	public static final String RETURN_URL;
	
	private static final String default_isTest = "true";
	private static final String DEFAULT_NOTIFY_URL="pay/ntf.act";
	private static final String DEFAULT_RETURN_URL="pay/rtn.act";
	
	static{
		String isTest1;
		String NOTIFY_URL1;
		String RETURN_URL1;
		try {
			isTest1 = PropertiesUtils.getProperty(Const.PRO_PAY_IS_TEST, Const.FILE_PAY_CONFIG);
			NOTIFY_URL1 = PropertiesUtils.getProperty(Const.PRO_PAY_NTF_URL, Const.FILE_PAY_CONFIG);
			RETURN_URL1 = PropertiesUtils.getProperty(Const.PRO_PAY_RTN_URL, Const.FILE_PAY_CONFIG);
		} catch (IOException e) {
			isTest1 = default_isTest;
			NOTIFY_URL1 = DEFAULT_NOTIFY_URL;
			RETURN_URL1 = DEFAULT_RETURN_URL;
			e.printStackTrace();
		}
		isTest = isTest1;
		NOTIFY_URL = NOTIFY_URL1;
		RETURN_URL = RETURN_URL1;
	}
}
