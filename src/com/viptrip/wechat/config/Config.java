package com.viptrip.wechat.config;

import java.util.HashMap;
import java.util.Map;

import weixin.mp.api.WxMpInMemoryConfigStorage;

public class Config {

	/**
	 * 获取微信公众号的配置
	 * @return
	 */
	public static WxMpInMemoryConfigStorage GetWechatConfig() {
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(Wechat_AppID);
		config.setSecret(Wechat_Secret);
		config.setToken(Wechat_Token);
		config.setAesKey(Wechat_AesKey);
		return config;
	}
	
	/**
	 * 微信用户扩展信息http请求参数key值
	 */
	public static String Wechat_HttpParameterKey_UserExtendInfo="extend";
	
	/**
	 * 微信公众账号id
	 */
	public static String Wechat_AppID="wx1a2d9214cda67345";
	public static String Wechat_Secret="0f23a24879a1c7af3677d38faf1416cd";
	public static String Wechat_Token="VipTrip365888888888";
	public static String Wechat_AesKey="s3thxscHEIrnMf2efKAR5mzoTD7CyiLeRMy6ZeDOyyI";

	/**
	 * 微信绑定拦截传递的重定向参数key值
	 */
	public static String Wechat_RedirectParameterKey="redirectParamStr";

	/**
	 * 短信验证码有效分钟
	 */
	public static int SMS_CheckCodeValidMinutes=10;
	
	/**
	 * 短信内容
	 */
	public static String SMS_SendInfoModel="尊敬的贵宾，您的{0}验证码是{1}，" +
			"有效时间{2}分钟，请尽快完成后续的{3}操作。";
	
	
	
	/**
	 * 默认整数数值
	 */
	public static long Default_IntegerValue=-1;
	
	/**
	 * 夜间开始时间
	 * hx
	 */
	public static String NIGHTBEGIN="20:00";
	/**
	 * 夜间结束时间
	 * hx
	 */
	public static String NIGHTEND="8:00";
	/**
	 * 北京电话
	 * hx
	 */
	public static String beijingTel="4006020365";
	/**
	 * 上海电话
	 * hx
	 */
	public static String shanghaiTel="4006246365";
	/**
	 * 短信开关
	 * hx
	 */
	public static String TURNON="false";
	/**
	 * 
	 * hx
	 */
	public static String updateStatusTime="30";
	
	/**
	 * pnr
	 * hx
	 */
	public static String PNRDERAIL="false";

	/**
	 * hx
	 */
	public static final String JDTK = "-1";//企业预付款流动状态：酒店退款
	public static final String JPTK = "-2";//企业预付款流动状态：机票退款
	public static final String JJTK = "-3";//企业预付款流动状态：机加酒退款
	public static final String CWCZ = "0";//企业预付款流动状态：财务充值
	public static final String JDFK = "1";//企业预付款流动状态：酒店付款
	public static final String JPFK = "2";//企业预付款流动状态：机票付款
	public static final String JJFK = "3";//企业预付款流动状态：机加酒付款
	public static final String JDGQ = "4";//企业预付款流动状态：酒店变更
	public static final String JPGQ = "5";//企业预付款流动状态：机票改签
	public static final String JJGQ = "6";//企业预付款流动状态：机加酒改签
	public static final String CWTK = "7";//企业预付款流动状态：财务退款
	public static final String HCFK = "11";//企业预付款流动状态：火车票付款
	public static final String HCTK = "12";//企业预付款流动状态：火车票退款
	public static final Map<String,String> BALANCEMAP = new HashMap<String,String>();//预付款流动 键值对
	static{
		BALANCEMAP.put(JDTK, "酒店退款");
		BALANCEMAP.put(JPTK, "机票退款");
		BALANCEMAP.put(JJTK, "机加酒退款");		
		BALANCEMAP.put(CWCZ, "财务充值");	
		BALANCEMAP.put(JDFK, "酒店付款");	
		BALANCEMAP.put(JPFK, "机票付款");
		BALANCEMAP.put(JJFK, "机加酒付款");
		BALANCEMAP.put(JDGQ, "酒店变更");
		BALANCEMAP.put(JPGQ, "机票改签");
		BALANCEMAP.put(JJGQ, "机加酒改签");
		BALANCEMAP.put(CWTK, "财务退款");
		BALANCEMAP.put(HCFK, "火车票付款");
		BALANCEMAP.put(HCTK, "火车票退款");
	}
}
