package com.viptrip.hotel.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

public class PayConfig {
	private static Logger logger = LoggerFactory.getLogger(PayConfig.class);
	
	public static final String isTest;
	public static final Integer maxCount;
	public static final String ali_rtnurl;
	public static final String ali_ntfurl;
	public static final String ali_rtnurl_r;
	public static final String ali_ntfurl_r;
	
	private static final String default_isTest = "true";
	private static final Integer default_maxCount = 11;
	private static final String default_ali_rtnurl = "http://61.51.80.138:18181/wetrip/hotelPay/ali_return.act";
	private static final String default_ali_ntfurl = "http://61.51.80.138:18181/wetrip/hotelPay/ali_notify.act";
	private static final String default_ali_rtnurl_r = "http://61.51.80.138:18181/wetrip/hotelPay/ali_return_r.act";
	private static final String default_ali_ntfurl_r = "http://61.51.80.138:18181/wetrip/hotelPay/ali_notify_r.act";

	
	
	
	static{
		String _isTest;
		Integer _maxCount;
		String _ali_rtnurl;
		String _ali_ntfurl;
		String _ali_rtnurl_r;
		String _ali_ntfurl_r;
		try {
			_isTest = PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_IS_TEST, Const.FILE_PAY_HOTEL_CONFIG);
			_maxCount = StringUtil.isEmpty(PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_MAXCOUNT, Const.FILE_PAY_HOTEL_CONFIG))?default_maxCount:Integer.parseInt(PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_MAXCOUNT, Const.FILE_PAY_HOTEL_CONFIG));
			_ali_rtnurl = PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_RETURN_URL, Const.FILE_PAY_HOTEL_CONFIG);
			_ali_ntfurl = PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_NOTIFY_URL, Const.FILE_PAY_HOTEL_CONFIG);
			_ali_rtnurl_r = PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_RETURN_URL_R, Const.FILE_PAY_HOTEL_CONFIG);
			_ali_ntfurl_r = PropertiesUtils.getProperty(Const.PRO_PAY_HOTEL_NOTIFY_URL_R, Const.FILE_PAY_HOTEL_CONFIG);
		} catch (IOException e) {
			_isTest = default_isTest;
			_maxCount = default_maxCount;
			_ali_rtnurl = default_ali_rtnurl;
			_ali_ntfurl = default_ali_ntfurl;
			_ali_rtnurl_r = default_ali_rtnurl_r;
			_ali_ntfurl_r = default_ali_ntfurl_r;
			logger.error("加载酒店支付配置文件失败，使用默认配置");
		}
		isTest = _isTest;
		maxCount = _maxCount;
		ali_rtnurl = _ali_rtnurl;
		ali_ntfurl = _ali_ntfurl;
		ali_rtnurl_r = _ali_rtnurl_r;
		ali_ntfurl_r = _ali_ntfurl_r;
	}
}
