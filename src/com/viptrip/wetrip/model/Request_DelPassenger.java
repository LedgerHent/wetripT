package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Request_UserId;

public class Request_DelPassenger extends Request_UserId {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1971980477624415495L;
	
	public Integer passengerId;

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

}
