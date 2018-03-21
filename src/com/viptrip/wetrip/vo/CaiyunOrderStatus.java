package com.viptrip.wetrip.vo;

public enum CaiyunOrderStatus {
	
	PAY_ING(1,"PAY_ING","订单支付中"),PAY_SUCCESS(2,"PAY_SUCCESS","订单支付成功"),REFUND_SUCCESS(3,"REFUND_SUCCESS","退款成功"),TRADE_CLOSE(4,"TRADE_CLOSE","交易关闭");
	
	private Integer status;
	private String val;
	private String msg;
	
	
	private CaiyunOrderStatus() {
	}
	
	private CaiyunOrderStatus(Integer status,String val,String msg) {
		this.status = status;
		this.val = val;
		this.msg = msg;
	}
	
	public Integer status(){
		return this.status;
	}
	public String msg(){
		return this.msg;
	}
	public String val(){
		return this.val;
	}
}
