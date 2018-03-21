package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.base.Response_PersonMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;

public class Request_AddPassenger extends Request_UserId{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3580375363605376013L;
	
	public Req_Passenger addP;

	public Req_Passenger getAddP() {
		return addP;
	}

	public void setAddP(Req_Passenger addP) {
		this.addP = addP;
	}

	

	
	
}
