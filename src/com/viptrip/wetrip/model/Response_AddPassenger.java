package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Response_Base;

public class Response_AddPassenger extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8506531037115628342L;
 
	public long number;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	} 
	
}
