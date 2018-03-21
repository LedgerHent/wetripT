package com.viptrip.wetrip.model.orderlist;

import java.util.List;

public class ReqData_OrderData {

	
	
	/**
	 * 记录总数
	 * */
	public int totalCount;
	
	/**
	 * 列表
	 * */
	public List<ReqData_OrderDataList> list;

	
	
	
	public ReqData_OrderData(int totalCount, List<ReqData_OrderDataList> list) {
		this.totalCount = totalCount;
		this.list = list;
	}

	public ReqData_OrderData() {
		super();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ReqData_OrderDataList> getList() {
		return list;
	}

	public void setList(List<ReqData_OrderDataList> list) {
		this.list = list;
	}
	
	
}
