package com.viptrip.wetrip.pay.weixin.service;


import org.springframework.stereotype.Service;

import com.viptrip.wetrip.pay.PayConfig;
import com.viptrip.wetrip.pay.weixin.api.WXMwebPayApi;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebPayReq;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebPayRtn;

@Service
public class WXPayService {

	
	/**
	 * MWEB支付
	 * @param orderNo
	 * @param price
	 * @param title
	 * @param body
	 */
	public WXMwebPayRtn mwebPay(String orderNo,Double price,String title,String body,String ip,String baseUrl){
		if("true".equals(PayConfig.isTest)){
			price = 0.01;
		}
		price = price * 100;
		WXMwebPayReq model = new WXMwebPayReq(orderNo, price.intValue(), ip, body, baseUrl);
		WXMwebPayRtn wxRtnMweb = WXMwebPayApi.unifiedOrder(model);
		return wxRtnMweb;
	}
	/**
	 * js支付
	 * @param orderNo
	 * @param price
	 * @param title
	 * @param body
	 */
	public WXMwebPayRtn jsPay(String orderNo,Double price,String title,String body,String ip,String baseUrl){
		if("true".equals(PayConfig.isTest)){
			price = 0.01;
		}
		price = price * 100;
		WXMwebPayReq model = new WXMwebPayReq(orderNo, price.intValue(), ip, body, baseUrl);
		WXMwebPayRtn wxRtnMweb = WXMwebPayApi.unifiedOrder(model);
		return wxRtnMweb;
	}

	
}
