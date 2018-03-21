package com.viptrip.hotel.model.payCallBack;

import java.util.Map;

public class PayCallBack_Data {
	
	private Integer type;
	private String orderNo;
	private Integer amount;
	private String returnUrl;
	private String notifyUrl;
	private String subject;
	private String body;
	private String nonceStr;
	private Integer payType;
	private Integer payStatus;
	private String payMemo;
	
	
	
	public PayCallBack_Data() {
		super();
	}
	
	public PayCallBack_Data(Map<String,Object> map) {
		this.type = (Integer) map.get("type");
		this.orderNo = (String) map.get("orderNo");
		this.amount = (Integer) map.get("amount");
		this.returnUrl = (String) map.get("returnUrl");
		this.notifyUrl = (String) map.get("notifyUrl");
		this.subject = (String) map.get("subject");
		this.body = (String) map.get("body");
		this.nonceStr = (String) map.get("nonceStr");
		this.payType = (Integer) map.get("payType");
		this.payStatus = (Integer) map.get("payStatus");
		this.payMemo = (String) map.get("payMemo");
	}
	

	public PayCallBack_Data(Integer type, String orderNo, Integer amount,
			String returnUrl, String notifyUrl, String subject, String body,
			String nonceStr, Integer payType, Integer payStatus, String payMemo) {
		this.type = type;
		this.orderNo = orderNo;
		this.amount = amount;
		this.returnUrl = returnUrl;
		this.notifyUrl = notifyUrl;
		this.subject = subject;
		this.body = body;
		this.nonceStr = nonceStr;
		this.payType = payType;
		this.payStatus = payStatus;
		this.payMemo = payMemo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayMemo() {
		return payMemo;
	}

	public void setPayMemo(String payMemo) {
		this.payMemo = payMemo;
	}

}
