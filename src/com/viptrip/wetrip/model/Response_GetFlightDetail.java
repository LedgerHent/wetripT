package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail;

import etuf.v1_0.model.v2.base.Response_Base;
/**
 * 航班详情查询返回结果
 * @author selfwhisper
 *
 */
public class Response_GetFlightDetail extends Response_Base{
	private static final long serialVersionUID = 933188564231848919L;
	
	public RespData_GetFlightDetail data;

	
	public Response_GetFlightDetail(RespData_GetFlightDetail data) {
		super();
		this.data = data;
	}
	public Response_GetFlightDetail() {
		super();
	}
	public RespData_GetFlightDetail getData() {
		return data;
	}
	public void setData(RespData_GetFlightDetail data) {
		this.data = data;
	}
	
}
