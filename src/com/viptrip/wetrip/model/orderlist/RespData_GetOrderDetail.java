package com.viptrip.wetrip.model.orderlist;

import java.io.Serializable;
import java.util.List;

import com.viptrip.wetrip.model.employees.Auditor;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Contact;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;

public class RespData_GetOrderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2375310838810414875L;

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
	 * 支付方式描述
	 */
	public String payMethod;
	
	/**
	 * 订单状态
	 */
	public String status;
	
	//下单时间
	public String orderDateTime;
	
	//出行方式 1因公 2因私
	public Integer businessType;
	
	
	public Auditor auditor;
	

	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	/**
	 * 航程信息
	 */
	public List<ReqData_GetFlightList_TripInfo> tripInfo;
	
	/**
	 * 航班数据
	 */
	public List<RespData_GetOrderDetail_Flight> flights;
	
	//乘客信息
	public List<RespData_GetOrderDetail_Passenger> passengers;
	
	//联系人信息
	public List<ReqData_BookAirTicket_Contact> contacts;
	
	//机票信息
	public List<RespData_GetOrderDetail_Ticket> tickets;
	
	//改期信息
	public List<RespData_GetOrderDetail_Change> changes;
	
	//退票信息
	public List<RespData_GetOrderDetail_Refund> refunds;
	
	
	

	public List<RespData_GetOrderDetail_Refund> getRefunds() {
		return refunds;
	}

	public void setRefunds(List<RespData_GetOrderDetail_Refund> refunds) {
		this.refunds = refunds;
	}

	public List<RespData_GetOrderDetail_Change> getChanges() {
		return changes;
	}

	public void setChanges(List<RespData_GetOrderDetail_Change> changes) {
		this.changes = changes;
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

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public List<ReqData_GetFlightList_TripInfo> getTripInfo() {
		return tripInfo;
	}

	public void setTripInfo(List<ReqData_GetFlightList_TripInfo> tripInfo) {
		this.tripInfo = tripInfo;
	}

	public List<RespData_GetOrderDetail_Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<RespData_GetOrderDetail_Flight> flights) {
		this.flights = flights;
	}

	public List<RespData_GetOrderDetail_Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<RespData_GetOrderDetail_Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<ReqData_BookAirTicket_Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<ReqData_BookAirTicket_Contact> contacts) {
		this.contacts = contacts;
	}

	public List<RespData_GetOrderDetail_Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<RespData_GetOrderDetail_Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	
}
