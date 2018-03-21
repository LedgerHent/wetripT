package com.viptrip.hotelHtml5.vo.tmc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6987045854791439993L;

	private String status;//	是否成功	整型数字，Succeed	0	执行成功	Error	1	执行失败

	private String msg;//	错误信息，如果status=1，执行失败的时候，才有此字段，成功的时候没有	字符串

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
