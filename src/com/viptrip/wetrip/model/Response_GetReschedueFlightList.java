package com.viptrip.wetrip.model;


import java.util.List;

import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;

import etuf.v1_0.model.v2.base.Response_Base;
/**
 * 航班信息查询返回结果
 * @author selfwhisper
 *
 */
public class Response_GetReschedueFlightList extends Response_Base{

	private static final long serialVersionUID = 1455907672554963884L;
	
	public List<RespData_GetReschedueFlightList> data;
	
	public Response_GetReschedueFlightList() {
		super();
	}
	
	public Response_GetReschedueFlightList(List<RespData_GetReschedueFlightList> data) {
		super();
		this.data = data;
	}

	public List<RespData_GetReschedueFlightList> getData() {
		return data;
	}

	public void setData(List<RespData_GetReschedueFlightList> data) {
		this.data = data;
	}

	

}
