package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
/**
 * 航班信息查询请求参数
 * @author selfwhisper
 *
 */
public class Request_GetFlightList extends Request_UserId{

	private static final long serialVersionUID = 82998720481481352L;
	
	public ReqData_GetFlightList data;
	
	

	public Request_GetFlightList(ReqData_GetFlightList data) {
		this.data = data;
	}
	

	public Request_GetFlightList() {
	}

	public ReqData_GetFlightList getData() {
		return data;
	}

	public void setData(ReqData_GetFlightList data) {
		this.data = data;
	}
	
}
