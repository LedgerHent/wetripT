package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.orderlist.ReqData_GetOrderList;

public class Request_GetOrderList extends Request_UserId{
	
	private static final long serialVersionUID = -20166644481509415L;
	/**
	 * 请求参数
	 */
	public ReqData_GetOrderList data;

	public ReqData_GetOrderList getData() {
		return data;
	}

	public void setData(ReqData_GetOrderList data) {
		this.data = data;
	}
	
	
	
	
}
