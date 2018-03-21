package com.viptrip.wetrip.model;


import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderList;

public class Response_GetOrderList extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3709655277899730855L;

	//总记录数
	public Integer totalCount;
	//列表数据
	public List<RespData_GetOrderList> list;
	
	
	public Integer getStatus(){
		return status;
	}
	
	public Response_GetOrderList(Integer totalCount,
			List<RespData_GetOrderList> list) {
		this.totalCount = totalCount;
		this.list = list;
	}
	
	
	public Response_GetOrderList() {
	}


	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<RespData_GetOrderList> getList() {
		return list;
	}
	public void setList(List<RespData_GetOrderList> list) {
		this.list = list;
	}
	
}
