package com.viptrip.pay.abc.vo;

import com.abc.pay.client.ebus.RefundRequest;

public class RefundReq extends RefundRequest{

	private RefundReq() {
		super();
		this.dicRequest.put("CurrencyCode","156");
	}

	public RefundReq(String orderDate, String orderTime, String orderNo,
			String newOrderNo, Double trxAmount) {
		this();
		this.dicRequest.put("OrderDate",orderDate);
		this.dicRequest.put("OrderTime",orderTime);
		this.dicRequest.put("OrderNo",orderNo);
		this.dicRequest.put("NewOrderNo",newOrderNo);
		this.dicRequest.put("TrxAmount", trxAmount==null?"":trxAmount.toString());
	}

}
