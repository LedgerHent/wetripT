package com.viptrip.common.model;

import com.viptrip.wetrip.model.base.Request_Base;

public class Request_OrderBase extends Request_Base {
	public Integer businessType;
	public String orderno;
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	
}
