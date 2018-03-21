package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

public class Request_RefundQueryByAlipay extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6463553298154301637L;
	
	private String orderNo;
	private String subNo;
	
	public Request_RefundQueryByAlipay() {
		super();
	}
	
	
	public Request_RefundQueryByAlipay(String orderNo, String subNo) {
		this.orderNo = orderNo;
		this.subNo = subNo;
	}


	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSubNo() {
		return subNo;
	}
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	
	
	

}
