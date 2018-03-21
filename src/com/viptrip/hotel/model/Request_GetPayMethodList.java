package com.viptrip.hotel.model;

import com.viptrip.wetrip.model.base.Request_UserId;

public class Request_GetPayMethodList extends Request_UserId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4109780079628268266L;

	public String businessType;



	public String getBusinessType() {
		return businessType;
	}



	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
