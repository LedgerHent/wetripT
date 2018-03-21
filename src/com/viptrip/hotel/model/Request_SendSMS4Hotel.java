package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.sendSMS4Hotel.SMS4HotelMessage;


public class Request_SendSMS4Hotel extends Request_Base{

	/**
	 * 2017-7-5 hanzhicheng
	 */
	private static final long serialVersionUID = -7359499823242538838L;
	
	public SMS4HotelMessage data;

	public SMS4HotelMessage getData() {
		return data;
	}

	public void setData(SMS4HotelMessage data) {
		this.data = data;
	}


	
	

}
