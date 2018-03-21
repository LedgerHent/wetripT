package com.viptrip.hotel.model.pay;


public class RefundQueryByAlipay_Data {
	private Integer code;
	private String msg;
	private String orderNo;
	private String subNo;
	private Integer amount;
	private Integer refundAmount;
	private String refundReason;
	
	
	
	public RefundQueryByAlipay_Data() {
		this.code = -1;
		this.msg = "失败";
	}
	
	
	public RefundQueryByAlipay_Data(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public RefundQueryByAlipay_Data(Integer code, String msg, String orderNo,
			String subNo, Integer amount, Integer refundAmount,
			String refundReason) {
		this.code = code;
		this.msg = msg;
		this.orderNo = orderNo;
		this.subNo = subNo;
		this.amount = amount;
		this.refundAmount = refundAmount;
		this.refundReason = refundReason;
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
	public String getSubNo() {
		return subNo;
	}
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	
	
}
