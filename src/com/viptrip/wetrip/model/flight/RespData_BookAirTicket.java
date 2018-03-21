package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class RespData_BookAirTicket implements Serializable{
	private static final long serialVersionUID = 2598920783239336345L;
	@Description("订单id")
	public Integer orderId;
	@Description("订单编号")
	public String orderNo;
	@Description("订单总金额")
	public Double amount;
	@Description("服务金额")
	public Double serviceFee;
	@Description("订单状态")
	public String orderStatus;
	@Description("讯盟返回错误信息")
	public String errorStr;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
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
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getErrorStr() {
		return errorStr;
	}
	public void setErrorStr(String errorStr) {
		this.errorStr = errorStr;
	}
	
	
}
