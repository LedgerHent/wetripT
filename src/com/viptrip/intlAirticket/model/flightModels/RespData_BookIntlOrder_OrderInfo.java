package com.viptrip.intlAirticket.model.flightModels;

public class RespData_BookIntlOrder_OrderInfo {

	private long orderId;			//订单号，查询详情用
	
	private String orderno;			//订单号，用于显示
	
	private double totalPrice;		//订单总价（含税总价）

	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
