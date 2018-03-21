package com.viptrip.wetrip.model;


import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket;

public class Request_BookAirTicket extends Request_UserId{

	private static final long serialVersionUID = -7995186977715208401L;
	
	public Integer source;
	public String token;
	public Integer orderType;
	public String tradePassword;
	public Integer businessType;
	public String remark;
	public ReqData_BookAirTicket data;
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getTradePassword() {
		return tradePassword;
	}
	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ReqData_BookAirTicket getData() {
		return data;
	}
	public void setData(ReqData_BookAirTicket data) {
		this.data = data;
	}
}
