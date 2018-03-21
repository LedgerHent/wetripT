package com.viptrip.intlAirticket.model;


public class Response_Model<T> {
	
	 private String code;
	 private String msg;
	private T data;
	
	 public Response_Model() {
		this.code="0";	
		this.msg="数据获取成功！";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T result) {
		this.data = result;
	}
}
