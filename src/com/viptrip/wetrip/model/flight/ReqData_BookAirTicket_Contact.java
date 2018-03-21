package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class ReqData_BookAirTicket_Contact implements Serializable{
	private static final long serialVersionUID = 5244011078992245374L;
	@Description("通知人编号")
	public Integer id;
	@Description("通知人姓名")
	public String name;
	@Description("通知人电话")
	public String mobile;
	@Description("通知人邮箱")
	public String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
