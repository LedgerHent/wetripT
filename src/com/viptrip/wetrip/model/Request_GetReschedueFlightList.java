package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetReschedueFlightList;
/**
 * 航班信息查询请求参数
 * @author selfwhisper
 *
 */
public class Request_GetReschedueFlightList extends Request_UserId{

	private static final long serialVersionUID = 82998720481481352L;
	
	public ReqData_GetReschedueFlightList data;
	
	public Request_GetReschedueFlightList(ReqData_GetReschedueFlightList data) {
		this.data = data;
	}

	public Request_GetReschedueFlightList() {
	}

	public ReqData_GetReschedueFlightList getData() {
		return data;
	}

	public void setData(ReqData_GetReschedueFlightList data) {
		this.data = data;
	}
	
}
