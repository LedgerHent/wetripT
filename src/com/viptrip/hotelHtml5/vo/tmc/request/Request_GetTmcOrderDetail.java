package com.viptrip.hotelHtml5.vo.tmc.request;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_GetTmcOrderDetail extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
