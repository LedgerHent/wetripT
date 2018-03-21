package com.viptrip.hotelHtml5.vo.request;

import com.viptrip.hotelHtml5.vo.tmc.OrderDetailRequestVo;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_SaveTmcOrder extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderDetailRequestVo orderDetailRequestVo;

	public OrderDetailRequestVo getOrderDetailRequestVo() {
		return orderDetailRequestVo;
	}

	public void setOrderDetailRequestVo(OrderDetailRequestVo orderDetailRequestVo) {
		this.orderDetailRequestVo = orderDetailRequestVo;
	}
	
}
