package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Response_Base;
import com.viptrip.hotel.model.pay.RefundByAlipay_Data;

public class Response_RefundByAlipay extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3510166831094795108L;

	private RefundByAlipay_Data data;

	
	
	public Response_RefundByAlipay(RefundByAlipay_Data data) {
		this.data = data;
	}

	public Response_RefundByAlipay() {
		super();
	}

	public RefundByAlipay_Data getData() {
		return data;
	}

	public void setData(RefundByAlipay_Data data) {
		this.data = data;
	}
	
}
