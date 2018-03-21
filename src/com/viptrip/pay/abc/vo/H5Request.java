package com.viptrip.pay.abc.vo;


import com.viptrip.pay.abc.api.resource.PayType;

public class H5Request extends MPaymentRequest{
	
	public H5Request() {
		super();
	}

	@SuppressWarnings("unchecked")
	public H5Request(String orderNo,Double amount,String productName,String notifyURL){
		super(orderNo, amount, productName, notifyURL);
		this.dicRequest.put("PaymentLinkType", PayType.MOBILE.code());//接入方式
	}
	
	
}
