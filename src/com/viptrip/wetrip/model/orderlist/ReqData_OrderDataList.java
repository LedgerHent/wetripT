package com.viptrip.wetrip.model.orderlist;

import java.util.List;

import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;

public class ReqData_OrderDataList {

	/**
	 * 订单id
	 */
	public int orderId;
	
	/**
	 * 订单号
	 */
	public String orderNo;
	
	/**
	 * 订单总金额
	 */
	public double amount;
	
	/**
	 * 航程类型
	 */
	public int tripType;
	
	/**
	 * 订单状态
	 */
	public String status;
	
	/**
	 * 剩余支付时间
	 */
	public String surplusPayTime;
	
	/**
	 * 航班信息
	 */
	public List<ReqData_GetFlightList_TripInfo> tripInfo;
	
	/**
	 * 航班序列
	 */
	public List<RespData_GetOrderList_Flight> flights;

	/**
	 * 下单时间
	 */
	public String orderDateTime;
	
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTripType() {
		return tripType;
	}

	public void setTripType(int tripType) {
		this.tripType = tripType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSurplusPayTime() {
		return surplusPayTime;
	}

	public void setSurplusPayTime(String surplusPayTime) {
		this.surplusPayTime = surplusPayTime;
	}

	public List<ReqData_GetFlightList_TripInfo> getTripInfo() {
		return tripInfo;
	}

	public void setTripInfo(List<ReqData_GetFlightList_TripInfo> tripInfo) {
		this.tripInfo = tripInfo;
	}

	public List<RespData_GetOrderList_Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<RespData_GetOrderList_Flight> flights) {
		this.flights = flights;
	}
	
	
}
