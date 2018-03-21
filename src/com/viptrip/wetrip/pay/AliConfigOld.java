package com.viptrip.wetrip.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

public class AliConfigOld {
	private static Logger log = LoggerFactory.getLogger(AliConfigOld.class);
	
	public static final String isTest;
	public static final String ALI_OLD_URL;
	public static final String ALI_OLD_SERVICE;
	public static final String ALI_OLD_KEY;
	public static final String ALI_OLD_PARTNER;
	public static final String ALI_OLD_SELLER_EMAIL;
	public static final String ALI_OLD_PAYMENT_TYPE;
	public static final String ALI_OLD_CHARSET;
	public static final String URL_RETURN_PAY;
	public static final String URL_NOTIFY_PAY;
	
	private static final String DEFAULT_isTest="true";
	
	private static final String DEFAULT_ALI_OLD_URL="https://mapi.alipay.com/gateway.do";
	private static final String DEFAULT_ALI_OLD_SERVICE ="create_direct_pay_by_user";
	private static final String DEFAULT_ALI_OLD_KEY = "scv3fdhd7i4eqysa5vemilensobl3tx8";
	private static final String DEFAULT_ALI_OLD_PARTNER = "2088421258192381";
	private static final String DEFAULT_ALI_OLD_SELLER_EMAIL = "lvbingying@viptrip365.com";
	private static final String DEFAULT_ALI_OLD_PAYMENT_TYPE = "1";
	private static final String DEFAULT_ALI_OLD_CHARSET = "utf-8";
	
	private static final String DEFAULT_URL_RETURN_PAY = "pay/alirtn.act";
	private static final String DEFAULT_URL_NOTIFY_PAY = "pay/alintf.act";
	
	static{
		String isTest1 ;
		String ALI_OLD_URL1 ;
		String ALI_OLD_SERVICE1 ;
		String ALI_OLD_KEY1;
		String ALI_OLD_PARTNER1;
		String ALI_OLD_SELLER_EMAIL1;
		String ALI_OLD_PAYMENT_TYPE1;
		String ALI_OLD_CHARSET1;
		String URL_NOTIFY_PAY1;
		String URL_RETURN_PAY1;
		log.info("开始加载老版支付宝支付配置文件");
		try {
			isTest1 = PropertiesUtils.getProperty(Const.PRO_PAY_IS_TEST, Const.FILE_PAY_CONFIG);
			ALI_OLD_URL1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_URL, Const.FILE_PAY_CONFIG);
			ALI_OLD_SERVICE1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_SERVICE, Const.FILE_PAY_CONFIG);
			ALI_OLD_KEY1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_KEY, Const.FILE_PAY_CONFIG);
			ALI_OLD_PARTNER1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_PARTNER, Const.FILE_PAY_CONFIG);
			ALI_OLD_SELLER_EMAIL1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_SELLER_EMAIL, Const.FILE_PAY_CONFIG);
			ALI_OLD_PAYMENT_TYPE1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_PAYMENT_TYPE, Const.FILE_PAY_CONFIG);
			ALI_OLD_CHARSET1 = PropertiesUtils.getProperty(Const.PRO_ALI_OLD_CHARSET, Const.FILE_PAY_CONFIG);
			
			URL_NOTIFY_PAY1 = PropertiesUtils.getProperty(Const.PRO_ALI_URL_NOTIFY_PAY, Const.FILE_PAY_CONFIG);
			URL_RETURN_PAY1 = PropertiesUtils.getProperty(Const.PRO_ALI_URL_RETURN_PAY, Const.FILE_PAY_CONFIG);
			
			Assert.notNull(isTest1);
			Assert.notNull(ALI_OLD_SERVICE1);
			Assert.notNull(ALI_OLD_KEY1);
			Assert.notNull(ALI_OLD_PARTNER1);
			Assert.notNull(ALI_OLD_SELLER_EMAIL1);
			Assert.notNull(ALI_OLD_PAYMENT_TYPE1);
			Assert.notNull(ALI_OLD_CHARSET1);
			Assert.notNull(URL_NOTIFY_PAY1);
			Assert.notNull(URL_RETURN_PAY1);
			log.info("加载老版支付宝支付配置文件成功");
		} catch (Exception e) {
			isTest1 = DEFAULT_isTest;
			ALI_OLD_URL1 = DEFAULT_ALI_OLD_URL;
			ALI_OLD_SERVICE1 = DEFAULT_ALI_OLD_SERVICE;
			ALI_OLD_KEY1 = DEFAULT_ALI_OLD_KEY;
			ALI_OLD_PARTNER1 = DEFAULT_ALI_OLD_PARTNER;
			ALI_OLD_SELLER_EMAIL1 = DEFAULT_ALI_OLD_SELLER_EMAIL;
			ALI_OLD_PAYMENT_TYPE1 = DEFAULT_ALI_OLD_PAYMENT_TYPE;
			ALI_OLD_CHARSET1 = DEFAULT_ALI_OLD_CHARSET;
			URL_NOTIFY_PAY1 = DEFAULT_URL_NOTIFY_PAY;
			URL_RETURN_PAY1 = DEFAULT_URL_RETURN_PAY;
			log.info("加载老版支付宝支付配置文件失败\r\n" + StringUtil.getExceptionStr(e));
			log.info("加载老版支付宝支付配置文件失败,使用默认配置");
		}
		isTest = isTest1;
		ALI_OLD_URL = ALI_OLD_URL1;
		ALI_OLD_SERVICE = ALI_OLD_SERVICE1;
		ALI_OLD_KEY = ALI_OLD_KEY1;
		ALI_OLD_PARTNER = ALI_OLD_PARTNER1;
		ALI_OLD_SELLER_EMAIL = ALI_OLD_SELLER_EMAIL1;
		ALI_OLD_PAYMENT_TYPE = ALI_OLD_PAYMENT_TYPE1;
		ALI_OLD_CHARSET = ALI_OLD_CHARSET1;
		URL_NOTIFY_PAY = URL_NOTIFY_PAY1;
		URL_RETURN_PAY = URL_RETURN_PAY1;
	}
	
	public enum CHARSET{
		UTF8("UTF-8"),GBK("GBK");
		private String set;
		
		private CHARSET(String set) {
			this.set = set;
		}
		
		public String value() {
			return set;
		}
	}
}
