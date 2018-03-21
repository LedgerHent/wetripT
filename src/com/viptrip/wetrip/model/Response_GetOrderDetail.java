package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail;

public class Response_GetOrderDetail extends Response_Base{
	private static final long serialVersionUID = 318132268572795681L;
	
	public RespData_GetOrderDetail data;

	
	
	public Response_GetOrderDetail() {
	}
	
	public Response_GetOrderDetail(RespData_GetOrderDetail data) {
		this.data = data;
	}

	public Integer getStatus(){
		return status;
	}

	public RespData_GetOrderDetail getData() {
		return data;
	}

	public void setData(RespData_GetOrderDetail data) {
		this.data = data;
	}
	
	
}
