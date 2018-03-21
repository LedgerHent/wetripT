package com.viptrip.pay.wx.service;

import com.viptrip.pay.wx.api.WXPayApi;
import com.viptrip.pay.wx.api.bean.*;
import com.viptrip.util.JSON;
import org.springframework.stereotype.Service;

import com.viptrip.pay.PayConfig;
import com.viptrip.pay.wx.util.PayCommonUtil;
import com.viptrip.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Service("wx_unipay")
public class WXPayService {

	/**
	 * H5支付
	 * @param orderId
	 * @param price 支付金额 单位元
	 * @param title 标题
	 * @param body 主体
	 * @param ip ip地址
	 * @param returnURL 
	 * @param notifyURL
	 * @return
	 */
	public String mwebPay(String orderId,Double price,String title,String body,String ip,String returnURL,String notifyURL,Map<String,String> headers){
		String result = null;
		price = price * 100;
		WXMwebPayReq model = new WXMwebPayReq(orderId, price.intValue(), ip, body, notifyURL);
		
		WXMwebPayRtn wxRtnMweb = WXPayApi.unifiedOrderMWEB(model,headers);
		if(null!=wxRtnMweb){
			if("SUCCESS".equals(wxRtnMweb.getReturn_code())&&"SUCCESS".equals(wxRtnMweb.getResult_code())){
				result = PayCommonUtil.buildEncodeRedirectURL(wxRtnMweb.getMweb_url(), returnURL + (returnURL.indexOf("?")>=0?"&":"?") + "out_trade_no=" + orderId + "&total_amount=" + price.intValue());
			}else{
				if(StringUtil.isNotEmpty(wxRtnMweb.getMweb_url())){
					result = PayCommonUtil.buildEncodeRedirectURL(wxRtnMweb.getMweb_url(), returnURL+ (returnURL.indexOf("?")>=0?"&":"?") + "?out_trade_no=" + orderId + "&total_amount=" + price.intValue());
				}else{
					result = wxRtnMweb.getErr_code_des();
				}
			}
		}
		return result;
	}

	public String JSAPIPay(String orderId,Double price,String body,String ip,String notifyURL,String openId,Map<String,String> headers){
		String result = null;

		price = price * 100;
		WXJSAPIPayReq model = new WXJSAPIPayReq(orderId, price.intValue(), ip, body, notifyURL,openId);

		WXJSAPIPayRtn wxjsapiPayRtn = WXPayApi.unifiedOrderJSAPI(model,headers);
		/*if(null!=wxjsapiPayRtn){
			if("SUCCESS".equals(wxjsapiPayRtn.getReturn_code())&&"SUCCESS".equals(wxjsapiPayRtn.getResult_code())){
				result = wxjsapiPayRtn.getPrepay_id();
			}
		}*/
		return JSON.get().toJson(wxjsapiPayRtn);
	}

	/**
	 * app支付
	 * @return
	 */
	public Map<String, String> appPay(String orderId,Double price,String body,String ip,String notifyURL,Map<String,String> headers){
		Map<String, String> result = new HashMap<>();

		price = price * 100;
		WXAPPPayReq model = new WXAPPPayReq(orderId, price.intValue(), ip, body, notifyURL);
		WXAPPPayRtn wxappPayRtn = WXPayApi.unifiedOrderApp(model, headers);
		if(null!=wxappPayRtn){
			if("SUCCESS".equals(wxappPayRtn.getReturn_code())&&"SUCCESS".equals(wxappPayRtn.getResult_code())){
				String prepay_id = wxappPayRtn.getPrepay_id();
				if(StringUtil.isNotEmpty(prepay_id)){
					result = WXPayApi.buildAppRequestMap(model, prepay_id);
					result.put("code","0");
					result.put("msg","成功");
				}else {
					result.put("code","2");
					result.put("msg","prepay_id为空");
				}
			}else{
				result.put("code","1");
				result.put("msg",wxappPayRtn.getReturn_msg());
			}
		}
		return result;
	}


	public WXMwebRefundRtn refund(String orderId,String refundNo,Double totalAmount,Double refundAmount,String refundReason,Map<String,String> headers){

		refundAmount = refundAmount * 100;
		totalAmount = totalAmount * 100;
		WXMwebRefundReq model = new WXMwebRefundReq(orderId,refundNo,totalAmount.intValue(),refundAmount.intValue(),refundReason);
		WXMwebRefundRtn refund = WXPayApi.refund(model,headers);
		System.out.println("=============" + JSON.get().toJson(refund));
		return refund;
	}

	public void close(String orderId,Map<String,String> headers){
		WXMwebCloseReq model =  new WXMwebCloseReq(orderId);
		WXMwebCloseRtn close = WXPayApi.close(model,headers);
		System.out.println("=============" + JSON.get().toJson(close));
	}
	
}
