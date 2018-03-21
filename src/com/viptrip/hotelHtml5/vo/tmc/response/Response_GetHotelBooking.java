package com.viptrip.hotelHtml5.vo.tmc.response;

import com.viptrip.hotelHtml5.vo.tmc.HotelBookingVo;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetHotelBooking extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	private HotelBookingVo hotelBooking;

	public HotelBookingVo getHotelBooking() {
		return hotelBooking;
	}

	public void setHotelBooking(HotelBookingVo hotelBooking) {
		this.hotelBooking = hotelBooking;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
