package com.viptrip.intlAirticket.model.flightModels;

public class Req_Data_IntlAuditOrder {

	private int orderId;//订单ID
	
	private int type;//1=审核通过，2=审核拒绝
	
	private String memo;//审核说明
	

	public Req_Data_IntlAuditOrder(int orderId, int type, String memo) {
		this.orderId = orderId;
		this.type = type;
		this.memo = memo;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
