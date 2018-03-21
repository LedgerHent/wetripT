package com.viptrip.pay.abc.vo;

import com.viptrip.pay.abc.api.resource.PayType;

public class PcRequest extends MPaymentRequest{

	public PcRequest() {
		super();
	}

	@SuppressWarnings("unchecked")
	public PcRequest(String orderNo,Double amount,String productName,String notifyURL){
		super(orderNo, amount, productName, notifyURL);
		this.dicRequest.put("PaymentLinkType", PayType.PC.code());//接入方式
	}
	
	
}
