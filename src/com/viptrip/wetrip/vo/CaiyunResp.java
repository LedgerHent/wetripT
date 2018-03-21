package com.viptrip.wetrip.vo;

import com.viptrip.wetrip.vo.base.CaiyunBaseData;

public class CaiyunResp<T extends CaiyunBaseData> {
	private Integer status;//0成功 其他失败
	private Boolean success;// 成功为true
	private String message; //信息
	private T data;
	
	public CaiyunResp() {
		this.status = -1;
		this.success = false;
	}

	public CaiyunResp(Integer status, String message, T data) {
		this(status, 0==status?true:false, message, data);
	}
	
	private CaiyunResp(Integer status, Boolean success, String message, T data) {
		this.status = status;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
