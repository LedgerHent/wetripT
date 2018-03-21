package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.List;

import com.viptrip.base.annotation.Description;

public class ReqData_BookAirTicket implements Serializable{
	private static final long serialVersionUID = 1274323139310363571L;
	@Description("航程类型")
	public Integer tripType;
	@Description("时间戳")
	public Long timestamp;
	@Description("航程信息")
	public List<ReqData_BookAirTicket_Trip> trips;
	@Description("支付方式")
	public Integer payMethod;
	@Description("超标理由")
	public List<ReqData_BookAirTicket_OverReason> overReason;
	@Description("乘客信息")
	public List<ReqData_BookAirTicket_Passenger> passengers;
	public List<ReqData_BookAirTicket_Contact> contacts;
	public Integer auditorId;
	public Integer approvalId;//审批id
	
	public Integer getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Integer approvalId) {
		this.approvalId = approvalId;
	}
	public Integer getTripType() {
		return tripType;
	}
	public void setTripType(Integer tripType) {
		this.tripType = tripType;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public List<ReqData_BookAirTicket_Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<ReqData_BookAirTicket_Trip> trips) {
		this.trips = trips;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public List<ReqData_BookAirTicket_OverReason> getOverReason() {
		return overReason;
	}
	public void setOverReason(List<ReqData_BookAirTicket_OverReason> overReason) {
		this.overReason = overReason;
	}
	public List<ReqData_BookAirTicket_Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<ReqData_BookAirTicket_Passenger> passengers) {
		this.passengers = passengers;
	}
	public List<ReqData_BookAirTicket_Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<ReqData_BookAirTicket_Contact> contacts) {
		this.contacts = contacts;
	}
	public Integer getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}
}
