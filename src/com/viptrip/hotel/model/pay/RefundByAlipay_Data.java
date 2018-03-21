package com.viptrip.hotel.model.pay;


public class RefundByAlipay_Data {
	private Integer code;
	private String msg;
	private String orderNo;
	private Integer refundAmount;
	private String buyerId;
	private String refundTime;
	
	
	
	public RefundByAlipay_Data() {
		this.code = -1;
		this.msg = "失败";
	}
	
	
	public RefundByAlipay_Data(Integer code, String msg) {
		this(code, msg, null, null);
	}
	
	public RefundByAlipay_Data(Integer code, String msg, String orderNo,
			Integer refundAmount) {
		this(code, msg, orderNo, refundAmount, null, null);
	}

	

	
	public RefundByAlipay_Data(Integer code, String msg, String orderNo,
			Integer refundAmount, String buyerId, String refundTime) {
		this.code = code;
		this.msg = msg;
		this.orderNo = orderNo;
		this.refundAmount = refundAmount;
		this.buyerId = buyerId;
		this.refundTime = refundTime;
	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	
	
}
