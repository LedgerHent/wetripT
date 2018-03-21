package com.viptrip.base.common;

/**
 * 单点登录URL地址及配置信息
 * @author Administrator
 * 
 */
public class Config {
	/**
	 * 单点登录URL地址
	 */
	public static String ssoUrl = "http://61.51.80.138:60002/cas/login";
	/**
	 * service 回调地址
	 * user 登录名，姓名，手机号等
	 * pass 登录密码
	 * submitDirection 是否免登
	 */
	public static String ssoGetParamsFormat = "service={0}&serialize={1}";
	
	
	/**
	 * sso登出
	 * service 回调地址
	 */
	public static String ssoLogoutGetParamsFormat = "service={0}";
	
	/**
	 * 用户默认登录密码
	 */
	public static String defaultPassword = "123456";
	
	/**
	 * 客服热线
	 */
	public static String customerHotService="4006-020-365";
	
	/**
	 * 微信登录action相对地址
	 */
	public static String wechatLoginActionUrl="/weAvoidLogin/login.act";
	
	/**
	 * 国内机票首页地址
	 */
	public static String ticketIndex="/flight/search.act";
	
	/**
	 * 国际机票首页地址
	 */
	public static String inlticketIndex="/intlflight/search.act";
	
	/**
	 * 酒店首页地址
	 */
	public static String hotelIndex="/hotel/page.act";
	/**
	 * 国内机票订单列表地址
	 */
	public static String ticketOrderList="/order/list.act";
	
	/**
	 * 酒店订单列表地址
	 */
	public static String hotelOrderList="/orderInfo/listAllOrderInit.act";
}

