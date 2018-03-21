package com.viptrip.wetrip.model.orderlist;

import java.io.Serializable;
import java.util.List;

import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;

public class RespData_GetOrderList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4258128381853552344L;

	/**
	 * 订单id
	 */
	public Long orderId;
	
	/**
	 * 订单号
	 */
	public String orderNo;
	
	/**
	 * 订单总金额
	 */
	public Double amount;
	
	/**
	 * 航程类型
	 */
	public Integer tripType;
	
	/**
	 * 订单状态
	 */
	public String status;
	
	/**
	 * 剩余支付时间
	 */
	public String surplusPayTime;
	
	/**
	 * 航程信息
	 */
	public List<ReqData_GetFlightList_TripInfo> tripInfo;
	
	/**
	 * 航班序列
	 */
	public List<RespData_GetOrderList_Flight> flights;
	
	
	

	public RespData_GetOrderList(Long orderId, String orderNo, double amount,
			int tripType, String status, String surplusPayTime,
			List<ReqData_GetFlightList_TripInfo> tripInfo,
			List<RespData_GetOrderList_Flight> flights) {
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.amount = amount;
		this.tripType = tripType;
		this.status = status;
		this.surplusPayTime = surplusPayTime;
		this.tripInfo = tripInfo;
		this.flights = flights;
	}
	
	

	public RespData_GetOrderList() {
	}



	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTripType() {
		return tripType;
	}

	public void setTripType(Integer tripType) {
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
