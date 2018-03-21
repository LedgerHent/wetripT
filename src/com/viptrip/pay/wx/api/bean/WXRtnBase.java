package com.viptrip.pay.wx.api.bean;

public class WXRtnBase {
	protected String return_code;
	protected String return_msg;
	
	public WXRtnBase() {
	}
	
	public WXRtnBase(String return_code, String return_msg) {
		this.return_code = return_code;
		this.return_msg = return_msg;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}
