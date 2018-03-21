package com.viptrip.hotel.model;


import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.passenger.Req_Passenger;

public class Request_AddPassenger extends Request_UserId{

	/**
	 * 2017-7-4 hanzhicheng
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Req_Passenger data;


	public Req_Passenger getData() {
		return data;
	}


	public void setData(Req_Passenger data) {
		this.data = data;
	}

	
	
	
}
