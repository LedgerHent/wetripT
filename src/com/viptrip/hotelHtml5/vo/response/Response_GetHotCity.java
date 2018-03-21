package com.viptrip.hotelHtml5.vo.response;

import java.util.Map;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetHotCity extends Response_Base{
	private static final long serialVersionUID = -3793789965823793025L;
	private Map<String,Object> busiData;
	private String code;
	private String message;
	public Map<String, Object> getBusiData() {
		return busiData;
	}
	public void setBusiData(Map<String, Object> busiData) {
		this.busiData = busiData;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
