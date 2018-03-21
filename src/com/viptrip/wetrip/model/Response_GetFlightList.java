package com.viptrip.wetrip.model;


import java.util.List;

import com.viptrip.wetrip.model.flight.RespData_GetFlightList;

import etuf.v1_0.model.v2.base.Response_Base;
/**
 * 航班信息查询返回结果
 * @author selfwhisper
 *
 */
public class Response_GetFlightList extends Response_Base{

	private static final long serialVersionUID = 1455907672554963884L;
	
	public List<RespData_GetFlightList> data;
	
	

	public Response_GetFlightList() {
		super();
	}

	public Response_GetFlightList(List<RespData_GetFlightList> data) {
		super();
		this.data = data;
	}

	public List<RespData_GetFlightList> getData() {
		return data;
	}

	public void setData(List<RespData_GetFlightList> data) {
		this.data = data;
	}

	

}
