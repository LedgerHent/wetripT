package com.viptrip.hotel.model;

public class Request_RefundByAlipay extends Request_Base_UserId{

	/**
	 * 
	 */
	private static final long serialVersionUID = 699339704934142879L;

	private String orderNo;
	private Integer refundAmount;
	private String reason;
	private String subNo;
	
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSubNo() {
		return subNo;
	}
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	
	
	
	
}
