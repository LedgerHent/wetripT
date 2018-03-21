package com.viptrip.wetrip.pay.weixin.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kevinsawicki.http.HttpRequest;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.pay.WxConfig;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebPayReq;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebRefundReq;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebPayRtn;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebRefundRtn;
import com.viptrip.wetrip.pay.weixin.api.util.PayCommonUtil;
import com.viptrip.wetrip.pay.weixin.api.util.XMLParser;

public class WXMwebPayApi {
	private static Logger log = LoggerFactory.getLogger(WXMwebPayApi.class);
	
	public static WXMwebPayRtn unifiedOrder(WXMwebPayReq model){
		WXMwebPayRtn result = null;
		try {
			//String rtnxml = HttpRequest.post(WxConfig.UNIFIEDORDER_URL, true, model.getReqMap(WxConfig.SIGNTYPE.MD5.val())).body();
//			String rtnxml = HttpRequest.post(WxConfig.UNIFIEDORDER_URL, true, model.getReqXML(WxConfig.SIGNTYPE.MD5.val())).body();
			HttpRequest post = HttpRequest.post(WxConfig.UNIFIEDORDER_URL);
			String rtnxml = post.header("referer","viptrip365.com").contentType("text/xml").send(PayCommonUtil.getPayReqXML(model).getBytes(WxConfig.CHARSET.UTF8.val())).body();
			if(log.isDebugEnabled()){
				log.debug("WXPayApi.unifiedOrder请求参数为：\r\n" + PayCommonUtil.getPayReqXML(model));
				log.debug("WXPayApi.unifiedOrder返回结果为：\r\n" + rtnxml);
			}
			//将request转换成返回对象
			if(StringUtil.isNotEmpty(rtnxml)){
				result = XMLParser.convertToObjFromXML(rtnxml, WXMwebPayRtn.class,WxConfig.CHARSET.UTF8.val());
			}
			
			//System.out.println("WXPayApi.unifiedOrderMWEB()==rtnxml==" + rtnxml);
		} catch (Exception e) {
			result = new WXMwebPayRtn("FAIL", "调用接口异常");
			log.error("请求微信统一下单接口出错\r\n" + StringUtil.getExceptionStr(e));
		}
		if(null==result){
			result = new WXMwebPayRtn("FAIL","调用接口出错");
		}
		return result;
	}
	
	public static WXMwebRefundRtn refund(WXMwebRefundReq model){
		WXMwebRefundRtn result = null;
		try {
			HttpRequest post = HttpRequest.post(WxConfig.UNIFIEDORDER_URL);
			String rtnxml = post.contentType("text/xml").send(PayCommonUtil.getPayReqXML(model).getBytes(WxConfig.CHARSET.UTF8.val())).body();
			if(log.isDebugEnabled()){
				log.debug("WXPayApi.unifiedOrder请求参数为：\r\n" + PayCommonUtil.getPayReqXML(model));
				log.debug("WXPayApi.unifiedOrder返回结果为：\r\n" + rtnxml);
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
	
	
	
}
