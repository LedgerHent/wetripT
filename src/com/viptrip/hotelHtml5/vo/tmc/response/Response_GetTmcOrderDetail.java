package com.viptrip.hotelHtml5.vo.tmc.response;

import com.viptrip.hotelHtml5.vo.tmc.OrderDetailVo;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetTmcOrderDetail extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	private OrderDetailVo orderDetail;

	public OrderDetailVo getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetailVo orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
