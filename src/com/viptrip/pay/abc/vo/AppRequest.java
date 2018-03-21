package com.viptrip.pay.abc.vo;

import com.viptrip.pay.abc.api.resource.PayType;

public class AppRequest extends MPaymentRequest{
	public AppRequest() {
		super();
	}

	@SuppressWarnings("unchecked")
	public AppRequest(String orderNo,Double amount,String productName,String notifyURL){
		super(orderNo, amount, productName, notifyURL);
		this.dicRequest.put("PaymentLinkType", PayType.APP.code());//接入方式
	}
	
	
}
