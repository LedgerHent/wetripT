package com.viptrip.wetrip.model.orderlist;

import java.io.Serializable;

public class RespData_GetOrderDetail_Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7860698017552292499L;
	//乘客顺序号
	public Integer passengerId;
	//航班号
	public String flightNo;
	//PNR
	public String pnr;
	//票号
	public String ticketNo;
	//票价
	public Double price;
	//优惠金额
	public Double rebatePrice;
	//机场建设费
	public Double airportTax;
	//机场燃油费
	public Double fuelSurTax;
	//服务费
	public Double serviceFee;
	
	//改期费
	public Double changeFee;
	//升舱费
	public Double classFee;
	//退票费
	public Double refundFee;
	//退票理由
	public String refundReason;
	
	
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	//票状态
	public String status;
	//儿童婴儿预定的车票
	public String childBabyBookCabin;
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRebatePrice() {
		return rebatePrice;
	}
	public void setRebatePrice(Double rebatePrice) {
		this.rebatePrice = rebatePrice;
	}
	public Double getAirportTax() {
		return airportTax;
	}
	public void setAirportTax(Double airportTax) {
		this.airportTax = airportTax;
	}
	public Double getFuelSurTax() {
		return fuelSurTax;
	}
	public void setFuelSurTax(Double fuelSurTax) {
		this.fuelSurTax = fuelSurTax;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChildBabyBookCabin() {
		return childBabyBookCabin;
	}
	public void setChildBabyBookCabin(String childBabyBookCabin) {
		this.childBabyBookCabin = childBabyBookCabin;
	}
	public Double getChangeFee() {
		return changeFee;
	}
	public void setChangeFee(Double changeFee) {
		this.changeFee = changeFee;
	}
	public Double getClassFee() {
		return classFee;
	}
	public void setClassFee(Double classFee) {
		this.classFee = classFee;
	}
	public Double getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	
	
	
}
