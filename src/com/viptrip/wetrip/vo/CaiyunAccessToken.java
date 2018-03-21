package com.viptrip.wetrip.vo;

import com.viptrip.wetrip.vo.base.CaiyunBaseData;

public class CaiyunAccessToken implements CaiyunBaseData{
	
	private static final long serialVersionUID = -9176338284588018419L;
	
	private Integer code;
	private String msg;
	private String accessToken;
	private Integer expiresIn;
	
	

	public CaiyunAccessToken() {
		
	}
	
	public CaiyunAccessToken(String accessToken, Integer expiresIn) {
		this(1,"失败",accessToken,expiresIn);
	}
	
	public CaiyunAccessToken(Integer code,String msg,String accessToken, Integer expiresIn) {
		this.code = code;
		this.msg = msg;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
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
	
	
}
