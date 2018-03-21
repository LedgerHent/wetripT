package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.passenger.Req_Passenger;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_ListPassenger extends Response_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6469011088067568995L;
	public List<Req_Passenger> data;
	public List<Req_Passenger> getData() {
		return data;
	}
	public void setData(List<Req_Passenger> data) {
		this.data = data;
	}

}
