package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_SmsCallBack extends Request_Base {
//orderNo=12334344444&userId=303163&upSmsContent=Y&mobile=13212333
	private String orderNo;
	private String upSmsContent;
	private String mobile;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUpSmsContent() {
		return upSmsContent;
	}
	public void setUpSmsContent(String upSmsContent) {
		this.upSmsContent = upSmsContent;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
}
