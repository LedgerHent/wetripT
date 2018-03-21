package com.viptrip.register.model;

/**
 * 用户注册发送短信验证码所需【响应】model。
 * @author Administrator
 *
 */
public class Response_Result {
	private int code;
	private String result;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
