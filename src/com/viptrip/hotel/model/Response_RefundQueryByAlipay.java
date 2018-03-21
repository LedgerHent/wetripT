package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Response_Base;
import com.viptrip.hotel.model.pay.RefundQueryByAlipay_Data;

public class Response_RefundQueryByAlipay extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6588883374752089299L;
	
	private RefundQueryByAlipay_Data data;
	
	

	public Response_RefundQueryByAlipay() {
		super();
	}
	

	public Response_RefundQueryByAlipay(RefundQueryByAlipay_Data data) {
		this.data = data;
	}


	public RefundQueryByAlipay_Data getData() {
		return data;
	}

	public void setData(RefundQueryByAlipay_Data data) {
		this.data = data;
	}
	
	

}
