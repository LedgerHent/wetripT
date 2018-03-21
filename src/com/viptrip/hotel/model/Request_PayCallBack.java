package com.viptrip.hotel.model;

import com.viptrip.hotel.model.payCallBack.PayCallBack_Data;


public class Request_PayCallBack extends Request_Base_UserId{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8843066882756993790L;
	
	private PayCallBack_Data data;

	public PayCallBack_Data getData() {
		return data;
	}

	public void setData(PayCallBack_Data data) {
		this.data = data;
	}

	
}
