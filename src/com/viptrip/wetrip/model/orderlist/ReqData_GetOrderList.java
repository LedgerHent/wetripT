package com.viptrip.wetrip.model.orderlist;

import java.io.Serializable;

public class ReqData_GetOrderList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1845450461039808133L;

	/**
	 * 开始位置
	 */
	private int start;
	
	/**
	 * 请求个数
	 */
	private int count;
	
	/**
	 * 状态枚举
	 	<100自己预定的订单，100-200作为管理员，审核员所能看到的订单
		0=全部订单，
		1=待审核订单（含审核过程中），
		2=审核通过的订单，
		3=审核驳回的订单，
		4=待支付订单，
		5=已完成订单（已审核，已支付，已取消），
		100=全部订单，
		101=待审核的订单（待我审核），
		102=审核通过的订单（只是我审核通过，审核流程未必完结），
		103=审核驳回的订单，
		105=已完成订单（已审核，已支付，已取消）
	 */
	private int status;

	
	
	
	
	public ReqData_GetOrderList(int start, int count, int status) {
		this.start = start;
		this.count = count;
		this.status = status;
	}

	public ReqData_GetOrderList() {
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
