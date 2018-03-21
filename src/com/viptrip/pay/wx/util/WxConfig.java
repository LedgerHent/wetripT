package com.viptrip.pay.wx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

public class WxConfig {
	private static Logger log = LoggerFactory.getLogger(WxConfig.class);
	
	
	public static final String WAP_NAME;
	public static final String WAP_URL;
	
	public static final String APPID;
	public static final String APPSECRET;
	public static final String CERTFILE;
	public static final String MCHID;
	public static final String KEY;
	public static final String DOMAIN;

	public static final String UNIFIEDORDER_URL;
	public static final String ORDERQUERY_URL;
	public static final String CLOSEORDER_URL;
	public static final String REVERSE_URL;
	public static final String REFUND_URL;
	public static final String REFUNDQUERY_URL;
	public static final String DOWNLOADBILLY_URL;
	public static final String REPORT_URL;
	public static final String SHORT_URL;
	public static final String AUTHCODETOOPENID_URL;
	public static final String MICROPAY_URL;
	
	
	private static final String DEFAULT_WAP_NAME = "凯撒商旅";
	private static final String DEFAULT_WAP_URL = "";
	private static final String DEFAULT_APPID = "wxb0a059e1665238e6";
	private static final String DEFAULT_APPSECRET = "";
	private static final String DEFAULT_CERTFILE = "";
	private static final String DEFAULT_MCHID = "1363014402";
	private static final String DEFAULT_KEY = "3232KFDKSFKS23FSkdfkdlgldlflsfls";
	private static final String DEFAULT_DOMAIN = "viptrip365.com";
	private static final String DEFAULT_UNIFIEDORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
	private static final String DEFAULT_ORDERQUERY_URL="https://api.mch.weixin.qq.com/pay/orderquery";
	private static final String DEFAULT_CLOSEORDER_URL="https://api.mch.weixin.qq.com/pay/closeorder";
	private static final String DEFAULT_REVERSE_URL="https://api.mch.weixin.qq.com/secapi/pay/reverse";
	private static final String DEFAULT_REFUND_URL="https://api.mch.weixin.qq.com/secapi/pay/refund";
	private static final String DEFAULT_REFUNDQUERY_URL="https://api.mch.weixin.qq.com/pay/refundquery";
	private static final String DEFAULT_DOWNLOADBILLY_URL="https://api.mch.weixin.qq.com/pay/downloadbill";
	private static final String DEFAULT_REPORT_URL="https://api.mch.weixin.qq.com/payitil/report";
	private static final String DEFAULT_SHORT_URL="https://api.mch.weixin.qq.com/tools/shorturl";
	private static final String DEFAULT_AUTHCODETOOPENID_URL="https://api.mch.weixin.qq.com/tools/authcodetoopenid";
	private static final String DEFAULT_MICROPAY_URL="https://api.mch.weixin.qq.com/pay/micropay";
	//mweb支付通知url
	
	static{
		String WAP_NAME1;
		String WAP_URL1;
		String APPID1;
		String APPSECRET1;
		String CERTFILE1;
		String MCHID1;
		String KEY1;
		String DOMAIN1;
		String UNIFIEDORDER_URL1;
		String ORDERQUERY_URL1;
		String CLOSEORDER_URL1;
		String REVERSE_URL1;
		String REFUND_URL1;
		String REFUNDQUERY_URL1;
		String DOWNLOADBILLY_URL1;
		String REPORT_URL1;
		String SHORT_URL1;
		String AUTHCODETOOPENID_URL1;
		String MICROPAY_URL1;
		log.info("开始加载微信支付配置文件");
		try {
			WAP_NAME1 = PropertiesUtils.getProperty(Const.PRO_WX_WAP_NAME, Const.FILE_PAY_CONFIG);
			WAP_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_WAP_URL, Const.FILE_PAY_CONFIG);
			APPID1 = PropertiesUtils.getProperty(Const.PRO_WX_APPID, Const.FILE_PAY_CONFIG);
			APPSECRET1 = PropertiesUtils.getProperty(Const.PRO_WX_APPSECRET, Const.FILE_PAY_CONFIG);
			CERTFILE1 = PropertiesUtils.getProperty(Const.PRO_WX_CERTFILE, Const.FILE_PAY_CONFIG);
			MCHID1 = PropertiesUtils.getProperty(Const.PRO_WX_MACHID, Const.FILE_PAY_CONFIG);
			KEY1 = PropertiesUtils.getProperty(Const.PRO_WX_KEY, Const.FILE_PAY_CONFIG);
			DOMAIN1 = PropertiesUtils.getProperty(Const.PRO_WX_DOMAIN, Const.FILE_PAY_CONFIG);
			UNIFIEDORDER_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_UNIFIED_ORDER, Const.FILE_PAY_CONFIG);
			ORDERQUERY_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_ORDER_QUERY, Const.FILE_PAY_CONFIG);
			CLOSEORDER_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_CLOSE_ORDER, Const.FILE_PAY_CONFIG);
			REVERSE_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_REVERSE, Const.FILE_PAY_CONFIG);
			REFUND_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_REFUND, Const.FILE_PAY_CONFIG);
			REFUNDQUERY_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_REFUNDQUERY, Const.FILE_PAY_CONFIG);
			DOWNLOADBILLY_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_DOWNLOADBILLY, Const.FILE_PAY_CONFIG);
			REPORT_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_REPORT, Const.FILE_PAY_CONFIG);
			SHORT_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_SHORT, Const.FILE_PAY_CONFIG);
			AUTHCODETOOPENID_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_AUTHCODETOOPENID, Const.FILE_PAY_CONFIG);
			MICROPAY_URL1 = PropertiesUtils.getProperty(Const.PRO_WX_URL_MICROPAY, Const.FILE_PAY_CONFIG);
			Assert.notNull(WAP_NAME1);
			Assert.notNull(WAP_URL1);
			Assert.notNull(APPID1);
			Assert.notNull(APPSECRET1);
			Assert.notNull(CERTFILE1);
			Assert.notNull(MCHID1);
			Assert.notNull(KEY1);
			Assert.notNull(DOMAIN1);
			Assert.notNull(UNIFIEDORDER_URL1);
			Assert.notNull(ORDERQUERY_URL1);
			Assert.notNull(CLOSEORDER_URL1);
			Assert.notNull(REVERSE_URL1);
			Assert.notNull(REFUND_URL1);
			Assert.notNull(REFUNDQUERY_URL1);
			Assert.notNull(DOWNLOADBILLY_URL1);
			Assert.notNull(REPORT_URL1);
			Assert.notNull(SHORT_URL1);
			Assert.notNull(AUTHCODETOOPENID_URL1);
			Assert.notNull(MICROPAY_URL1);
			log.info("加载微信支付配置文件成功");
		} catch (Exception e) {
			log.error("加载微信支付配置文件失败\r\n" + StringUtil.getExceptionStr(e));
			log.info("加载微信支付配置文件失败,使用默认配置");
			WAP_NAME1 = DEFAULT_WAP_NAME;
			WAP_URL1 = DEFAULT_WAP_URL;
			APPID1 = DEFAULT_APPID;
			APPSECRET1 = DEFAULT_APPSECRET;
			CERTFILE1 = DEFAULT_CERTFILE;
			MCHID1 = DEFAULT_MCHID;
			KEY1 = DEFAULT_KEY;
			DOMAIN1 = DEFAULT_DOMAIN;
			UNIFIEDORDER_URL1 = DEFAULT_UNIFIEDORDER_URL;
			ORDERQUERY_URL1 = DEFAULT_ORDERQUERY_URL;
			CLOSEORDER_URL1 = DEFAULT_CLOSEORDER_URL;
			REVERSE_URL1 = DEFAULT_REVERSE_URL;
			REFUND_URL1 = DEFAULT_REFUND_URL;
			REFUNDQUERY_URL1 = DEFAULT_REFUNDQUERY_URL;
			DOWNLOADBILLY_URL1 = DEFAULT_DOWNLOADBILLY_URL;
			REPORT_URL1 = DEFAULT_REPORT_URL;
			SHORT_URL1 = DEFAULT_SHORT_URL;
			AUTHCODETOOPENID_URL1 = DEFAULT_AUTHCODETOOPENID_URL;
			MICROPAY_URL1 = DEFAULT_MICROPAY_URL;
		}
		WAP_NAME = WAP_NAME1;
		WAP_URL = WAP_URL1;
		UNIFIEDORDER_URL = UNIFIEDORDER_URL1;
		ORDERQUERY_URL = ORDERQUERY_URL1;
		CLOSEORDER_URL = CLOSEORDER_URL1;
		REVERSE_URL = REVERSE_URL1;
		REFUND_URL = REFUND_URL1;
		REFUNDQUERY_URL = REFUNDQUERY_URL1;
		DOWNLOADBILLY_URL = DOWNLOADBILLY_URL1;
		REPORT_URL = REPORT_URL1;
		SHORT_URL = SHORT_URL1;
		AUTHCODETOOPENID_URL = AUTHCODETOOPENID_URL1;
		MICROPAY_URL = MICROPAY_URL1;
		APPID = APPID1;
		APPSECRET = APPSECRET1;
		CERTFILE = CERTFILE1;
		MCHID = MCHID1;
		KEY = KEY1;
		DOMAIN = DOMAIN1;
	}
	
	/*UNIFIEDORDER_URL
	ORDERQUERY_URL
	CLOSEORDER_URL
	REVERSE_URL
	REFUND_URL
	REFUNDQUERY_URL
	DOWNLOADBILLY_URL
	REPORT_URL
	SHORT_URL
	AUTHCODETOOPENID_URL
	MICROPAY_URL*/
	
	/**
	 * 字符编码
	 * @author selfwhisper
	 *
	 */
	public enum CHARSET{
		UTF8("UTF-8"),GBK("GBK");
		private String desc;
		
		private CHARSET(String desc){
			this.desc = desc;
		}
		
		public String val(){
			return this.desc;
		}
	}
	
	/**
	 * 签名加密类型
	 * @author selfwhisper
	 *
	 */
	public enum SIGNTYPE{
		MD5("MD5"),SHA256("SHA256");
		private String desc;
		
		private SIGNTYPE(String desc){
			this.desc = desc;
		}
		
		public String val(){
			return this.desc;
		}
	}
}
