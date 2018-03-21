package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Response_Base;

public class Response_PayByAlipay extends Response_Base{
	
	private static final long serialVersionUID = 8835957930204256084L;
	
	private String param;
	
	

	public Response_PayByAlipay() {
	}

	public Response_PayByAlipay(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	
}
