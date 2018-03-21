package com.viptrip.hotelHtml5.vo.response;

import java.math.BigDecimal;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_CancelOrder extends Response_Base{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7221423238961694197L;
	public BigDecimal cancelFee;

	public BigDecimal getCancelFee() {
		return cancelFee;
	}

	public void setCancelFee(BigDecimal cancelFee) {
		this.cancelFee = cancelFee;
	}
	
}
