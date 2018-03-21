package com.viptrip.hotel.model.pay;

public class PayData {
	private String orderNo;
	private Integer amount;
	private String returnUrl;
	private String notifyUrl;
	private String subject;
	private String body;
	private String nonceStr;
	
	public PayData() {
	}
	
	public PayData(String orderNo, Integer amount, String returnUrl,
			String notifyUrl) {
		this(orderNo,amount,returnUrl,notifyUrl,null);
	}
	
	public PayData(String orderNo, Integer amount, String returnUrl,
			String notifyUrl,String subject) {
		this(orderNo,amount,returnUrl,notifyUrl,null,null,null);
	}
	
	public PayData(String orderNo, Integer amount, String returnUrl,
			String notifyUrl, String subject, String body, String nonceStr) {
		this.orderNo = orderNo;
		this.amount = amount;
		this.returnUrl = returnUrl;
		this.notifyUrl = notifyUrl;
		this.subject = subject;
		this.body = body;
		this.nonceStr = nonceStr;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
}
