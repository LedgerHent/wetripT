package com.viptrip.pay.wx.api;

import com.viptrip.pay.wx.api.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kevinsawicki.http.HttpRequest;
import com.viptrip.pay.wx.util.PayCommonUtil;
import com.viptrip.pay.wx.util.WxConfig;
import com.viptrip.pay.wx.util.XMLParser;
import com.viptrip.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WXPayApi {
	private static Logger log = LoggerFactory.getLogger(WXPayApi.class);

	public  static String unifiedOrder(WXMwebReqBaseBean model,Map<String,String> headers){
		String result = null;
		HttpRequest post = HttpRequest.post(WxConfig.UNIFIEDORDER_URL);
		if(null!=headers && headers.size()>0){
			post = post.headers(headers);
		}
		try {
			result = post.contentType("text/xml").send(PayCommonUtil.getPayReqXML(model).getBytes(WxConfig.CHARSET.UTF8.val())).body();
		} catch (UnsupportedEncodingException e) {
			log.error("请求微信统一下单接口出错\r\n" + StringUtil.getExceptionStr(e));
		}
		return result;
	}

	public static WXMwebPayRtn unifiedOrderMWEB(WXMwebPayReq model,Map<String,String> headers){
		WXMwebPayRtn result = null;
		String rtnxml =unifiedOrder(model,headers);
		//将request转换成返回对象
		if(StringUtil.isNotEmpty(rtnxml)){
			result = XMLParser.convertToObjFromXML(rtnxml, WXMwebPayRtn.class,WxConfig.CHARSET.UTF8.val());
		}
		if(null==result){
			result = new WXMwebPayRtn("FAIL","调用接口出错");
		}
		return result;
	}

	public static WXJSAPIPayRtn unifiedOrderJSAPI(WXJSAPIPayReq model,Map<String,String> headers){
		WXJSAPIPayRtn result = null;
		String rtnxml = unifiedOrder(model,headers);
		//将request转换成返回对象
		if(StringUtil.isNotEmpty(rtnxml)){
			result = XMLParser.convertToObjFromXML(rtnxml, WXJSAPIPayRtn.class,WxConfig.CHARSET.UTF8.val());
		}
		if(null==result){
			result = new WXJSAPIPayRtn("FAIL","调用接口出错");
		}
		return result;
	}

	public static WXAPPPayRtn unifiedOrderApp(WXAPPPayReq model,Map<String,String> headers){
		WXAPPPayRtn result = null;
		String rtnxml = unifiedOrder(model,headers);
		//将request转换成返回对象
		if(StringUtil.isNotEmpty(rtnxml)){
			result = XMLParser.convertToObjFromXML(rtnxml, WXAPPPayRtn.class,WxConfig.CHARSET.UTF8.val());
		}
		if(null==result){
			result = new WXAPPPayRtn("FAIL","调用接口出错");
		}
		return result;
	}

	public static Map<String,String> buildAppRequestMap(WXAPPPayReq model,String prepayid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid",model.getAppid());
		map.put("partnerid",model.getMch_id());
		map.put("prepayid",prepayid);
		map.put("package","Sign=WXPay");
		map.put("noncestr", UUID.randomUUID().toString().substring(0,20));
		map.put("timestamp",new Date().getTime()/1000+"");
		String sign = PayCommonUtil.createSign("utf-8",PayCommonUtil.sortMap(map),WxConfig.KEY);
		map.put("sign", sign);
		return map;
	}
	
	public static WXMwebRefundRtn refund(WXMwebRefundReq model,Map<String,String> headers){
		WXMwebRefundRtn result = null;
		try {
			HttpRequest post = HttpRequest.post(WxConfig.REFUND_URL);
			if(null!=headers && headers.size()>0){
				post = post.headers(headers);
			}
			String rtnxml = post.contentType("text/xml").send(PayCommonUtil.getPayReqXML(model).getBytes(WxConfig.CHARSET.UTF8.val())).body();
			if(log.isDebugEnabled()){
				log.debug("WXPayApi.refund请求参数为：\r\n" + PayCommonUtil.getPayReqXML(model));
				log.debug("WXPayApi.refund返回结果为：\r\n" + rtnxml);
			}
			//将request转换成返回对象
			if(StringUtil.isNotEmpty(rtnxml)){
				result = XMLParser.convertToObjFromXML(rtnxml, WXMwebRefundRtn.class,WxConfig.CHARSET.UTF8.val());
			}
		} catch (Exception e) {
			result = new WXMwebRefundRtn("FAIL", "调用接口异常");
			log.error("请求微信统一下单接口出错\r\n" + StringUtil.getExceptionStr(e));
		}
		if(null==result){
			result = new WXMwebRefundRtn("FAIL","调用接口出错");
		}
		return result;
	}


	public static WXMwebCloseRtn close(WXMwebCloseReq model,Map<String,String> headers){
		WXMwebCloseRtn result = null;
		try {
			HttpRequest post = HttpRequest.post(WxConfig.CLOSEORDER_URL);
			if(null!=headers && headers.size()>0){
				post = post.headers(headers);
			}
			String rtnxml = post.contentType("text/xml").send(PayCommonUtil.getPayReqXML(model).getBytes(WxConfig.CHARSET.UTF8.val())).body();
			if(log.isDebugEnabled()){
				log.debug("WXPayApi.close请求参数为：\r\n" + PayCommonUtil.getPayReqXML(model));
				log.debug("WXPayApi.close返回结果为：\r\n" + rtnxml);
			}
			//将request转换成返回对象
			if(StringUtil.isNotEmpty(rtnxml)){
				result = XMLParser.convertToObjFromXML(rtnxml, WXMwebCloseRtn.class,WxConfig.CHARSET.UTF8.val());
			}
		} catch (Exception e) {
			result = new WXMwebCloseRtn("FAIL", "调用接口异常");
			log.error("请求微信统一下单接口出错\r\n" + StringUtil.getExceptionStr(e));
		}
		if(null==result){
			result = new WXMwebCloseRtn("FAIL","调用接口出错");
		}
		return result;
	}
	
	
}
