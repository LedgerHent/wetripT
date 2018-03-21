package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;

public class Response_ToPay extends Response_Base{

	private static final long serialVersionUID = -2947826760845851891L;

	public RespData_BookAirTicket data;
	
	public Response_ToPay() {
	}
	public Response_ToPay(RespData_BookAirTicket data) {
		this.data = data;
	}
	public RespData_BookAirTicket getData() {
		return data;
	}
	public void setData(RespData_BookAirTicket data) {
		this.data = data;
	}
	
	
}
